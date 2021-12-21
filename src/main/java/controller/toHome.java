package controller;

import model.MensaDAO;
import model.MensaDAOInterface;

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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        MensaDAOInterface mensadao=new MensaDAO();
        ArrayList<String> mensa=mensadao.doRetrieveMensaByKey("mensa1");
        session.setAttribute("nomeMensa",mensa.get(0));
        session.setAttribute("postiMensa",Integer.valueOf(mensa.get(1)));
        session.setAttribute("aperturaMensa", Time.valueOf(mensa.get(2)));
        session.setAttribute("chiusuraMensa",Time.valueOf(mensa.get(3)));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/mensa/home.jsp");
        dispatcher.forward(request, response);
    }
}
