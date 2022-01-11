package controller.GestioneProfilo;


import model.tessera.Tessera;
import model.tessera.TesseraDAO;
import model.tessera.TesseraDAOInterface;
import model.utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Classe che permette di visualizzare i dati dell'utente.
 */
@WebServlet(name="ShowProfile", value="/ShowProfile")
public class ShowProfile extends HttpServlet {

    /**
     * DAO di Tessera
     */
    private final TesseraDAOInterface tdao;


    /**
     * Costruttore Vuoto
     */
    public ShowProfile() {
        super();
        tdao = new TesseraDAO();
    }
    /**Costruttore con parametri
     * @param tdao DAO di Tessera
     */
    public ShowProfile(TesseraDAOInterface tdao) {
        super();
        this.tdao = tdao;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        Utente user= (Utente) session.getAttribute("utenteSessione");
        if(user==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else
        {
            String CF = user.getCF();
            Tessera tessera = tdao.doRetrieveTesseraByKey(CF);
            request.setAttribute("tessera", tessera);
            System.out.println("servlet " + tessera.getSaldo()); //DEBUG
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/utente/showProfile.jsp");
            dispatcher.forward(request, response);
        }
    }
}


