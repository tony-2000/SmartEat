package controller.SmartEatConfig;

import model.mensa.Mensa;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Config e' una servlet che setta un timer per aggiornamenti vari.
 */
@WebServlet(name="Config", value="/Config",loadOnStartup = 0)
public class Config extends HttpServlet
{
    /**
     * Costruttore vuoto
     */
    public Config() {
        super();
    }

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        Mensa.setPostiVuoti(Mensa.mensa.getPostiDisponibili());
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new Timer(), 0, 1000*60*30);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}
}
