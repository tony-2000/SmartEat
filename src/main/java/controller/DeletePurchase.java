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
        response.sendRedirect(request.getContextPath()+"/toHome");
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
        MenuDAOInterface menudao=new MenuDAO();
        Menu menu=menudao.doRetrieveMenuByKey(codiceMenu);
        AcquistoDAOInterface acquistodao=new AcquistoDAO();
        Acquisto acquisto= acquistodao.doRetrieveAcquistoByKey(data,CF,codiceMenu);

        long actual=System.currentTimeMillis();
        Date actualDate = new Date(actual);

        if(Mensa.isMensaPurchase()&&acquisto.getDataAcquisto().equals(actualDate))
            acquisto.setRefund(true);
        else acquisto.setRefund(false);

        TesseraDAOInterface tdao=new TesseraDAO();
        Tessera tessera=tdao.doRetrieveTesseraByKey(CF);

        if(acquisto.isRefund())
        {
            tessera.setSaldo(tessera.getSaldo()+menu.getPrezzo());
            acquistodao.doDelete(data,CF,codiceMenu);
            message[1]="Rimborso effettuato con successo";
            return true;
        }
        else
        {
            message[0]="Rimborso non effettuato, si prega di riprovare.";
            return false;
        }
    }

}