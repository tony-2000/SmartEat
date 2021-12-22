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
import java.util.ArrayList;

/**
 * Classe che reindirizza al form di creazione del menu.
 */
@WebServlet(name="toAddMenu", value="/toAddMenu")
public class toAddMenu extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        RuoloUtente ruoloUtente = u.isAmministratore();
        if(!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath()+"/toHome");
        else
        {
            PietanzaDAOInterface pdao=new PietanzaDAO();
            ArrayList<Pietanza> pietanze= (ArrayList<Pietanza>) pdao.doRetrieveAllPietanza();
            request.setAttribute("pietanze",pietanze);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/addMenu.jsp");
            dispatcher.forward(request, response);
        }
    }
}
