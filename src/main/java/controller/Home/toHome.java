package controller.Home;

import model.mensa.Mensa;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Classe che reindirizza alla Home
 */
@WebServlet(name="toHome", value="/toHome")
public class toHome extends HttpServlet {


    /**
     * Costruttore Vuoto
     */
    public toHome() {
        super();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        session.setAttribute("nomeMensa",Mensa.mensa.getNome());
        session.setAttribute("postiMensa",Mensa.mensa.getPostiDisponibili());
        session.setAttribute("aperturaMensa", Mensa.mensa.getOrarioApertura());
        session.setAttribute("chiusuraMensa",Mensa.mensa.getOrarioChiusura());
        session.setAttribute("postiVuoti",Mensa.getPostiVuoti());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/mensa/home.jsp");
        dispatcher.forward(request, response);
    }
}
