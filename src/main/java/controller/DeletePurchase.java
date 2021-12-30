package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Questa classe elimina un acquisto ed effettua il rimborso se possibile.
 */
@WebServlet(name="DeletePurchase", value="/DeletePurchase")
public class DeletePurchase extends HttpServlet
{

    private final TesseraDAOInterface tdao;
    private final AcquistoDAOInterface acquistodao;
    private final MenuDAOInterface menudao;
    private final PietanzaDAOInterface pdao;

    public DeletePurchase() {
        super();
        tdao = new TesseraDAO();
        acquistodao=new AcquistoDAO();
        menudao=new MenuDAO();
        pdao=new PietanzaDAO();
    }

    public DeletePurchase(TesseraDAOInterface tdao,AcquistoDAOInterface acquistodao,MenuDAOInterface menudao,PietanzaDAOInterface pdao) {
        super();
        this.tdao = tdao;
        this.acquistodao=acquistodao;
        this.menudao=menudao;
        this.pdao=pdao;
    }


    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String[] message=new String[2];
        Date data= Date.valueOf(request.getParameter("dataAcquisto"));
        String CF=request.getParameter("CF");
        int codiceMenu= Integer.parseInt(request.getParameter("codiceMenu"));
        boolean refund=this.Rimborso(codiceMenu,CF,data,message);
        if(refund)
            request.setAttribute("message",message[1]);
        else
            request.setAttribute("message",message[0]);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/mensa/home.jsp");
        dispatcher.forward(request, response);
    }

    /** Restituisce un booleano che conferma l'eliminazione dell'acquisto e rimborso oppure segnala un errore.
     * @pre {@literal codiceMenu!=null && CF!=null && data!=null}
     * @post {@literal !acquisto->exists(a|a.dataAcquisto==dataAcquisto && a.codiceFiscale==CF && a.codiceMenu==codiceMenu)}
     * @param codiceMenu codice del menu
     * @param CF codice fiscale
     * @param data data dell'acquisto
     * @param message array di stringhe con messaggi di errore o conferma.
     * @return booleano di conferma rimborso o errore.
     */
    public boolean Rimborso(int codiceMenu, String CF, Date data, String[] message )
    {
        Menu menu=menudao.doRetrieveMenuByKey(codiceMenu);
        Acquisto acquisto= acquistodao.doRetrieveAcquistoByKey(data,CF,codiceMenu);

        long actual=System.currentTimeMillis();
        Date actualDate = new Date(actual);

        if (Mensa.isMensaPurchase() && acquisto.getDataAcquisto().toString().equals(actualDate.toString()))
            acquisto.setRefund(true);
        else acquisto.setRefund(false);

        Tessera tessera=tdao.doRetrieveTesseraByKey(CF);

        if(acquisto.isRefund())
        {
            tessera.setSaldo(tessera.getSaldo()+menu.getPrezzo());
            tdao.doUpdate(tessera);
            acquistodao.doDelete(data,CF,codiceMenu);

            Pietanza primo= pdao.doRetrievePietanzaByKey(menu.getPrimo());
            Pietanza secondo= pdao.doRetrievePietanzaByKey(menu.getSecondo());
            Pietanza dessert= pdao.doRetrievePietanzaByKey(menu.getDessert());
            primo.setNumeroAcquisti(primo.getNumeroAcquisti()-1);
            secondo.setNumeroAcquisti(primo.getNumeroAcquisti()-1);
            dessert.setNumeroAcquisti(primo.getNumeroAcquisti()-1);
            pdao.doUpdate(primo);
            pdao.doUpdate(secondo);
            pdao.doUpdate(dessert);

            message[1]="Rimborso effettuato con successo";
            return true;
        }
        else
        {
            message[0]="Rimborso non effettuato perché il tempo per il rimborso è scaduto.";
            return false;
        }
    }

}