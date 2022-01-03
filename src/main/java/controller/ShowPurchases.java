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
 * Classe che mostra tutti gli acquisti effettuati da un utente.
 */
@WebServlet(name="ShowPurchases", value="/ShowPurchases")
public class ShowPurchases extends HttpServlet
{
    /**
     * DAO di Menu
     */
    private final MenuDAOInterface menudao;

    /**
     * DAO di Acquisto
     */
    private final AcquistoDAOInterface acquistodao;


    /**
     * Costruttore Vuoto
     */
    public ShowPurchases() {
        super();
        acquistodao=new AcquistoDAO();
        menudao=new MenuDAO();
    }

    /**Costruttore con parametri
     * @param acquistodao DAO di Acquisto
     */
    public ShowPurchases(AcquistoDAOInterface acquistodao,MenuDAOInterface menudao) {
        super();
        this.acquistodao=acquistodao;
        this.menudao=menudao;
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        Utente user= (Utente) session.getAttribute("utenteSessione");
        if(user==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else {
            ArrayList<Acquisto> acquisti = this.showAllAcquisti(user);
            request.setAttribute("listaAcquisti", acquisti);
            ArrayList<Menu> menu=new ArrayList<>();
            for(Acquisto x: acquisti)
            {
                menu.add(menudao.doRetrieveMenuByKey(x.getCodiceMenu()));
            }
            request.setAttribute("listaMenu",menu);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/mensa/showPurchases.jsp");
            dispatcher.forward(request, response);
        }
    }

    /** Restituisce tutti gli acquisti effettuati da un utente.
     * @pre {@literal user.CF!=null}
     * @post {@literal Utente (empty) || utente->select(u|u.codiceFiscale==CF)}}
     * @param user Utente
     * @return lista con tutti gli acquisti effettuati dall'utente
     */
    public ArrayList<Acquisto> showAllAcquisti(Utente user)
    {
        return (ArrayList<Acquisto>) acquistodao.doRetrieveAllAcquistoByCF(user.getCF());
    }

}