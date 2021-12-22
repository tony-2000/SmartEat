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
import java.util.ArrayList;

@WebServlet(name="ShowPurchases", value="/ShowPurchases")
public class ShowPurchases extends HttpServlet
{
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        Utente user= (Utente) session.getAttribute("utenteSessione");
        ArrayList<Acquisto> acquisti=this.showAllAcquisti(user);
        request.setAttribute("listaAcquisti",acquisti);
        RequestDispatcher dispatcher = request.getRequestDispatcher("showPurchases.jsp");
        dispatcher.forward(request, response);
    }

    public ArrayList<Acquisto> showAllAcquisti(Utente user)
    {
        AcquistoDAOInterface acquistodao=new AcquistoDAO();
        return (ArrayList<Acquisto>) acquistodao.doRetrieveAllAcquistoByCF(user.getCF());
    }

}