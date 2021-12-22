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

        RequestDispatcher dispatcher = request.getRequestDispatcher("showInfoPurchase.jsp");
        dispatcher.forward(request, response);
    }

    public boolean Rimborso(int codiceMenu, String CF, Date data, String[] message )
    {
        MenuDAOInterface menudao=new MenuDAO();
        Menu menu=menudao.doRetrieveMenuByKey(codiceMenu);
        AcquistoDAOInterface acquistodao=new AcquistoDAO();
        Acquisto acquisto= acquistodao.doRetrieveAcquistoByKey(data,CF,codiceMenu);

        long actual=System.currentTimeMillis();
        Date actualDate = null;
        assert false;
        actualDate.setTime(actual);

        if(Mensa.isMensaPurchase()&&acquisto.getDataAcquisto().equals(actualDate))
            acquisto.setRefund(true);
        else acquisto.setRefund(false);
        TesseraDAOInterface tdao=new TesseraDAO();
        Tessera tessera=tdao.doRetrieveTesseraByKey(CF);

        if(acquisto.isRefund())
        {
            tessera.setSaldo(tessera.getSaldo()+menu.getPrezzo());
            acquistodao.doDelete(data,CF,codiceMenu);
        }
        else
        {

        }

    }

}