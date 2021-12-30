package controller;


import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe che reindirizza al form di registrazione.
 */
@WebServlet(name="toSignUp", value="/toSignUp")
public class toSignUp extends HttpServlet {

    /**
     * Costruttore Vuoto
     */
    public toSignUp() {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("signUp.jsp");
        dispatcher.forward(request, response);
    }
}
