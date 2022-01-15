import controller.GestioneMensa.AdminMensaArea;
import model.mensa.MensaDAO;
import model.mensa.MensaDAOInterface;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AdminMensaAreaTest
{
    AdminMensaArea adminMensaArea;
    MensaDAOInterface mdao;
    HttpSession session;

    @Before
    public void setup() {
        session = mock(HttpSession.class);
        mdao=mock(MensaDAO.class);
        adminMensaArea = new AdminMensaArea(mdao);

    }

    @Test
    public void AdminMensaTest()
    {
        ArrayList<String> mensa=new ArrayList<>();
        mensa.add("SmartEat");
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

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        adminMensaArea.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
    }

    @Test
    public void  AdminAreaNonAdmin() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);

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

        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setCF("1234567890ABCDEF");
        user.setAmministratore(new RuoloAdmin());
        ArrayList<String> mensa=new ArrayList<>();
        mensa.add("SmartEat");

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(mdao.doRetrieveMensaByKey(anyString())).thenReturn(mensa);

        adminMensaArea.doGet(request,response);

        verify(dispatcher, atLeastOnce()).forward(request, response);
        verify(request,atLeastOnce()).setAttribute(eq("mensa"),any(ArrayList.class));
    }

}
