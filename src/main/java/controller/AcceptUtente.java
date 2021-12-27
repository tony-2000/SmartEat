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

/**
 * Classe che implementa l'accettazione degli utenti da parte dell'amministratore.
 */
@WebServlet(name = "AcceptUtente", value = "/AcceptUtente")
public class AcceptUtente extends HttpServlet {
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
            String CF=request.getParameter("CFUser");
            boolean accept= Boolean.parseBoolean(request.getParameter("accept"));
            CompleteReg(CF,accept);
            String message;
            if(accept)
                message="Utente accettato correttamente";
            else
                message="Utente rifiutato correttamente";
            request.setAttribute("message",message);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/toAcceptUtente");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    /** Completa la registrazione di un utente accettandolo o meno.
     * @pre {@literal CF!=null}
     * @param CF Codice fiscale dell'utente che deve essere accettato o meno.
     * @param accept true se l'utente è accettato, false se l'utente non è accettato.
     */
        public void CompleteReg(String CF,boolean accept)
        {
            UtenteDAOInterface dao= new UtenteDAO();
            Utente user=dao.doRetrieveUtenteByKey(CF);
            user.setAccepted(accept);
            dao.doAccept(user.getCF());
            if(!accept)
                dao.doDelete(CF);
        }
}