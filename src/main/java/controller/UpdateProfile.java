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
import java.sql.Date;

/**
 * Classe che implementa l'aggiornamento dei dati utente.
 */
@WebServlet(name="UpdateProfile", value="/UpdateProfile")
public class UpdateProfile extends HttpServlet {

    private final UtenteDAOInterface udao;
    private final TesseraDAOInterface tesseradao;
    private HttpSession session;

    public UpdateProfile() {
        super();
        udao=new UtenteDAO();
        tesseradao = new TesseraDAO();

    }

    public UpdateProfile(TesseraDAOInterface tesseradao,UtenteDAOInterface udao,HttpSession session) {
        super();
        this.tesseradao = tesseradao;
        this.udao=udao;
        this.session=session;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(session==null)
            session=request.getSession();
        String url="/WEB-INF/results/utente/showProfile.jsp";
        Utente oldUser= (Utente) session.getAttribute("utenteSessione");
        String nome = request.getParameter("nome_utente");
        String cognome = request.getParameter("cognome");
        String g = request.getParameter("gender");
        char gender=g.charAt(0);
        Date nascita = Date.valueOf((request.getParameter("dataDiNascita")));
        String luogo = request.getParameter("luogoDiNascita");
        String residenza = request.getParameter("residenza");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");
        String message=this.updateProfile(oldUser, nome,cognome,gender,nascita,luogo,residenza,password,passwordCheck);
        if(!message.equals(""))
            url="/WEB-INF/results/utente/updateProfile.jsp";
        else message="Aggiornamento dei dati avvenuto con successo";
        request.setAttribute("message",message);
        Utente user= udao.doRetrieveUtenteByKey(oldUser.getCF());
        session.setAttribute("utenteSessione",user);
        Tessera tessera=tesseradao.doRetrieveTesseraByKey(oldUser.getCF());
        request.setAttribute("tessera",tessera);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /** Metodo che aggiorna i dati del profilo utente nel database e restituisce una stringa di conferma, oppure restituisce solo una stringa di errore
     * @param oldUser Dati dell'utente da aggiornare
     * @param nome Nome dell'utente
     * @param cognome Cognome dell'utente
     * @param gender sesso dell'utente
     * @param nascita data di nascita dell'utente
     * @param luogo luogo di nascita dell'utente
     * @param residenza residenza dell'utente
     * @param password password dell'utente
     * @param passwordCheck campo Conferma password
     * @return Stringa di conferma di avvenuto salvataggio o di errore.
     */
    public String updateProfile(Utente oldUser,String nome,String cognome,char gender, Date nascita,String luogo,
                                String residenza,String password,String passwordCheck)
    {
        String error="";
        Esito res;

            res=Check.nomeIsValid(nome);
            if (!res.isValido())
            {
                error= res.getMessage();
                return error;
            }
            res=Check.cognomeIsValid(cognome);
            if (!res.isValido())
            {
                error= res.getMessage();
                return error;
            }
            res=Check.sessoIsValid(gender);
            if (!res.isValido())
            {
                error= res.getMessage();
                return error;
            }
            res=Check.nascitaIsValid(nascita);
            if (!res.isValido())
            {
                error= res.getMessage();
                return error;
            }
            res=Check.luogoDNIsValid(luogo);
            if (!res.isValido())
            {
                error= res.getMessage();
                return error;
            }
            res=Check.residenzaIsValid(residenza);
            if (!res.isValido())
            {
                error= res.getMessage();
                return error;
            }
            res=Check.passwordIsValid(password,passwordCheck);
            if (!res.isValido())
            {
                error= res.getMessage();
                return error;
            }

           Utente user = new Utente();
           user.setCF(oldUser.getCF());
           user.setNome(nome);
           user.setCognome(cognome);
           user.setSesso(gender);
           user.setDataDiNascita(nascita);
           user.setLuogoDiNascita(luogo);
           user.setEmail(oldUser.getEmail());
           user.setResidenza(residenza);
           user.setPasswordHash(password);
           user.setAmministratore(oldUser.isAmministratore());
           user.setAccepted(oldUser.isAccepted());
           udao.doUpdateUtenteInfo(user);
           res.setValido(true);
           return error;
    }
}
