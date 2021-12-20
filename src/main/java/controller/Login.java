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
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name="Login", value="/Login")
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        String resp="/Home.jsp";
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String error = null;
        Utente user=this.login(mail,password,error);
        if(error==null)  //corretto
        {
            error="Dati utente sbagliati.";
            resp="Login.jsp";
            request.setAttribute("logError",error);
        }
        else if(user==null)
        {
            String err="Dati utente non trovati.";
            resp="Login.jsp";
            request.setAttribute("logError",err);
        }
        else
            session.setAttribute("utenteSessione",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher(resp);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){}

    public Utente login(String mail, String password,String error)
    {
        try
        {
            Check.mailIsValid(mail);
            Check.passwordIsValid(password,password);
        }
        catch (Exception e)
        {
            error=e.getMessage();
        }
        UtenteDAO dao= new UtenteDAO();
        return dao.doRetrieveUtenteByEmailPassword(mail,password);
    }
}