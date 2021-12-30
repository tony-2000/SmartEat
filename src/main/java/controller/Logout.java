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
 * Classe che permette di effettuare il logout dal sistema.
 */
@WebServlet(name="Logout", value="/Logout")
public class Logout extends HttpServlet
{
    /**
     * Sessione in corso
     */
    private HttpSession session;

    /**
     * Costruttore vuoto
     */
    public Logout() {
        super();
    }

    /**Costruttore con parametri
     * @param session Sessione
     */
    public Logout(HttpSession session) {
        super();
        this.session=session;
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(session==null)
            session=request.getSession();
        session.removeAttribute("utenteSessione");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

}