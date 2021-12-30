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
import java.sql.Time;
import java.util.ArrayList;

/**
 * Classe che reindirizza alla Home
 */
@WebServlet(name="toHome", value="/toHome")
public class toHome extends HttpServlet {

    /**
     * Sessione in corso
     */
    private HttpSession session;

    /**
     * Costruttore Vuoto
     */
    public toHome() {
        super();
    }

    /**Costruttore con parametri
     * @param session Sessione
     */
    public toHome(HttpSession session) {
        super();
        this.session=session;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(session==null)
            session=request.getSession();
        session.setAttribute("nomeMensa",Mensa.mensa.getNome());
        session.setAttribute("postiMensa",Mensa.mensa.getPostiDisponibili());
        session.setAttribute("aperturaMensa", Mensa.mensa.getOrarioApertura());
        session.setAttribute("chiusuraMensa",Mensa.mensa.getOrarioChiusura());
        session.setAttribute("postiVuoti",Mensa.getPostiVuoti());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/mensa/home.jsp");
        dispatcher.forward(request, response);
    }
}
