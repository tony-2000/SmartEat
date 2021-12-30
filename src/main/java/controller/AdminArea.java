package controller;

import model.PietanzaDAO;
import model.PietanzaDAOInterface;
import model.RuoloUtente;
import model.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Classe che porta all'Area Admin
 */
@WebServlet(name = "AdminArea", value = "/AdminArea")
public class AdminArea extends HttpServlet {

    private HttpSession session;

    public AdminArea() {
        super();
    }

    public AdminArea(HttpSession session) {
        super();
        this.session=session;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(session==null)
            session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        assert u != null;
        RuoloUtente ruoloUtente = u.isAmministratore();
        if(!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath()+"/toHome");
        else
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminArea.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
