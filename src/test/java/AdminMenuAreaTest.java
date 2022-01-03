import controller.AdminMenuArea;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class AdminMenuAreaTest extends Mockito
{
    AdminMenuArea adminMenuArea;
    MenuDAOInterface mdao;
    HttpSession session;

    @Before
    public void setup() {
        session = mock(HttpSession.class);
        mdao=mock(MenuDAO.class);
        adminMenuArea = new AdminMenuArea(mdao,session);

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

        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        adminMenuArea.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
    }

    @Test
    public void  AdminMenuAreaTestNonAdmin() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

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