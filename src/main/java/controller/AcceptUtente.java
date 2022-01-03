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

/**
 * Classe che implementa l'accettazione degli utenti da parte dell'amministratore.
 */
@WebServlet(name = "AcceptUtente", value = "/AcceptUtente")
public class AcceptUtente extends HttpServlet {
    /**
     * DAO di Utente
     */
    private final UtenteDAOInterface dao;

    /**
     * DAO di Tessera
     */
    private final TesseraDAOInterface tdao;


    /**
     * Costruttore Vuoto
     */
    public AcceptUtente() {
        super();
        dao = new UtenteDAO();
        tdao = new TesseraDAO();
    }

    /**
     * Costruttore con parametri
     *
     * @param dao     DAO di Utente
     * @param tdao    DAO di Tessera
     */
    public AcceptUtente(UtenteDAOInterface dao, TesseraDAOInterface tdao) {
        super();
        this.dao = dao;
        this.tdao = tdao;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Utente u = (Utente) session.getAttribute("utenteSessione");
        if (u == null)
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        else {
            RuoloUtente ruoloUtente = u.isAmministratore();
            if (!ruoloUtente.isAdmin())
                response.sendRedirect(request.getContextPath() + "/toHome");
            else {
                String CF = request.getParameter("CFUser");
                boolean accept = Boolean.parseBoolean(request.getParameter("accept"));
                CompleteReg(CF, accept);
                String message;
                if (accept)
                    message = "Utente accettato correttamente";
                else
                    message = "Utente rifiutato correttamente";
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/toAcceptUtente");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Completa la registrazione di un utente accettandolo o meno.
     *
     * @param CF     Codice fiscale dell'utente che deve essere accettato o meno.
     * @param accept true se l'utente è accettato, false se l'utente non è accettato.
     * @pre {@literal CF!=null}
     */
    public void CompleteReg(String CF, boolean accept) {
        Utente user = dao.doRetrieveUtenteByKey(CF);
        if (accept) {
            dao.doAccept(user.getCF());
            Tessera tessera = new Tessera();
            tessera.setCF(CF);
            tessera.setSaldo(0);
            tdao.doSave(tessera);
        }
        if (!accept)
            dao.doDelete(CF);
    }
}