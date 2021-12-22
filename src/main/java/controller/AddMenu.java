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

/**
 * Classe che
 */
@WebServlet(name="AddMenu", value="/AddMenu")
public class AddMenu extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utenteSessione");
        MenuDAOInterface menudao = new MenuDAO();
        Menu menu = new Menu();
        RuoloUtente ruoloUtente = u.isAmministratore();
        if (!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath() + "/toHome");
        else {
            String nome = request.getParameter("nome");
            String primo = request.getParameter("primo");
            String secondo = request.getParameter("secondo");
            String dessert = request.getParameter("dessert");
            String descrizione = request.getParameter("descrizione");
            String immagine = request.getParameter("immagine");
            float prezzo = Float.parseFloat(request.getParameter("prezzo"));
            menu.setNome(nome);
            menu.setPrimo(primo);
            menu.setSecondo(secondo);
            menu.setDessert(dessert);
            menu.setDescrizione(descrizione);
            menu.setImmagine(immagine);
            menu.setPrezzo(prezzo);
            menu.setAvailable(false);
            try
            {
                this.addMenu(menu,menudao);
            }
            catch (InterruptedException ignored)
            {}

            /*
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/admin/adminListMenu.jsp");
            dispatcher.forward(request, response);
            */
            response.sendRedirect(request.getContextPath() + "/AdminMenuArea");
        }
    }
        public void addMenu(Menu menu, MenuDAOInterface menudao) throws InterruptedException {
            if(Mensa.isMensaConfig())
            {
                menudao.doSave(menu);
                System.out.println("Aggiunta effettuata");
            }
            else
            {
                ThreadAddMenu tam=new ThreadAddMenu();
                tam.start();
                tam.join();
                menudao.doSave(menu);
                System.out.println("Aggiunta effettuata");
            }
        }
}