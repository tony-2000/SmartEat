package controller.GestioneMensa;
import model.mensa.MensaDAO;
import model.mensa.MensaDAOInterface;
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

/**
 * Classe che porta all'Area Admin dedicata alla mensa
 */
@WebServlet(name = "AdminMensaArea", value = "/AdminMensaArea")
public class AdminMensaArea extends HttpServlet {

    /**
     * DAO in Mensa
     */
    private final MensaDAOInterface mensadao;


    /**
     * Costruttore Vuoto
     */
    public AdminMensaArea() {
        super();
        mensadao = new MensaDAO();
    }

    /**Costruttore con parametri
     * @param mensadao DAO di Mensa
     */
    public AdminMensaArea(MensaDAOInterface mensadao) {
        super();
        this.mensadao = mensadao;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        Utente u= (Utente) session.getAttribute("utenteSessione");
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else{
        RuoloUtente ruoloUtente = u.isAmministratore();
        if(!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath()+"/toHome");
        else {
            ArrayList<String> mensa = this.adminMensa();
            request.setAttribute("mensa", mensa);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminMensaArea.jsp");
            dispatcher.forward(request, response);
        }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    /**
     *Restituisce le informazioni relative alla mensa
     * @return lista informazioni della mensa
     */
    public ArrayList<String> adminMensa()
    {
        return mensadao.doRetrieveMensaByKey("mensa1");
    }
}
