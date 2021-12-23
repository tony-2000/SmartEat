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


//Se non ci sono posti vuoti non fa comparire la checkbox e rimanda "postoMensa" a false

/**
 * Classe che implementa l'acquisto di un menu
 */
@WebServlet(name="BuyMenu", value="/BuyMenu")
public class BuyMenu extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        int codiceMenu = Integer.parseInt(request.getParameter("codiceMenu"));
        HttpSession session=request.getSession();
        Utente user= (Utente) session.getAttribute("utenteSessione");

        Date actual=new Date(System.currentTimeMillis());
        boolean posto= Boolean.parseBoolean(request.getParameter("postoMensa"));
        TesseraDAOInterface tdao=new TesseraDAO();
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
        TesseraDAOInterface tesseradao=new TesseraDAO();
        AcquistoDAOInterface acquistodao=new AcquistoDAO();
        Acquisto acquisto=new Acquisto();
        acquisto.setDataAcquisto(actual);
        acquisto.setCF(CF);
        acquisto.setCodiceMenu(codiceMenu);
        acquisto.setPostoMensa(posto);
        MenuDAOInterface menudao=new MenuDAO();
        Menu menu=menudao.doRetrieveMenuByKey(codiceMenu);
        float prezzo=menu.getPrezzo();
        if(Mensa.isMensaPurchase())
        {
            if(tessera.getSaldo()-prezzo>=0)
            {
                if(acquistodao.doRetrieveAcquistoByKey(actual, CF, codiceMenu).getDataAcquisto()==null)
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
                    tesseradao.doUpdate(tessera);
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