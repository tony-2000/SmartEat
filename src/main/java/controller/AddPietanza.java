package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe che aggiunge una pietanza al database da parte dell'admin.
 */
@WebServlet(name = "AddPietanza", value = "/AddPietanza")
@MultipartConfig
public class AddPietanza extends HttpServlet {

    /**
     * DAO di Pietanza
     */
    private final PietanzaDAOInterface pietanzadao;


    /**
     * Costruttore Vuoto
     */
    public AddPietanza() {
        super();
        pietanzadao = new PietanzaDAO();
    }

    /**Costruttore con parametri
     * @param pietanzadao DAO di Pietanza
     */
    public AddPietanza(PietanzaDAOInterface pietanzadao) {
        super();
        this.pietanzadao = pietanzadao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        Utente u = (Utente) session.getAttribute("utenteSessione");
        Pietanza pietanza = new Pietanza();
        if(u==null)
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        else{
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
            String descrizione = request.getParameter("descrizione");
            char tipo = request.getParameter("tipo").charAt(0);
            String ingredienti = request.getParameter("ingredienti");

            pietanza.setNome(nome);
            pietanza.setDescrizione(descrizione);
            pietanza.setTipo(tipo);
            pietanza.setIngredienti(ingredienti);
            pietanza.setImmagine(nameImage);
            pietanza.setNumeroAcquisti(0);
            this.addPietanza(pietanza);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminPietanzeArea");
            dispatcher.forward(request, response);
        }
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
