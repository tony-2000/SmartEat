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
 * Classe che implementa la visualizzazione di tutti i menu disponibili all'acquisto.
 */
@WebServlet(name="ShowAllMenus", value="/ShowAllMenus")
public class ShowAllMenus extends HttpServlet
{
    /**
     * DAO di Menu
     */
    private final MenuDAOInterface mdao;

    /**
     * Sessione in corso
     */
    private HttpSession session;

    /**
     * Cotruttore vuoto
     */
    public ShowAllMenus() {
        super();
        mdao=new MenuDAO();
    }

    /**Costruttore con parametri
     * @param mdao DAO di Menu
     * @param session Sessione
     */
    public ShowAllMenus(MenuDAOInterface mdao,HttpSession session) {
        super();
        this.mdao=mdao;
        this.session=session;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if(session==null)
            session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else
        {
            ArrayList<Menu> listMenu = this.ShowAllMenu();
            request.setAttribute("listaMenu", listMenu);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/mensa/showAllMenus.jsp");
            dispatcher.forward(request, response);
        }
    }

    /** Restituisce una lista con tutti i menu disponibili.
     * @post {@literal menu->select(m|m.available==true)}
     * @return lista con tutti i menu disponibili.
     */
    public ArrayList<Menu> ShowAllMenu()
    {
        ArrayList<Menu> menu= (ArrayList<Menu>) mdao.doRetrieveAllMenu();
        ArrayList<Menu> disponibili= new ArrayList<>();
        for(Menu x : menu)
        {
            if(x.isAvailable())
                disponibili.add(x);
        }
        return disponibili;
    }
}