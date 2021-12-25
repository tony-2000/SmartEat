package controller;


import model.RuoloUtente;
import model.Utente;
import model.UtenteDAO;
import model.UtenteDAOInterface;

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
 *
 */
@WebServlet(name = "AdminUtentiArea", value = "/AdminUtentiArea")
public class AdminUtentiArea extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        assert u != null;
        RuoloUtente ruoloUtente = u.isAmministratore();
        if(!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath()+"/toHome");
        else
        {
            ArrayList<Utente> utenti=this.ShowAllUsers();
            request.setAttribute("listaUtenti",utenti);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminUtentiArea.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }


    /**
     *
     * @return
     */
    public ArrayList<Utente> ShowAllUsers()
    {
        UtenteDAOInterface dao = new UtenteDAO();
        ArrayList<Utente> listUtenti = (ArrayList<Utente>) dao.doRetrieveAllUtente();
        ArrayList<Utente> accepted = new ArrayList<>();
        for (Utente x : listUtenti)
        {
            if (x.isAccepted())
                    accepted.add(x);
        }
        return accepted;
    }
}
