import controller.UpdateMensa;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UpdateMensaTest {

    private UpdateMensa updateMensa;
    private MensaDAOInterface mendao;
    private MockedStatic<Mensa> mensa;
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @Before
    public void setup() {
        mensa = mockStatic(Mensa.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        mendao = mock(MensaDAO.class);
        dispatcher = mock(RequestDispatcher.class);

        updateMensa = new UpdateMensa(mendao);
    }

    @Test
    public void updateMensaOraTest() {
        String nome = "mensa1";
        int posti = 5;
        Time ap = Time.valueOf("12:12:00");
        Time ch = Time.valueOf("14:12:00");
        boolean result;

        mensa.when(Mensa::isMensaConfig).thenReturn(true);

        result = updateMensa.updateMensa(posti, ap, ch);

        verify(mendao, atLeastOnce()).doUpdate(nome, posti, ap, ch);
        assertTrue(result);

        mensa.close();
    }

    @Test
    public void updateMensaDopoTest() {
        String nome = "mensa1";
        int posti = 5;
        Time ap = Time.valueOf("12:12:00");
        Time ch = Time.valueOf("14:12:00");
        boolean result;
        ArrayList<String> strings = new ArrayList<>();

        mensa.when(Mensa::isMensaConfig).thenReturn(false);
        mensa.when(Mensa::getListUpdateMensa).thenReturn(strings);

        result = updateMensa.updateMensa(posti, ap, ch);

        assertFalse(result);
        assertEquals("5", strings.get(0));
        assertEquals("12:12:00", strings.get(1));
        assertEquals("14:12:00", strings.get(2));

        mensa.close();
    }

    @Test
    public void updateMensaNonInSessioneTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        updateMensa.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");

        mensa.close();
    }

    @Test
    public void updateMensaNonAdminTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        updateMensa.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");

        mensa.close();
    }

    @Test
    public void updateMensaAperturaScorretta1Test() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());

        ArrayList<String> strings = new ArrayList<>();
        strings.add("mensa1");
        strings.add("500");
        strings.add("12:00:00");
        strings.add("14:00:00");

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");
        when(mendao.doRetrieveMensaByKey(anyString())).thenReturn(strings);
        when(request.getParameter("postiDisponibili")).thenReturn("500");
        when(request.getParameter("orarioApertura")).thenReturn("14:30:00");
        when(request.getParameter("orarioChiusura")).thenReturn("14:00:00");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        updateMensa.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "L'orario di apertura deve essere precedente a quello di chiusura");
        verify(dispatcher,atLeastOnce()).forward(request,response);

        mensa.close();
    }

    @Test
    public void updateMensaAperturaScorretta2Test() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());

        ArrayList<String> strings = new ArrayList<>();
        strings.add("mensa1");
        strings.add("500");
        strings.add("12:00:00");
        strings.add("14:00:00");

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");
        when(mendao.doRetrieveMensaByKey(anyString())).thenReturn(strings);
        when(request.getParameter("postiDisponibili")).thenReturn("500");
        when(request.getParameter("orarioApertura")).thenReturn("00:30:00");
        when(request.getParameter("orarioChiusura")).thenReturn("14:00:00");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        updateMensa.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "L'orario di apertura non può essere precedente alle ore 01:00");
        verify(dispatcher,atLeastOnce()).forward(request,response);

        mensa.close();
    }

    @Test
    public void updateMensaChiusuraScorrettaTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());

        ArrayList<String> strings = new ArrayList<>();
        strings.add("mensa1");
        strings.add("500");
        strings.add("12:00:00");
        strings.add("14:00:00");

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");
        when(mendao.doRetrieveMensaByKey(anyString())).thenReturn(strings);
        when(request.getParameter("postiDisponibili")).thenReturn("500");
        when(request.getParameter("orarioApertura")).thenReturn("12:30:00");
        when(request.getParameter("orarioChiusura")).thenReturn("23:30:00");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        updateMensa.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "L'orario di chiusura non può essere successivo alle ore 23:00");
        verify(dispatcher,atLeastOnce()).forward(request,response);

        mensa.close();
    }

    @Test
    public void updateMensaCorrettaOraTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());

        ArrayList<String> strings = new ArrayList<>();
        strings.add("mensa1");
        strings.add("500");
        strings.add("12:00:00");
        strings.add("14:00:00");

        mensa.when(Mensa::isMensaConfig).thenReturn(true);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");
        when(mendao.doRetrieveMensaByKey(anyString())).thenReturn(strings);
        when(request.getParameter("postiDisponibili")).thenReturn("500");
        when(request.getParameter("orarioApertura")).thenReturn("12:30:00");
        when(request.getParameter("orarioChiusura")).thenReturn("14:30:00");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        updateMensa.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "Le informazioni della mensa sono state modificate correttamente");
        verify(dispatcher,atLeastOnce()).forward(request,response);

        mensa.close();
    }

    @Test
    public void updateMensaCorrettaDopoTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());

        ArrayList<String> strings = new ArrayList<>();
        strings.add("mensa1");
        strings.add("500");
        strings.add("12:00:00");
        strings.add("14:00:00");

        mensa.when(Mensa::isMensaConfig).thenReturn(false);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");
        when(mendao.doRetrieveMensaByKey(anyString())).thenReturn(strings);
        when(request.getParameter("postiDisponibili")).thenReturn("500");
        when(request.getParameter("orarioApertura")).thenReturn("12:30:00");
        when(request.getParameter("orarioChiusura")).thenReturn("14:30:00");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        updateMensa.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "Le informazioni della mensa verranno modificate dopo l'orario di chiusura");
        verify(dispatcher,atLeastOnce()).forward(request,response);

        mensa.close();
    }
}
