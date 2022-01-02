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
 * Classe che elimina un menu da parte dell'admin
 */
@WebServlet(name = "DeleteMenu", value = "/DeleteMenu")
public class DeleteMenu extends HttpServlet
{

    /**
     * DAO di Menu
     */
    private final MenuDAOInterface menudao;

    /**
     * Sessione in corso
     */
    private HttpSession session;

    /**
     * Costruttore vuoto
     */
    public DeleteMenu() {
        super();
        menudao=new MenuDAO();
    }

    /**Costruttore con parametri
     * @param menudao DAO di Menu
     * @param session Sessione
     */
    public DeleteMenu(MenuDAOInterface menudao,HttpSession session) {
        super();
        this.menudao=menudao;
        this.session=session;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(session==null)
            session=request.getSession();
        Utente u = (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else{
        RuoloUtente ruoloUtente = u.isAmministratore();
        if (!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath() + "/toHome");
        else {
            int codiceMenu = Integer.parseInt(request.getParameter("codiceMenu"));
            boolean result = this.deleteMenu(codiceMenu);
            String message;
            if (result)
                message = "Il menu è stato eliminato correttamente";
            else
                message = "Il menu verrà eliminato dopo l'orario di chiusura";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminMenuArea");
            dispatcher.forward(request, response);
        }
        }
    }

    /**
     * Elimina un menu quando possibile
     * @pre {@literal codiceMenu!=null}
     * @post {@literal !menu->exist(m|m.codiceMenu!=codiceMenu)}
     * @param codiceMenu codice del menu da eliminare
     * @return true se il menu' e' stato eliminato all'istante, false se elimiato successivamente
     */
    public boolean deleteMenu(int codiceMenu)
    {
        if (Mensa.isMensaConfig()) {
            menudao.doDelete(codiceMenu);
            return true;
        }
        ArrayList<Integer> listDeleteMenu = Menu.getListDeleteMenu();
        listDeleteMenu.add(codiceMenu);
        return false;
    }
}