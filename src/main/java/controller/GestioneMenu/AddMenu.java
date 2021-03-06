package controller.GestioneMenu;

import model.mensa.Mensa;
import model.menu.Menu;
import model.menu.MenuDAO;
import model.menu.MenuDAOInterface;
import model.utente.RuoloUtente;
import model.utente.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Classe che aggiunge menu al Sistema da parte dell'admin
 */
@WebServlet(name = "AddMenu", value = "/AddMenu")
@MultipartConfig
public class AddMenu extends HttpServlet {

    /**
     * DAO di Menu
     */
    private final MenuDAOInterface menudao;


    /**
     * Costruttore Vuoto
     */
    public AddMenu() {
        super();
        menudao = new MenuDAO();
    }

    /**
     * Costruttore con parametri
     *
     * @param menudao DAO di Menu
     */
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

        if (u == null)
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        else {
            RuoloUtente ruoloUtente = u.isAmministratore();
            if (!ruoloUtente.isAdmin())
                response.sendRedirect(request.getContextPath() + "/toHome");
            else {
                Part image = request.getPart("image");
                String nameImage = System.currentTimeMillis() + Paths.get(image.getSubmittedFileName()).getFileName().toString();
                String uploadPath = System.getenv("CATALINA_HOME") + File.separator + "uploads" + File.separator;
                InputStream stream = image.getInputStream();
                String linkImmagine = uploadPath + nameImage;
                File file = new File(linkImmagine);
                try {
                    Files.copy(stream, file.toPath());
                } catch (FileAlreadyExistsException e) {
                    /* do nothing */
                }

                String nome = request.getParameter("nome");
                String primo = request.getParameter("primo");
                String secondo = request.getParameter("secondo");
                String dessert = request.getParameter("dessert");
                String descrizione = request.getParameter("descrizione");
                float prezzo = Float.parseFloat(request.getParameter("prezzo"));
                menu.setNome(nome);
                menu.setPrimo(primo);
                menu.setSecondo(secondo);
                menu.setDessert(dessert);
                menu.setDescrizione(descrizione);
                menu.setImmagine(nameImage);
                menu.setPrezzo(prezzo);
                menu.setAvailable(true);
                boolean result = this.addMenu(menu);
                String message;
                if (result)
                    message = "Il menu ?? stato aggiunto correttamente";
                else
                    message = "Il menu verr?? aggiunto dopo l'orario di chiusura";
                request.setAttribute("message", message);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMenuArea");
                dispatcher.forward(request, response);
            }
        }
    }

    /**
     * Aggiunge un menu quando possibile
     *
     * @param menu menu da aggiungere
     * @return true se il menu e' aggiunto all'istante, false se aggiunto successivamente
     * @pre {@literal menu.nome!=null && menu.primo!=null && menu.secondo!=null && menu.dessert!=null
     * && menu.descrizione!=null && menu.immagine!=null && menu.prezzo!=null && menu.available!=null
     * && !(menu->includes(menu))}
     * @post {@literal menu->includes(menu)}
     */
    public boolean addMenu(Menu menu) {
        if (Mensa.isMensaConfig()) {
            menudao.doSave(menu);
            return true;
        }
        ArrayList<Menu> listAddMenu = Menu.getListAddMenu();
        listAddMenu.add(menu);
        return false;
    }
}
