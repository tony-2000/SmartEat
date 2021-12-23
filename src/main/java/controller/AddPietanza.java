package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AddPietanza", value = "/AddPietanza")
public class AddPietanza extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utenteSessione");
        Pietanza pietanza = new Pietanza();
        PietanzaDAOInterface pietanzadao = new PietanzaDAO();
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
            int numeroAcquisti = Integer.parseInt(request.getParameter("numeroAcquisti"));
            pietanza.setNome(nome);
            pietanza.setDescrizione(descrizione);
            pietanza.setTipo(tipo);
            pietanza.setIngredienti(ingredienti);
            pietanza.setImmagine(immagine);
            pietanza.setNumeroAcquisti(numeroAcquisti);
            this.addPietanza(pietanza);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminListPietanze.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    public void addPietanza(Pietanza pietanza)
    {
        PietanzaDAOInterface pietanzadao = new PietanzaDAO();
        pietanzadao.doSave(pietanza);
    }
}
