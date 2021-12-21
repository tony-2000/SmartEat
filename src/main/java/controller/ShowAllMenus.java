package controller;

import model.Menu;
import model.MenuDAO;
import model.MenuDAOInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="ShowAllMenus", value="/ShowAllMenus")
public class ShowAllMenus extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ArrayList<Menu> listMenu=this.ShowAllMenu();
        request.setAttribute("listaMenu",listMenu);
        RequestDispatcher dispatcher = request.getRequestDispatcher("showAllMenus.jsp");
        dispatcher.forward(request, response);
    }

    public ArrayList<Menu> ShowAllMenu()
    {
        MenuDAOInterface mdao=new MenuDAO();
        ArrayList<Menu> menu= (ArrayList<Menu>) mdao.doRetrieveAllmenu();
        ArrayList<Menu> disponibili= new ArrayList<>();
        for(Menu x : menu)
        {
            if(x.isAvailable())
                disponibili.add(x);
        }
        return disponibili;
    }
}