package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Classe che reindirizza al form di creazione della pietanza.
 */
@WebServlet(name = "toAddPietanza", value = "/toAddPietanza")
public class toAddPietanza extends HttpServlet {

    /**
     * Sessione in corso
     */
    private HttpSession session;

    /**
     * Costruttore Vuoto
     */
    public toAddPietanza() {
        super();
    }

    /**Costruttore con parametri
     * @param session Sessione
     */
    public toAddPietanza(HttpSession session) {
        super();
        this.session=session;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(session==null)
            session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        assert u != null;
        RuoloUtente ruoloUtente = u.isAmministratore();
        if(!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath()+"/toHome");
        else
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/addPietanza.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
