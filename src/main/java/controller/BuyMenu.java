package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Classe che implementa l'acquisto di un menu
 */
@WebServlet(name="BuyMenu", value="/BuyMenu")
public class BuyMenu extends HttpServlet
{
    /**
     * DAO di Tessera
     */
    private final TesseraDAOInterface tdao;

    /**
     * DAO di Acquisto
     */
    private final AcquistoDAOInterface acquistodao;

    /**
     * DAO di Menu
     */
    private final MenuDAOInterface menudao;

    /**
     * DAO di Pietanza
     */
    private final PietanzaDAOInterface pdao;

    /**
     * sessione in corso
     */
    private HttpSession session;


    /**
     *    Costruttore vuoto
     */
    public BuyMenu() {
        super();
        tdao = new TesseraDAO();
        acquistodao=new AcquistoDAO();
        menudao=new MenuDAO();
        pdao=new PietanzaDAO();
    }

    /**Costruttore con parametri
     * @param tdao DAO di Tessera
     * @param acquistodao DAO di Acquisto
     * @param menudao DAO di Menu
     * @param pdao DAO di Pietanza
     * @param session Sessione
     */
    public BuyMenu(HttpSession session,TesseraDAOInterface tdao,AcquistoDAOInterface acquistodao,MenuDAOInterface menudao,PietanzaDAOInterface pdao) {
        super();
        this.tdao = tdao;
        this.acquistodao=acquistodao;
        this.menudao=menudao;
        this.pdao=pdao;
        this.session=session;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        int codiceMenu = Integer.parseInt(request.getParameter("codiceMenu"));
        if(session==null)
            session=request.getSession();
        Utente user= (Utente) session.getAttribute("utenteSessione");

        Date actual=new Date(System.currentTimeMillis());
        boolean posto= Boolean.parseBoolean(request.getParameter("postoMensa"));
        Tessera tessera=tdao.doRetrieveTesseraByKey(user.getCF());
        String[] message=new String[2];
        boolean buy=this.buy(codiceMenu,actual,posto,user.getCF(),tessera,message);
        if(!buy)
            request.setAttribute("message",message[0]);
        else
            request.setAttribute("message",message[1]);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/mensa/home.jsp");
        dispatcher.forward(request, response);

    }

    /** La classe effettua l'acquisto di un menu, se possibile.
     *
     * @pre {@literal codiceMenu!=null && actual!=null && posto!=null && CF!=null && tessera.codiceFiscale!=null && tessera.saldo!=null
     * && tessera->exists(t|t.codiceFiscale==tessera.codiceFiscale)}
     * @param codiceMenu codice del menu acquistato.
     * @param actual data del giorno.
     * @param posto stabilisce se si è prenotato un posto in mensa.
     * @param CF codice fiscale dell'utente.
     * @param tessera tessera dell'utente.
     * @param message array di stringhe con messaggi di successo o errore.
     * @return true se l'acquisto è andato a buon fine, false altrimenti.
     */
    public boolean buy(int codiceMenu,Date actual, boolean posto, String CF,Tessera tessera,String[] message)
    {

        Acquisto acquisto=new Acquisto();
        acquisto.setDataAcquisto(actual);
        acquisto.setCF(CF);
        acquisto.setCodiceMenu(codiceMenu);
        acquisto.setPostoMensa(posto);
        Menu menu=menudao.doRetrieveMenuByKey(codiceMenu);
        float prezzo=menu.getPrezzo();
        boolean hasPurchase=false;
        List<Acquisto> acquistos =acquistodao.doRetrieveAllAcquistoByCF(CF);
        for (Acquisto value : acquistos) {
            if (value.getDataAcquisto().toString().equals(actual.toString())) {
                hasPurchase = true;
                break;
            }
        }

        if(Mensa.isMensaPurchase())
        {
            if(tessera.getSaldo()-prezzo>=0)
            {
                if(!hasPurchase)
                {
                    if(posto)
                    {
                        if(Mensa.getPostiVuoti()==0)
                        {
                            message[0]="Posto non disponibile, l'operazione non ha avuto successo";
                            return false;
                        }
                        Mensa.setPostiVuoti(Mensa.getPostiVuoti()-1);
                    }
                    acquistodao.doSave(acquisto);
                    tessera.setSaldo(tessera.getSaldo()-prezzo);
                    tdao.doUpdate(tessera);
                    Pietanza primo= pdao.doRetrievePietanzaByKey(menu.getPrimo());
                    Pietanza secondo= pdao.doRetrievePietanzaByKey(menu.getSecondo());
                    Pietanza dessert= pdao.doRetrievePietanzaByKey(menu.getDessert());
                    primo.setNumeroAcquisti(primo.getNumeroAcquisti()+1);
                    secondo.setNumeroAcquisti(primo.getNumeroAcquisti()+1);
                    dessert.setNumeroAcquisti(primo.getNumeroAcquisti()+1);
                    pdao.doUpdate(primo);
                    pdao.doUpdate(secondo);
                    pdao.doUpdate(dessert);
                }
                else
                {
                    message[0]="Limite acquisto giornaliero raggiunto, l'operazione non ha avuto successo.";
                    return false;
                }
            }
            else
            {
                message[0]="Saldo insufficiente, l'operazione non ha avuto successo.";
                return false;
            }
        }
        else
        {
            message[0]="Il periodo per effettuare acquisti è terminato, ritorni nella fascia d'orario consentita.";
            return false;
        }
        message[1]="Operazione di acquisto completata con successo.";
        return true;
    }
}