package controller;


import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Classe che permette all'utente di inviare una richiesta di registrazione.
 */
@WebServlet(name="SignUp", value="/SignUp")
public class SignUp extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url="login.jsp";
        String CF = request.getParameter("CF");
        String nome = request.getParameter("nome_utente");
        String cognome = request.getParameter("cognome");
        String g = request.getParameter("gender");
        char gender=g.charAt(0);
        Date nascita = Date.valueOf((request.getParameter("dataDiNascita")));
        String luogo = request.getParameter("luogoDiNascita");
        String mail = request.getParameter("mail");
        String residenza = request.getParameter("residenza");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");
        String message=this.registrazione(CF,nome,cognome,gender,nascita,luogo,mail,residenza,password,passwordCheck);
        if(!message.equals(""))
            url="signUp.jsp";
        else message="Richiesta di registrazione avvenuta con successo";
        request.setAttribute("message",message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /** Metodo che o salva una richiesta di registrazione in database e restituisce una stringa di conferma,
     * oppure restituisce una stringa di errore nel caso di input errato.
     * @param CF Codice fiscale dell'utente
     * @param nome Nome dell'utente
     * @param cognome Cognome dell'utente
     * @param gender sesso dell'utente
     * @param nascita data di nascita dell'utente
     * @param luogo luogo di nascita dell'utente
     * @param mail mail dell'utente
     * @param residenza residenza dell'utente
     * @param password password dell'utente
     * @param passwordCheck campo Conferma password
     * @return Stringa di conferma di avvenuto salvataggio o di errore.
     */
    public String registrazione(String CF, String nome, String cognome, char gender, Date nascita,String luogo, String mail, String residenza, String password, String passwordCheck)
    {
        String error="";
        try
        {
            Check.CFIsValid(CF);
            Check.nomeIsValid(nome);
            Check.cognomeIsValid(cognome);
            Check.sessoIsValid(gender);
            Check.nascitaIsValid(nascita);
            Check.luogoDNIsValid(luogo);
            Check.mailIsValidReg(mail);
            Check.residenzaIsValid(residenza);
            Check.passwordIsValid(password,passwordCheck);
        }
        catch (Exception e)
        {
             error=e.getMessage();
             return error;
        }
        Utente user=new Utente();
        UtenteDAOInterface dao=new UtenteDAO();
        user.setCF(CF);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setSesso(gender);
        user.setDataDiNascita(nascita);
        user.setLuogoDiNascita(luogo);
        user.setEmail(mail);
        user.setResidenza(residenza);
        user.setPasswordHash(password);
        RuoloUtente standard= new RuoloStandard();
        user.setAmministratore(standard);
        user.setAccepted(false);
        dao.doSave(user);
        return error;
    }
}