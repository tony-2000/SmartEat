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

/**
 * Classe che implementa la visualizzazione di tutte le informazioni di una pietanza con vista da Admin.
 */
@WebServlet(name = "ShowPietanzaDetails", value = "/ShowPietanzaDetails")
public class ShowPietanzaDetails extends HttpServlet {

    /**
     * DAO di Pietanza
     */
    private final PietanzaDAOInterface pdao;


    /**
     * Costruttore Vuoto
     */
    public ShowPietanzaDetails() {
        super();
        pdao=new PietanzaDAO();
    }
    /**Costruttore con parametri
     * @param pdao DAO di Pietanza
     */
    public ShowPietanzaDetails(PietanzaDAOInterface pdao) {
        super();
        this.pdao=pdao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String nomePietanza = request.getParameter("nomePietanza");
            Pietanza pietanza = this.getPietanza(nomePietanza);
            request.setAttribute("pietanza", pietanza);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/showPietanzaDetails.jsp");
            dispatcher.forward(request, response);
        }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);}

    /** Restituisce una pietanza col nome specificato
     * @pre {@literal pietanza!=null}
     * @post {@literal pietanza->select(p|p.nome==pietanza)}
     * @param pietanza nome della pietanza da eliminare
     * @return Pietanza con nome specificato
     */
    public Pietanza getPietanza(String pietanza)
    {
        return pdao.doRetrievePietanzaByKey(pietanza);
    }
}
