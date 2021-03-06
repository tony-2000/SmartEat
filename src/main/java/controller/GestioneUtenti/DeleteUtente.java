package controller.GestioneUtenti;


import model.utente.RuoloUtente;
import model.utente.Utente;
import model.utente.UtenteDAO;
import model.utente.UtenteDAOInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Classe che implementa l'eliminazione di un utente.
 */
@WebServlet(name = "DeleteUtente", value = "/DeleteUtente")
public class DeleteUtente extends HttpServlet {

    /**
     * DAO di Utente
     */
    private final UtenteDAOInterface dao;

    /**
     * Costruttore vuoto
     */
    public DeleteUtente() {
        super();
        dao=new UtenteDAO();
    }

    /**Costruttore con parametri
     * @param dao DAO di Utente
     */
    public DeleteUtente(UtenteDAOInterface dao) {
        super();
       this.dao=dao;
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
            String CF = request.getParameter("CF");
            DeleteUser(CF);
            String message = "Utente eliminato correttamente.";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminUtentiArea");
            dispatcher.forward(request, response);
        }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    /** Elimina un utente.
     * @pre {@literal CF!=null && utente->exists(u|u.codiceFiscale==CF)}
     * @post {@literal !(utente->exists(u|u.codiceFiscale==CF))}
     * @param CF Codice fiscale dell'utente da eliminare.
     */
        public void DeleteUser(String CF)
        {
            dao.doDelete(CF);
        }
}
