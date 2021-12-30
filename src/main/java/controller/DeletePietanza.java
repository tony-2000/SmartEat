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
 * Classe che elimina una pietanza da parte dell'admin
 */
@WebServlet(name="DeletePietanza", value="/DeletePietanza")
public class DeletePietanza extends HttpServlet {

    /**
     * DAO di Pietanza
     */
    private final PietanzaDAOInterface pietanzaDao;

    /**
     * Sessione in corso
     */
    private HttpSession session;

    /**
     * Costruttore vuoto
     */
    public DeletePietanza() {
        super();
        pietanzaDao=new PietanzaDAO();
    }

    /**Costruttore con parametri
     * @param pietanzaDao DAO di Pietanza
     * @param session Sessione
     */
    public DeletePietanza(PietanzaDAOInterface pietanzaDao,HttpSession session) {
        super();
       this.pietanzaDao=pietanzaDao;
       this.session=session;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        if(session==null)
            session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        assert u != null;
        RuoloUtente ruoloUtente = u.isAmministratore();
        if (!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath() + "/toHome");
        else
        {
            String nomePietanza = request.getParameter("nomePietanza");
            boolean result = this.deletePietanza(nomePietanza);
            String message;
            if (result)
                message = "La pietanza è stata eliminata correttamente";
            else
                message = "La pietanza verrà eliminata dopo l'orario di chiusura";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminPietanzeArea");
            dispatcher.forward(request, response);
        }
    }


    /**
     * Elimina una pietanza quando possibile
     * @pre {@literal nomePietanza!=null}
     * @post {@literal !pietanza->exist(m|m.nome!=nomePietanza)}
     * @param nomePietanza nome della pietanza da eliminare
     * @return true se la pietanza e' stato eliminata all'istante, false se elimiata successivamente
     */
    public boolean deletePietanza(String nomePietanza)
    {
        if (Mensa.isMensaConfig()) {
            pietanzaDao.doDelete(nomePietanza);
            return true;
        }
        ArrayList<String> listDeletePietanza = Pietanza.getListDeletePietanza();
        listDeletePietanza.add(nomePietanza);
        return false;
    }
}
