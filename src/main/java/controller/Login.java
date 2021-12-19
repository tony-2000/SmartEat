package controller;

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
        String resp="/Home.jsp";
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        UtenteDAO dao= new UtenteDAO();
        Utente user=dao.doRetrieveUtenteByEmailPassword(mail,password);
        HttpSession session=request.getSession();
        if(user==null)
        {
            String err="Dati utente sbagliati.";
            resp="Login.jsp";
            request.setAttribute("logError",err);
        }
        else
            session.setAttribute("utenteSessione",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher(resp);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}