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

    /**
     * DAO di Pietanza
     */
    private final PietanzaDAOInterface pdao;
    /**
     * Sessione in corso
     */
    private HttpSession session;

    /**
     * Costruttore Vuoto
     */
    public toAddMenu() {
        super();
        pdao=new PietanzaDAO();
    }
    /**Costruttore con parametri
     * @param pdao DAO di Pietanza
     * @param session Sessione
     */
    public toAddMenu(PietanzaDAOInterface pdao,HttpSession session) {
        super();
        this.pdao=pdao;
        this.session=session;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(session==null)
            session=request.getSession();
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
