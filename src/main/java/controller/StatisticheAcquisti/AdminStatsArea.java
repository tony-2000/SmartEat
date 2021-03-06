package controller.StatisticheAcquisti;

import model.menu.Menu;
import model.menu.MenuDAO;
import model.menu.MenuDAOInterface;
import model.pietanza.Pietanza;
import model.pietanza.PietanzaDAO;
import model.pietanza.PietanzaDAOInterface;
import model.utente.RuoloUtente;
import model.utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che implementa la visualizzazione delle statistiche.
 */
@WebServlet(name="AdminStatsArea", value="/AdminStatsArea")
public class AdminStatsArea extends HttpServlet
{
    /**
     *DAO di Pietanza
     */
    private final PietanzaDAOInterface pdao;

    /**
     *DAO di Menu
     */
    private final MenuDAOInterface mdao;

    /**
     *Costruttore vuoto
     */
    public AdminStatsArea() {
        super();
        pdao = new PietanzaDAO();
        mdao=new MenuDAO();
    }

    /**Costruttore con parametri
     * @param pdao DAO di Pietanza
     * @param mdao DAO di Menu
     */
    public AdminStatsArea(PietanzaDAOInterface pdao,MenuDAOInterface mdao) {
        super();
        this.pdao = pdao;
        this.mdao=mdao;
    }


    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else{
        RuoloUtente ruoloUtente = u.isAmministratore();
        if(!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath()+"/toHome");
        else {
            List<Pietanza> pietanzas = pdao.doRetrieveAllPietanza();
            List<Integer> statsMenu = this.mostraStatistiche(pietanzas);
            request.setAttribute("pietanze", pietanzas);
            request.setAttribute("statsMenu", statsMenu);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminStatsArea.jsp");
            dispatcher.forward(request, response);
        }
        }
    }


    /** Restituisce lista di interi che rappresenta il numero di menu in cui, nella lista di input, la pietanza al numero corrispondente ?? disponibile.
     * @param array array di tutte le pietanze
     * @return lista di interi che rappresenta il numero di menu in cui, nella lista di input, la pietanza al numero corrispondente ?? disponibile.
     */
    public List<Integer> mostraStatistiche(List<Pietanza> array) {
        List<Menu> menus = mdao.doRetrieveAllMenu();
        List<Integer> counter = new ArrayList<>();
        int i = 0;

        for (Pietanza x : array) {
            int temp = 0;
            for (Menu y : menus) {
                if (y.getPrimo().equals(x.getNome()))
                    temp++;
                else if (y.getSecondo().equals(x.getNome()))
                    temp++;
                else if (y.getDessert().equals(x.getNome()))
                    temp++;
            }
            counter.add(i, temp);
            i++;
        }
        return counter;
    }
}