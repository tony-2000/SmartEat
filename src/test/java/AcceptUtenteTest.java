import controller.GestioneUtenti.AcceptUtente;
import model.tessera.Tessera;
import model.tessera.TesseraDAO;
import model.tessera.TesseraDAOInterface;
import model.utente.*;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AcceptUtenteTest
{

    AcceptUtente acceptUtente;
    UtenteDAOInterface udao;
    TesseraDAOInterface tdao;
    HttpSession session;

    @Before
    public void setup() {
        udao = mock(UtenteDAO.class);
        tdao = mock(TesseraDAO.class);
        session = mock(HttpSession.class);
        acceptUtente = new AcceptUtente(udao, tdao);
    }

    @Test
    public void CompleteRegAcceptedTest(){
        String CF="1234567890ABCDEF";

        Utente user = new Utente();
        user.setCF(CF);

        when(udao.doRetrieveUtenteByKey(anyString())).thenReturn(user);

        acceptUtente.CompleteReg(CF, true);

        verify(udao, atLeastOnce()).doAccept(anyString());
        verify(tdao, atLeastOnce()).doSave(any(Tessera.class));
    }

    @Test
    public void CompleteRegNotAcceptedTest(){
        String CF="1234567890ABCDEF";

        Utente user = new Utente();
        user.setCF(CF);

        when(udao.doRetrieveUtenteByKey(anyString())).thenReturn(user);

        acceptUtente.CompleteReg(CF, false);

        verify(udao, atLeastOnce()).doDelete(anyString());
    }

    @Test
    public void  AcceptUtenteNonInSessioneTest() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        when(request.getSession()).thenReturn(session);
        acceptUtente.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
    }

    @Test
    public void  AcceptUtenteNonAdminTest() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        acceptUtente.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");
    }

    @Test
    public void  AcceptUtenteAcceptedTest() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setCF("1234567890ABCDEF");
        user.setAmministratore(new RuoloAdmin());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter("accept")).thenReturn("true");
        when(request.getParameter("CFUser")).thenReturn("1234567890ABCDEF");
        when(udao.doRetrieveUtenteByKey(anyString())).thenReturn(user);

        acceptUtente.doGet(request,response);

        verify(request, atLeastOnce()).setAttribute("message","Utente accettato correttamente");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }

    @Test
    public void  AcceptUtenteRejectedTest() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setCF("1234567890ABCDEF");
        user.setAmministratore(new RuoloAdmin());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter("accept")).thenReturn("false");
        when(request.getParameter("CFUser")).thenReturn("1234567890ABCDEF");
        when(udao.doRetrieveUtenteByKey(anyString())).thenReturn(user);

        acceptUtente.doGet(request,response);

        verify(request, atLeastOnce()).setAttribute("message","Utente rifiutato correttamente");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }
}
