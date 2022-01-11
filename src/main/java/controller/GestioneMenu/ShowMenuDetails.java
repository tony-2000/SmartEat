package controller.GestioneMenu;

import model.menu.Menu;
import model.menu.MenuDAO;
import model.menu.MenuDAOInterface;
import model.utente.RuoloUtente;
import model.utente.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Classe che implementa la visualizzazione di tutte le informazioni di un menu con vista da Admin.
 */
@WebServlet(name = "ShowMenuDetails", value = "/ShowMenuDetails")
public class ShowMenuDetails extends HttpServlet {

    /**
     * DAO di Menu
     */
    private final MenuDAOInterface mdao;


    /**
     * Costruttore Vuoto
     */
    public ShowMenuDetails() {
        super();
        mdao=new MenuDAO();
    }
    /**Costruttore con parametri
     * @param mdao DAO di Menu
     */
    public ShowMenuDetails(MenuDAOInterface mdao) {
        super();
        this.mdao=mdao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else {
            RuoloUtente ruoloUtente = u.isAmministratore();
            if (!ruoloUtente.isAdmin())
                response.sendRedirect(request.getContextPath() + "/toHome");
            else {
                int codiceMenu = Integer.parseInt(request.getParameter("codiceMenu"));
                Menu menu = this.getMenu(codiceMenu);
                request.setAttribute("menu", menu);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/showMenuDetails.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /** Restituisce un menu con chiave specificata
     * @pre {@literal codiceMenu!=null}
     * @post {@literal menu->select(m|m.codiceMenu==codiceMenu)}
     * @param codiceMenu codice del menu da selezionare
     * @return menu con codice specificato
     */
    public Menu getMenu(int codiceMenu)
    {
        return mdao.doRetrieveMenuByKey(codiceMenu);
    }
}
