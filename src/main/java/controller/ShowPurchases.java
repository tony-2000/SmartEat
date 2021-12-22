package controller;

import model.Acquisto;
import model.AcquistoDAO;
import model.AcquistoDAOInterface;
import model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        AcquistoDAOInterface acquistodao=new AcquistoDAO();
        ArrayList<Acquisto> acquisti= (ArrayList<Acquisto>) acquistodao.doRetrieveAllAcquistoByCF(user.getCF());

        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

}