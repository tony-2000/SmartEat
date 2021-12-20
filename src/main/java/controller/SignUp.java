package controller;


import model.Check;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

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

    public void doGet(HttpServletRequest request, HttpServletResponse response){}


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
            Check.mailIsValid(mail);
            Check.residenzaIsValid(residenza);
            Check.passwordIsValid(password,passwordCheck);
        }
        catch (Exception e)
        {
             error=e.getMessage();
             return error;
        }
        Utente user=new Utente();
        UtenteDAO dao=new UtenteDAO();
        user.setCF(CF);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setSesso(gender);
        user.setDataDiNascita(nascita);
        user.setLuogoDiNascita(luogo);
        user.setEmail(mail);
        user.setResidenza(residenza);
        user.setPasswordHash(password);
        user.setAmministratore(false);
        user.setAccepted(false);
        dao.doSave(user);
        return error;
    }
}