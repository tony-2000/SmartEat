package controller.MensaConfig;

import model.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

/**
 * MensaConfig è una servlet che setta un timer per aggiornamenti vari in Mensa.
 */
@WebServlet(name="mensaConfig", value="/mensaConfig",loadOnStartup = 0)
public class mensaConfig extends HttpServlet
{

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MensaTimer(), 0, 1000*60*60);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}
}
