package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe che implementa la parte dell'area admin che riguarda i menu.
 */
@WebServlet(name = "AdminMenuArea", value = "/AdminMenuArea")
public class AdminMenuArea extends HttpServlet {

    private final MenuDAOInterface mdao;
    private HttpSession session;

    public AdminMenuArea() {
        super();
        mdao = new MenuDAO();
    }

    public AdminMenuArea(MenuDAOInterface mdao,HttpSession session) {
        super();
        this.mdao = mdao;
        this.session=session;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(session==null)
            session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else {
            RuoloUtente ruoloUtente = u.isAmministratore();
            if (!ruoloUtente.isAdmin())
                response.sendRedirect(request.getContextPath() + "/toHome");
            ArrayList<Menu> menus = AdminShowMenu();
            request.setAttribute("listaMenu", menus);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminListMenu.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /** Restituisce la lista con tutti i menu.
     * @post {@literal menu->asSet()}
     * @return lista con tutti i menu
     */
    public ArrayList<Menu> AdminShowMenu()
    {
        return (ArrayList<Menu>) mdao.doRetrieveAllMenu();
    }
}
