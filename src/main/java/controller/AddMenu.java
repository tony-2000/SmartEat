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
 * Classe che aggiunge menu al Sistema da parte dell'admin
 */
@WebServlet(name = "AddMenu", value = "/AddMenu")
public class AddMenu extends HttpServlet {

    private MenuDAOInterface menudao;

    public AddMenu() {
        super();
        menudao = new MenuDAO();

    }

    public AddMenu(MenuDAOInterface menudao) {
        super();
        this.menudao = menudao;
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utenteSessione");
        Menu menu = new Menu();

        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        assert u != null;
        RuoloUtente ruoloUtente = u.isAmministratore();
        if (!ruoloUtente.isAdmin())
            response.sendRedirect(request.getContextPath() + "/toHome");
        else {
            String nome = request.getParameter("nome");
            System.out.println(nome); //DEBUG
            String primo = request.getParameter("primo");
            String secondo = request.getParameter("secondo");
            String dessert = request.getParameter("dessert");
            String descrizione = request.getParameter("descrizione");
            System.out.println(descrizione); //DEBUG
            String immagine = request.getParameter("immagine");
            float prezzo = Float.parseFloat(request.getParameter("prezzo"));
            menu.setNome(nome);
            menu.setPrimo(primo);
            menu.setSecondo(secondo);
            menu.setDessert(dessert);
            menu.setDescrizione(descrizione);
            menu.setImmagine(immagine);
            menu.setPrezzo(prezzo);
            menu.setAvailable(true);
            boolean result=this.addMenu(menu);
            String message;
            if(result)
                message="Il menu è stato aggiunto correttamente";
            else
                message="Il menu verrà aggiunto dopo l'orario di chiusura";
            request.setAttribute("message",message);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminMenuArea");
            dispatcher.forward(request, response);

        }
    }

    /**Aggiunge un menu quando possibile
     * @pre  {@literal menu.nome!=null && menu.primo!=null && menu.secondo!=null && menu.dessert!=null
     * && menu.descrizione!=null && menu.immagine!=null && menu.prezzo!=null && menu.available!=null
     * && !(menu->includes(menu))}
     * @post {@literal menu->includes(menu)}
     * @param menu menu da aggiungere
     * @return true se il menu e' aggiunto all'istante, false se aggiunto successivamente
     */
    public boolean addMenu(Menu menu)
    {
        if (Mensa.isMensaConfig())
        {
            menudao.doSave(menu);
            return  true;
        }
        ArrayList<Menu> listAddMenu= Menu.getListAddMenu();
        listAddMenu.add(menu);
        return false;
    }
}
