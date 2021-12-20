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


@WebServlet(name="Login", value="/Login")
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session=request.getSession();
        String resp="/Home.jsp";
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        Utente user=this.login(mail,password);
        if(user==null)
        {
            String err="Dati utente scorretti";
            resp="Login.jsp";
            request.setAttribute("logError",err);
        }
        else
            session.setAttribute("utenteSessione",user);
        MensaDAO mensadao=new MensaDAO();
        Mensa mensa=mensadao.doRetrieveMensaByKey("mensa1");
        request.setAttribute("mensa",mensa);
        RequestDispatcher dispatcher = request.getRequestDispatcher(resp);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){}

    public Utente login(String mail, String password)
    {
        try
        {
            Check.mailIsValid(mail);
            Check.passwordIsValid(password,password);
        }
        catch (Exception e)
        {
            return new Utente();
        }
        UtenteDAO dao= new UtenteDAO();
        return dao.doRetrieveUtenteByEmailPassword(mail,password);
    }
}