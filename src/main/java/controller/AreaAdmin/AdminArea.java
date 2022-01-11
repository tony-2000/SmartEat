package controller.AreaAdmin;

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

/**
 * Classe che porta all'Area Admin
 */
@WebServlet(name = "AdminArea", value = "/AdminArea")
public class AdminArea extends HttpServlet {

    /**
     * Costruttore Vuoto
     */
    public AdminArea() {
        super();
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminArea.jsp");
            dispatcher.forward(request, response);
        }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
