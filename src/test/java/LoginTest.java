import controller.Login;
import model.Utente;
import model.UtenteDAO;
import model.UtenteDAOInterface;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class LoginTest {

    private Login login;
    private UtenteDAOInterface udao;
    private HttpSession session;

    @Before
    public void setup()
    {
        udao = mock(UtenteDAO.class);
        session = mock(HttpSession.class);
        login = new Login(udao);
    }

    @Test
    public void loginTestMailScorretta() {
        String mail = "pippo";
        String password = "P4ssword!";
        String[] strings = new String[2];

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(), anyString())).thenReturn(user);

        login.login(mail, password, strings);

        assertEquals("Il campo mail deve contenere @", strings[1]);
    }

    @Test
    public void loginTestPasswordScorretta() {
        String mail = "pippo@mail.it";
        String password = "P4ssword";
        String[] strings = new String[2];

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(), anyString())).thenReturn(user);

        login.login(mail, password, strings);

        assertEquals("Il campo password non ha caratteri speciali", strings[1]);
    }

    @Test
    public void loginTestNotAccepted() {
        String mail = "pippo@mail.it";
        String password = "P4ssword!";
        String CF = "1234567890ABCDEF";
        String[] strings = new String[2];

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);
        user.setCF(CF);
        user.setAccepted(false);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(), anyString())).thenReturn(user);

        login.login(mail, password, strings);

        assertEquals("L'amministratore non ha ancora accettato la tua richiesta di registrazione.", strings[0]);
    }

    @Test
    public void loginTestCorretto() {
        String mail = "pippo@mail.it";
        String password = "P4ssword!";
        String CF = "1234567890ABCDEF";
        String[] strings = new String[2];

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);
        user.setCF(CF);
        user.setAccepted(false);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(), anyString())).thenReturn(user);

        Utente user2 = login.login(mail, password, strings);

        assertEquals(user, user2);
        assertNotEquals(null, user2.getCF());
    }

    @Test
    public void doPostTestCorretto() throws ServletException, IOException {
        Utente user = new Utente();
        user.setEmail("pippo@mail.it");
        user.setPasswordHash("P4ssword!");
        user.setCF("1234567890ABCDEF");
        user.setAccepted(true);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(), anyString())).thenReturn(user);
        when(request.getParameter("mail")).thenReturn("pippo@mail.it");
        when(request.getParameter("password")).thenReturn("P4ssword!");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        login.doPost(request, response);

        verify(session, atLeastOnce()).setAttribute(eq("utenteSessione"), any(Utente.class));
    }

    @Test
    public void doPostTestNullCFIncorrectValidation() throws ServletException, IOException {
        Utente user = new Utente();
        user.setEmail("pippo@mail.it");
        user.setPasswordHash("P4ssword");
        user.setAccepted(true);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(), anyString())).thenReturn(user);
        when(request.getParameter("mail")).thenReturn("pippo@mail.it");
        when(request.getParameter("password")).thenReturn("P4ssword!");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        login.doPost(request, response);

        verify(request, atLeastOnce()).setAttribute(eq("logError"), anyString());
        verify(request, never()).setAttribute(eq("logError"), eq("L'amministratore non ha ancora accettato la tua richiesta di registrazione."));
    }

    @Test
    public void doPostTestNullCFNotAccepted() throws ServletException, IOException {
        Utente user = new Utente();
        user.setEmail("pippo@mail.it");
        user.setPasswordHash("P4ssword!");
        user.setCF("1234567890ABCDEF");
        user.setAccepted(false);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(), anyString())).thenReturn(user);
        when(request.getParameter("mail")).thenReturn("pippo@mail.it");
        when(request.getParameter("password")).thenReturn("P4ssword!");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        login.doPost(request, response);

        verify(request, atLeastOnce()).setAttribute(eq("logError"), eq("L'amministratore non ha ancora accettato la tua richiesta di registrazione."));
    }
}
