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
import java.util.ArrayList;

/**
 * Classe che reindirizza alla pagina di accettazione degli utenti.
 */
@WebServlet(name = "toAcceptUtente", value = "/toAcceptUtente")
public class toAcceptUtente extends HttpServlet {

    /**
     * DAO di Utente
     */
    private final UtenteDAOInterface dao;

    /**
     * Costruttore Vuoto
     */
    public toAcceptUtente() {
        super();
        dao=new UtenteDAO();
    }
    /**Costruttore con parametri
     * @param dao DAO di Utente
     */
    public toAcceptUtente(UtenteDAOInterface dao) {
        super();
        this.dao=dao;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else{
        RuoloUtente ruoloUtente = u.isAmministratore();
        if(!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath()+"/toHome");
        else {
            ArrayList<Utente> utenti = this.ShowAllUsersToAccept();
            request.setAttribute("listaUtenti", utenti);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/acceptUtente.jsp");
            dispatcher.forward(request, response);
        }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    /** Restituisce tutti gli utenti non accettati e che hanno fatto una richiesta di registrazione alla piattaforma.
     * @post {@literal List=utente->select(u|u.accepted==false)->asSet()}
     * @return List con tutti gli utenti da accettare.
     */
    public ArrayList<Utente> ShowAllUsersToAccept()
    {
        ArrayList<Utente> listUtenti = (ArrayList<Utente>) dao.doRetrieveAllUtente();
        ArrayList<Utente> toAccept = new ArrayList<>();
        for (Utente x : listUtenti)
        {
            if (!x.isAccepted())
                toAccept.add(x);
        }
        return toAccept;
    }

}
