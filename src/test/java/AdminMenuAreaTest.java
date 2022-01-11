import controller.GestioneMenu.AdminMenuArea;
import model.menu.Menu;
import model.menu.MenuDAO;
import model.menu.MenuDAOInterface;
import model.utente.RuoloAdmin;
import model.utente.RuoloStandard;
import model.utente.Utente;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AdminMenuAreaTest
{
    AdminMenuArea adminMenuArea;
    MenuDAOInterface mdao;
    HttpSession session;

    @Before
    public void setup() {
        session = mock(HttpSession.class);
        mdao=mock(MenuDAO.class);
        adminMenuArea = new AdminMenuArea(mdao);

    }

    @Test
    public void AdminMenuAreaTestMethod()
    {
        ArrayList<Menu> lista=new ArrayList<>();
        when(mdao.doRetrieveAllMenu()).thenReturn(lista);
        assertNotNull(lista);
    }

    @Test
    public void  AdminMenuAreaTestNonInSessione() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        adminMenuArea.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
    }

    @Test
    public void  AdminMenuAreaTestNonAdmin() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        adminMenuArea.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");
    }


    @Test
    public void  AdminMenuAreaTestTrue() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setCF("1234567890ABCDEF");
        user.setAmministratore(new RuoloAdmin());
        ArrayList<Menu> lista=new ArrayList<>();

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(mdao.doRetrieveAllMenu()).thenReturn(lista);

        adminMenuArea.doGet(request,response);

        verify(dispatcher, atLeastOnce()).forward(request, response);
        verify(request,atLeastOnce()).setAttribute(eq("listaMenu"),any(ArrayList.class));
    }
}
