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
 * Classe che reindirizza al form di acquisto di un menu
 */
@WebServlet(name="toBuyMenu", value="/toBuyMenu")
public class toBuyMenu extends HttpServlet
{
    /**
     * DAO di Tessera
     */
    private final TesseraDAOInterface tdao;
    /**
     * DAO di Menu
     */
    private final MenuDAOInterface mdao;
    /**
     * Sessione in corso
     */
    private HttpSession session;

    /**
     * Costruttore Vuoto
     */
    public toBuyMenu() {
        super();
        tdao = new TesseraDAO();
        mdao=new MenuDAO();
    }

    /**Costruttore con parametri
     * @param mdao DAO di Menu
     * @param tdao DAO di Tessera
     * @param session Sessione
     */
    public toBuyMenu(TesseraDAOInterface tdao,MenuDAOInterface mdao,HttpSession session) {
        super();
        this.tdao = tdao;
        this.mdao=mdao;
        this.session=session;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int codiceMenu = Integer.parseInt(request.getParameter("codiceMenu"));
        Menu menu=mdao.doRetrieveMenuByKey(codiceMenu);
        request.setAttribute("menu",menu);
        if(session==null)
            session=request.getSession();
        Utente user= (Utente) session.getAttribute("utenteSessione");
        Tessera tessera=tdao.doRetrieveTesseraByKey(user.getCF());
        request.setAttribute("tessera",tessera);
        request.setAttribute("postiVuoti", Mensa.getPostiVuoti());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/mensa/buyMenu.jsp");
        dispatcher.forward(request, response);
    }
}