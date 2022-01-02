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
 * Classe che mostra le informazioni di un acquisto.
 */
@WebServlet(name="ShowInfoPurchase", value="/ShowInfoPurchase")
public class ShowInfoPurchase extends HttpServlet
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
     * Costruttore Vuoto
     */
    public ShowInfoPurchase() {
        super();
        tdao = new TesseraDAO();
        acquistodao=new AcquistoDAO();
        menudao=new MenuDAO();

    }
    /**Costruttore con parametri
     * @param menudao DAO di Menu
     * @param tdao DAO di Tessera
     * @param acquistodao DAO di Menu
     */
    public ShowInfoPurchase(TesseraDAOInterface tdao,AcquistoDAOInterface acquistodao,MenuDAOInterface menudao) {
        super();
        this.tdao = tdao;
        this.acquistodao=acquistodao;
        this.menudao=menudao;
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Date data= Date.valueOf(request.getParameter("dataAcquisto"));
        String CF=request.getParameter("CF");
        int codiceMenu= Integer.parseInt(request.getParameter("codiceMenu"));
        Menu menu=menudao.doRetrieveMenuByKey(codiceMenu);
        Acquisto acquisto=this.showInfoPurchase(codiceMenu,CF,data);

        long actual=System.currentTimeMillis();
        String actualDate = new Date(actual).toString();
        String dataAcquisto = acquisto.getDataAcquisto().toString();
        if(Mensa.isMensaPurchase() && dataAcquisto.equals(actualDate))
            acquisto.setRefund(true);
        else acquisto.setRefund(false);

        Tessera tessera=tdao.doRetrieveTesseraByKey(CF);

        request.setAttribute("tessera",tessera);
        request.setAttribute("menu",menu);
        request.setAttribute("acquisto",acquisto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/mensa/showInfoPurchase.jsp");
        dispatcher.forward(request, response);
    }

    /** Restituisce l'acquisto con una chiave specifica, se presente, altrimenti restituisce un oggetto Acquisto vuoto.
     * @pre {@literal codiceMenu!=null && CF!=null && data!=null}
     * @post {@literal Acquisto (empty) || acquisto->select(a|a.codiceMenu==codiceMenu && a.codiceFiscale==CF && a.dataAcquisto==data)}
     * @param codiceMenu codice del menu acquistato.
     * @param CF codice fiscale dell'utente che ha acquistato.
     * @param data data dell'acquisto.
     * @return Acquisto con la chiave richiesta, se presente.
     */
    public Acquisto showInfoPurchase(int codiceMenu,String CF, Date data)
    {
        return acquistodao.doRetrieveAcquistoByKey(data,CF,codiceMenu);
    }
}