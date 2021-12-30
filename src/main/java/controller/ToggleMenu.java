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

//un bottone in ShowMenuDetails che cliccato chiama la servlet e cambia lo stato del menu.Reindirizza alla stessa pagina.

/**
 * Classe che cambia lo stato di disponibilita' di un menu da parte dell'admin
 */

@WebServlet(name = "ToggleMenu", value = "/ToggleMenu")
public class ToggleMenu extends HttpServlet {

    private  MenuDAOInterface menudao;

    public ToggleMenu() {
        super();
        menudao=new MenuDAO();
    }

    public ToggleMenu(MenuDAOInterface menudao) {
        super();
        this.menudao=menudao;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        assert u != null;
        RuoloUtente ruoloUtente = u.isAmministratore();
        if (!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath() + "/toHome");
        else {
            int codiceMenu = Integer.parseInt(request.getParameter("codiceMenu"));
            request.setAttribute("codiceMenu",codiceMenu);
            boolean result=this.toggleMenu(codiceMenu);
            String message;
            if(result)
                message="La disponibilita' del menu è stata cambiata";
            else
                message="La disponibilita' del menu verra' aggiornata dopo l'orario di chiusura";
            request.setAttribute("message",message);
            //dispatcher
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/showMenuDetails.jsp");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminMenuArea");
            dispatcher.forward(request, response);
        }
    }

    /**Cambia la disponibilita' di un menu quando possibile
     * @pre {@literal codiceMenu!=null}
     * @param codiceMenu codice del menu a cui cambiare la disponibilita'
     * @return true se la disponibilita' e' stata cambiata all'istante, false se cambiato successivamente
     */
    public boolean toggleMenu(int codiceMenu)
    {
        boolean available=menudao.doRetrieveMenuByKey(codiceMenu).isAvailable();
        boolean change=!available;
        if (Mensa.isMensaConfig())
        {
            menudao.doUpdateAvailable(codiceMenu,change);
            return  true;
        }
        ArrayList<Integer> listToggleMenu= Menu.getListToggleMenu();
        listToggleMenu.add(codiceMenu);
        return false;
    }
}
