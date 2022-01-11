package controller.GestionePietanze;

import model.pietanza.Pietanza;
import model.pietanza.PietanzaDAO;
import model.pietanza.PietanzaDAOInterface;
import model.utente.RuoloUtente;
import model.utente.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe che implementa la parte dell'area admin che riguarda le pietanze.
 */
@WebServlet(name = "AdminPietanzeArea", value = "/AdminPietanzeArea")
public class AdminPietanzeArea extends HttpServlet {

    /**
     * DAO di Pietanza
     */
    private final PietanzaDAOInterface pdao;


    /**
     * Costruttore vuoto
     */
    public AdminPietanzeArea() {
        super();
        pdao = new PietanzaDAO();
    }

    /**Costruttore con parametri
     * @param pdao DAO di Pietanza
     */
    public AdminPietanzeArea(PietanzaDAOInterface pdao) {
        super();
        this.pdao = pdao;
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else {
            RuoloUtente ruoloUtente = u.isAmministratore();
            if (!ruoloUtente.isAdmin())
                response.sendRedirect(request.getContextPath() + "/toHome");
            else {
                ArrayList<Pietanza> pietanzas = AdminShowPietanze();
                request.setAttribute("listaPietanze", pietanzas);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminListPietanze.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request,response);}

    /** Restituisce la lista con tutte le pietanze.
     * @post {@literal pietanza->asSet()}
     * @return lista con tutte le pietanze.
     */
    public ArrayList<Pietanza> AdminShowPietanze()
    {
        return (ArrayList<Pietanza>) pdao.doRetrieveAllPietanza();
    }
}

