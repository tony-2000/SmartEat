package controller.SmartEatConfig;

import model.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Config è una servlet che setta un timer per aggiornamenti vari.
 */
@WebServlet(name="Config", value="/Config",loadOnStartup = 0)
public class Config extends HttpServlet
{

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        MensaDAO dao=new MensaDAO();
        ArrayList<String> mensa=dao.doRetrieveMensaByKey("mensa1");
        Mensa.setPostiVuoti(Integer.parseInt(mensa.get(1)));
        java.util.Timer timer = new java.util.Timer();
        //Timer ogni 30m
        timer.scheduleAtFixedRate(new Timer(), 0, 1000*60*30);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}
}
