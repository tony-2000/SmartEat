package controller;

import model.Mensa;
import model.MensaDAO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="mensaConfig", value="/mensaConfig",loadOnStartup = 0)
public class mensaConfig extends HttpServlet
{
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        ServletContext ctx=getServletContext();
        MensaDAO dao=new MensaDAO();
        ArrayList<String> mensa=dao.doRetrieveMensaByKey("mensa1");
        int pmd= Integer.parseInt(mensa.get(1));
        ctx.setAttribute("postiDisponibiliMensa",pmd);
        System.out.println(pmd);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {}
}
