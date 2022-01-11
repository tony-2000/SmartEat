package controller.GestioneMenu;

import model.pietanza.Pietanza;
import model.pietanza.PietanzaDAO;
import model.pietanza.PietanzaDAOInterface;
import model.utente.RuoloUtente;
import model.utente.Utente;

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

    /**
     * DAO di Pietanza
     */
    private final PietanzaDAOInterface pdao;


    /**
     * Costruttore Vuoto
     */
    public toAddMenu() {
        super();
        pdao=new PietanzaDAO();
    }
    /**Costruttore con parametri
     * @param pdao DAO di Pietanza
     */
    public toAddMenu(PietanzaDAOInterface pdao) {
        super();
        this.pdao=pdao;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else{
        RuoloUtente ruoloUtente = u.isAmministratore();
        if(!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath()+"/toHome");
        else {
            ArrayList<Pietanza> pietanze = (ArrayList<Pietanza>) pdao.doRetrieveAllPietanza();
            request.setAttribute("pietanze", pietanze);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/addMenu.jsp");
            dispatcher.forward(request, response);
        }
        }
    }
}
