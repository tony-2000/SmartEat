package controller.GestioneMensa;

import model.mensa.Mensa;
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
import java.sql.Time;
import java.util.ArrayList;

/**
 * Classe che aggiorna le informazioni della mensa da parte dell'admin
 */
@WebServlet(name = "UpdateMensa", value = "/UpdateMensa")
public class UpdateMensa extends HttpServlet {

    /**
     * DAO di Mensa
     */
    private final MensaDAOInterface mensaDao;

    /**
     * Costruttore Vuoto
     */
    public UpdateMensa() {
        super();
        mensaDao=new MensaDAO();
    }

    /**Costruttore con parametri
     * @param mensaDao di Mensa
     */
    public UpdateMensa(MensaDAOInterface mensaDao) {
        super();
        this.mensaDao=mensaDao;
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
        else
        {
            String message;
            ArrayList<String> mensa= mensaDao.doRetrieveMensaByKey("mensa1");
            int posti = Integer.parseInt(request.getParameter("postiDisponibili"));
            Time apertura = Time.valueOf(request.getParameter("orarioApertura"));
            Time chiusura = Time.valueOf(request.getParameter("orarioChiusura"));
            if(apertura.toString().compareTo(chiusura.toString())>=0)
            {
                message="L'orario di apertura deve essere precedente a quello di chiusura";
                request.setAttribute("message",message);
                request.setAttribute("mensa",mensa);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMensaArea");
                dispatcher.forward(request, response);
            }
            else if(apertura.toString().compareTo("01:00:00")<=0)
            {
                message="L'orario di apertura non può essere precedente alle ore 01:00";
                request.setAttribute("message",message);
                request.setAttribute("mensa",mensa);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMensaArea");
                dispatcher.forward(request, response);
            }
            else if(chiusura.toString().compareTo("23:00:00")>=0)
            {
                message="L'orario di chiusura non può essere successivo alle ore 23:00";
                request.setAttribute("message",message);
                request.setAttribute("mensa",mensa);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMensaArea");
                dispatcher.forward(request, response);
            }
            else {
                boolean result = this.updateMensa(posti, apertura, chiusura);
                mensa = mensaDao.doRetrieveMensaByKey("mensa1");
                if (result)
                    message = "Le informazioni della mensa sono state modificate correttamente";
                else
                    message = "Le informazioni della mensa verranno modificate dopo l'orario di chiusura";
                request.setAttribute("message", message);
                request.setAttribute("mensa", mensa);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMensaArea");
                dispatcher.forward(request, response);
            }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }


    /**
     * Aggiorna le informazioni della mensa quando possibile
     * @pre {@literal posti!=null && apertura!=null && chiusura!=null}
     * @param posti posti disponibili
     * @param apertura orario di apertura
     * @param chiusura orario di chiusura
     * @return true se la mensa e' stata aggiornata all'istante, false seaggiornata successivamente
     */
    public boolean updateMensa(int posti,Time apertura,Time chiusura)
    {
        if (Mensa.isMensaConfig())
        {
            mensaDao.doUpdate("mensa1",posti,apertura,chiusura);
            return  true;
        }
        ArrayList<String> listupdateMensa= Mensa.getListUpdateMensa();
        listupdateMensa.add(0,String.valueOf(posti));
        listupdateMensa.add(1,apertura.toString());
        listupdateMensa.add(2,chiusura.toString());
        return false;
    }
}
