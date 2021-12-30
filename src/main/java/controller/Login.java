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
 * Classe che implementa il login al sistema.
 */
@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

    /**
     * DAO di Utente
     */
    private final UtenteDAOInterface udao;

    /**
     * Sessione in corso
     */
    private HttpSession session;

    /**
     * Costruttore vuoto
     */
    public Login() {
        super();
        udao = new UtenteDAO();
    }

    /**Costruttore con parametri
     * @param udao DAO di Utente
     * @param session Sessione
     */
    public Login(UtenteDAOInterface udao, HttpSession session) {
        super();
        this.udao = udao;
        this.session = session;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] strings = new String[2];
        if (session == null)
            session = request.getSession();
        String resp = "/WEB-INF/results/mensa/home.jsp";
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        Utente user = this.login(mail, password, strings);

        if (user.getCF() == null) {
            String err = "Dati utente scorretti";
            if (strings[1] != null)
                err = err + ": " + strings[1];
            resp = "login.jsp";
            request.setAttribute("logError", err);
        } else if (!(strings[0] == null)) {
            resp = "login.jsp";
            request.setAttribute("logError", strings[0]);
        } else
            session.setAttribute("utenteSessione", user);
        session.setAttribute("nomeMensa", Mensa.mensa.getNome());
        session.setAttribute("postiMensa", Mensa.mensa.getPostiDisponibili());
        session.setAttribute("aperturaMensa", Mensa.mensa.getOrarioApertura());
        session.setAttribute("chiusuraMensa", Mensa.mensa.getOrarioChiusura());
        RequestDispatcher dispatcher = request.getRequestDispatcher(resp);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Questo metodo verifica che l'utente sia salvato nel database.
     *
     * @param mail     Mail dell'utente
     * @param password Password dell'utente
     * @return Utente (empty) se vi sono errori nella richiesta login oppure l'utente non esiste,
     * altrimenti restituisce le informazioni dell'Utente
     * @post {@literal Utente (empty) || utente->select(u|u.email=email && u.password==password}
     */
    public Utente login(String mail, String password, String[] strings) {
        Esito res;

        res = Check.mailIsValidLogin(mail);
        if (!res.isValido()) {
            strings[1] = res.getMessage();
            return new Utente();
        }

        res = Check.passwordIsValid(password, password);
        if (!res.isValido()) {
            strings[1] = res.getMessage();
            return new Utente();
        }

        Utente u = udao.doRetrieveUtenteByEmailPassword(mail, password);
        if (!u.isAccepted())
            strings[0] = "L'amministratore non ha ancora accettato la tua richiesta di registrazione.";
        return u;
    }
}