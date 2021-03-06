package controller.ProcessoDiAcquisto;

import model.mensa.Mensa;
import model.menu.Menu;
import model.menu.MenuDAO;
import model.menu.MenuDAOInterface;
import model.tessera.Tessera;
import model.tessera.TesseraDAO;
import model.tessera.TesseraDAOInterface;
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
     */
    public toBuyMenu(TesseraDAOInterface tdao,MenuDAOInterface mdao) {
        super();
        this.tdao = tdao;
        this.mdao=mdao;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int codiceMenu = Integer.parseInt(request.getParameter("codiceMenu"));
        Menu menu=mdao.doRetrieveMenuByKey(codiceMenu);
        request.setAttribute("menu",menu);
        HttpSession session=request.getSession();
        Utente user= (Utente) session.getAttribute("utenteSessione");
        Tessera tessera=tdao.doRetrieveTesseraByKey(user.getCF());
        request.setAttribute("tessera",tessera);
        request.setAttribute("postiVuoti", Mensa.getPostiVuoti());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/mensa/buyMenu.jsp");
        dispatcher.forward(request, response);
    }
}