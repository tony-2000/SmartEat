package controller.GestionePietanze;

import model.utente.RuoloUtente;
import model.utente.Utente;

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
     * Costruttore Vuoto
     */
    public toAddPietanza() {
        super();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else{
        RuoloUtente ruoloUtente = u.isAmministratore();
        if(!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath()+"/toHome");
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/addPietanza.jsp");
            dispatcher.forward(request, response);
        }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
