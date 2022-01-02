import controller.AcceptUtente;
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

public class AcceptUtenteTest extends Mockito {

    AcceptUtente acceptUtente;
    UtenteDAOInterface udao;
    TesseraDAOInterface tdao;
    HttpSession session;

    @Before
    public void setup() {
        udao = mock(UtenteDAO.class);
        tdao = mock(TesseraDAO.class);
        session = mock(HttpSession.class);
        acceptUtente = new AcceptUtente(udao, tdao, session);
    }

    @Test
    public void CompleteRegAccepted(){
        String CF="1234567890ABCDEF";

        Utente user = new Utente();
        user.setCF(CF);

        when(udao.doRetrieveUtenteByKey(anyString())).thenReturn(user);

        acceptUtente.CompleteReg(CF, true);

        verify(udao, atLeastOnce()).doAccept(anyString());
        verify(tdao, atLeastOnce()).doSave(any(Tessera.class));
    }

    @Test
    public void CompleteRegNotAccepted(){
        String CF="1234567890ABCDEF";

        Utente user = new Utente();
        user.setCF(CF);

        when(udao.doRetrieveUtenteByKey(anyString())).thenReturn(user);

        acceptUtente.CompleteReg(CF, false);

        verify(udao, atLeastOnce()).doDelete(anyString());
    }

    @Test
    public void  AcceptUtenteNonInSessione() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        acceptUtente.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
    }

    @Test
    public void  AcceptUtenteNonAdmin() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        acceptUtente.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");
    }

    @Test
    public void  AcceptUtenteAccepted() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

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
    public void  AcceptUtenteRejected() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

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
