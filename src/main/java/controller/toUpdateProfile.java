package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe che reindirizza al form di aggiornamento dati utente.
 */
@WebServlet(name="toUpdateProfile", value="/toUpdateProfile")
public class toUpdateProfile extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/utente/updateProfile.jsp");
        dispatcher.forward(request, response);
    }
}
