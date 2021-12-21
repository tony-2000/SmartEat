package controller;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;


@WebServlet(name="ShowInfoMenu", value="/ShowInfoMenu")
public class ShowInfoMenu extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int codiceMenu = Integer.parseInt(request.getParameter("codiceMenu"));
        Menu menu=showMenuInfo(codiceMenu);
        request.setAttribute("menu",menu);
        String message="Acquisto non consentito, la mensa è attualmente chiusa all'acquisto, si prega di riprovare nei tempi " +
                         "concessi all'acquisto dei pasti";
        boolean mensaAperta=Mensa.mensaAperta();
        if(!mensaAperta)
            request.setAttribute("message",message);
        request.setAttribute("mensaAperta",mensaAperta);
        RequestDispatcher dispatcher = request.getRequestDispatcher("showInfoMenu.jsp");
        dispatcher.forward(request, response);
    }

    public Menu showMenuInfo(int codiceMenu)
    {
        MenuDAOInterface mdao=new MenuDAO();
        return mdao.doRetrieveMenuByKey(codiceMenu);
    }
}