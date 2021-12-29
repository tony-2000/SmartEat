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
import java.sql.Time;
import java.util.ArrayList;

/**
 * Classe che implementa il login al sistema.
 */
@WebServlet(name="Login", value="/Login")
public class Login extends HttpServlet {

    UtenteDAOInterface dao;

    public Login() {
        super();
        dao= new UtenteDAO();
    }

    public Login(UtenteDAOInterface dao){
        super();
        this.dao=dao;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String[] strings=new String[2];
        HttpSession session=request.getSession();
        String resp="/WEB-INF/results/mensa/home.jsp";
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        Utente user=this.login(mail,password, strings);

        if(user.getCF()==null)
        {
            String err="Dati utente scorretti";
                    if(strings[1]!=null)
                        err=err+": "+strings[1];
            resp="login.jsp";
            request.setAttribute("logError",err);
        }
        else if(!(strings[0]==null))
        {
            resp="login.jsp";
            request.setAttribute("logError",strings[0]);
        }
        else
            session.setAttribute("utenteSessione",user);
        MensaDAOInterface mensadao=new MensaDAO();
        ArrayList<String> mensa=mensadao.doRetrieveMensaByKey("mensa1");
        session.setAttribute("nomeMensa",mensa.get(0));
        session.setAttribute("postiMensa",Integer.valueOf(mensa.get(1)));
        session.setAttribute("aperturaMensa", Time.valueOf(mensa.get(2)));
        session.setAttribute("chiusuraMensa",Time.valueOf(mensa.get(3)));
        RequestDispatcher dispatcher = request.getRequestDispatcher(resp);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    /** Questa servlet verifica che l'utente sia salvato nel database.
     * @post {@literal Utente (empty) || utente->select(u|u.email=email && u.password==password}
     * @param mail Mail dell'utente
     * @param password Password dell'utente
     * @return Utente (empty) se vi sono errori nella richiesta login oppure l'utente non esiste,
     * altrimenti restituisce le informazioni dell'Utente
     */
    public Utente login(String mail, String password, String[] strings)
    {
        try
        {
            Check.mailIsValidLogin(mail);
            Check.passwordIsValid(password,password);
        }
        catch (Exception e)
        {
            strings[1]=e.getMessage();
            return new Utente();
        }
        Utente u= dao.doRetrieveUtenteByEmailPassword(mail,password);
        if(!u.isAccepted())
            strings[0]="L'amministratore non ha ancora accettato la tua richiesta di registrazione.";
        return u;
    }
}