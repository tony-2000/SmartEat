package controller;


import model.Tessera;
import model.TesseraDAO;
import model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Classe che permette di visualizzare i dati dell'utente.
 */
@WebServlet(name="ShowProfile", value="/ShowProfile")
public class ShowProfile extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        TesseraDAO tdao=new TesseraDAO();
        HttpSession session=request.getSession();
        Utente user= (Utente) session.getAttribute("utenteSessione");
        String CF=user.getCF();
        Tessera tessera= tdao.doRetrieveTesseraByKey("CF");
        request.setAttribute("tessera",tessera);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/userProfile.jsp");
        dispatcher.forward(request, response);
    }
}


