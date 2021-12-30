package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Classe che aggiunge una pietanza al database da parte dell'admin.
 */
@WebServlet(name = "AddPietanza", value = "/AddPietanza")
public class AddPietanza extends HttpServlet {

    private PietanzaDAOInterface pietanzadao;

    public AddPietanza() {
        super();
        pietanzadao = new PietanzaDAO();
    }


    public AddPietanza(PietanzaDAOInterface pietanzadao) {
        super();
        this.pietanzadao = pietanzadao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utenteSessione");
        Pietanza pietanza = new Pietanza();
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        assert u != null;
        RuoloUtente ruoloUtente = u.isAmministratore();
        if (!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath() + "/toHome");
        else
        {
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            char tipo = request.getParameter("tipo").charAt(0);
            String ingredienti = request.getParameter("ingredienti");
            String immagine = request.getParameter("immagine");
            //int numeroAcquisti = Integer.parseInt(request.getParameter("numeroAcquisti"));
            pietanza.setNome(nome);
            pietanza.setDescrizione(descrizione);
            pietanza.setTipo(tipo);
            pietanza.setIngredienti(ingredienti);
            pietanza.setImmagine(immagine);
            pietanza.setNumeroAcquisti(0);
            this.addPietanza(pietanza);

            //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminListPietanze.jsp");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminPietanzeArea");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**Aggiunge una pietanza al database.
     * @pre {@literal pietanza.nome!=null && pietanza.descrizione!=null && pietanza.tipo!=null &&
     * pietanza.ingredienti!=null && pietanza.immagine!=null && pietanza.numeroAcquisti!=null
     * && !(pietanza->includes(pietanza))}
     * @post {@literal pietanza->includes(pietanza)}
     * @param pietanza pietanza da aggiungere al database
     */
    public void addPietanza(Pietanza pietanza)
    {
        pietanzadao.doSave(pietanza);
    }
}
