package controller.MensaConfig;


import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


@WebServlet(name="ShowInfoPurchase", value="/ShowInfoPurchase")
public class ShowInfoPurchase extends HttpServlet
{
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Date data= Date.valueOf(request.getParameter("dataAcquisto"));
        String CF=request.getParameter("CF");
        int codiceMenu= Integer.parseInt(request.getParameter("codiceMenu"));
        MenuDAOInterface menudao=new MenuDAO();
        Menu menu=menudao.doRetrieveMenuByKey(codiceMenu);
        Acquisto acquisto=this.showInfoPurchase(codiceMenu,CF,data);

        long actual=System.currentTimeMillis();
        Date actualDate = null;
        assert false;
        actualDate.setTime(actual);
        if(Mensa.isMensaPurchase()&&acquisto.getDataAcquisto().equals(actualDate))
            acquisto.setRefund(true);
        else acquisto.setRefund(false);

        TesseraDAOInterface tdao=new TesseraDAO();
        Tessera tessera=tdao.doRetrieveTesseraByKey(CF);

        request.setAttribute("tessera",tessera);
        request.setAttribute("menu",menu);
        request.setAttribute("acquisto",acquisto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("showInfoPurchase.jsp");
        dispatcher.forward(request, response);
    }

    public Acquisto showInfoPurchase(int codiceMenu,String CF, Date data )
    {
        AcquistoDAOInterface acquistodao=new AcquistoDAO();
        return acquistodao.doRetrieveAcquistoByKey(data,CF,codiceMenu);
    }
}