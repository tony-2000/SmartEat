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
 * Classe che porta all'Area Admin dedicata alla mensa
 */
@WebServlet(name = "AdminMensaArea", value = "/AdminMensaArea")
public class AdminMensaArea extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        assert u != null;
        RuoloUtente ruoloUtente = u.isAmministratore();
        if(!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath()+"/toHome");
        else
        {
            ArrayList<String> mensa= this.adminMensa();
            request.setAttribute("mensa",mensa);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminMensaArea.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    /**
     *Restituisce le informazioni relative alla mensa
     * @return lista informazioni della mensa
     */
    public ArrayList<String> adminMensa()
    {
        MensaDAOInterface mensadao=new MensaDAO();
        return mensadao.doRetrieveMensaByKey("mensa1");
    }
}
