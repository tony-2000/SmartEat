
import controller.AdminMensaArea;
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

import static org.junit.Assert.assertEquals;

public class AdminMensaAreaTest extends Mockito
{
    AdminMensaArea adminMensaArea;
    MensaDAOInterface mdao;
    HttpSession session;

    @Before
    public void setup() {
        session = mock(HttpSession.class);
        mdao=mock(MensaDAO.class);
        adminMensaArea = new AdminMensaArea(mdao,session);

    }

    @Test
    public void AdminMensaTest()
    {
        ArrayList<String> mensa=new ArrayList<>();
        mensa.add("mensa1");
        mensa.add("40");
        mensa.add("14:40:50");
        mensa.add("15:56:45");
        when(mdao.doRetrieveMensaByKey(anyString())).thenReturn(mensa);
        ArrayList<String> ret=adminMensaArea.adminMensa();
        assertEquals(mensa,ret);
    }

    @Test
    public void  AdminMensaAreaNonInSessione() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        adminMensaArea.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
    }

    @Test
    public void  AdminAreaNonAdmin() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        adminMensaArea.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");
    }

    @Test
    public void  AdminAreaTrue() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        Utente user = new Utente();
        user.setCF("1234567890ABCDEF");
        user.setAmministratore(new RuoloAdmin());
        ArrayList<String> mensa=new ArrayList<>();
        mensa.add("mensa1");

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(mdao.doRetrieveMensaByKey(anyString())).thenReturn(mensa);

        adminMensaArea.doGet(request,response);

        verify(dispatcher, atLeastOnce()).forward(request, response);
        verify(request,atLeastOnce()).setAttribute(eq("mensa"),any(ArrayList.class));
    }

}
