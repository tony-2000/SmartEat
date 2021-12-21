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

@WebServlet(name="BuyMenu", value="/BuyMenu")
public class BuyMenu extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        int codiceMenu = Integer.parseInt(request.getParameter("codiceMenu"));
        HttpSession session=request.getSession();
        Utente user= (Utente) session.getAttribute("utenteSessione");
        Date actual=new Date(System.currentTimeMillis());
        boolean posto= Boolean.parseBoolean(request.getParameter("postoMensa"));
        TesseraDAOInterface tdao=new TesseraDAO();
        Tessera tessera=tdao.doRetrieveTesseraByKey(user.getCF());
        String[] message=new String[2];
        boolean buy=this.buy(codiceMenu,actual,posto,user.getCF(),tessera,message);
        if(!buy)
            request.setAttribute("message",message[0]);
        else
            request.setAttribute("message",message[1]);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);

    }

    public boolean buy(int codiceMenu,Date actual, boolean posto, String CF,Tessera tessera,String[] message)
    {
        TesseraDAOInterface tesseradao=new TesseraDAO();
        AcquistoDAOInterface acquistodao=new AcquistoDAO();
        Acquisto acquisto=new Acquisto();
        acquisto.setDataAcquisto(actual);
        acquisto.setCF(CF);
        acquisto.setCodiceMenu(codiceMenu);
        acquisto.setPostoMensa(posto);
        MenuDAOInterface menudao=new MenuDAO();
        Menu menu=menudao.doRetrieveMenuByKey(codiceMenu);
        float prezzo=menu.getPrezzo();
        if(Mensa.mensaAperta())
        {
            if(tessera.getSaldo()-prezzo>=0)
            {
                acquistodao.doSave(acquisto);
                tessera.setSaldo(tessera.getSaldo()-prezzo);
                tesseradao.doUpdate(tessera);
            }
            else
            {
                message[0]="Saldo insufficiente, l'operazione non ha avuto successo";
                return false;
            }
        }
        else
        {
            message[0]="Il periodo per effettuare acquisti è terminato, ritorni nella fascia d'orario consentita";
            return false;
        }
        message[1]="Operazione di acquisto completata con successo";
        return true;
    }
}