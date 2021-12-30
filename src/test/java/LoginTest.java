import controller.Login;
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
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class LoginTest extends Mockito{

    Login login;

    @Before
    public void setup()
    {
        login= new Login();
    }

    @Test
    public void loginTestMailScorretta()
    {
        String mail="pippo";
        String password="P4ssword!";
        String[] strings = new String[2];
        UtenteDAOInterface udao = mock(UtenteDAO.class);
        MensaDAOInterface mdao = mock(MensaDAO.class);
        HttpSession session = mock(HttpSession.class);
        login=new Login(udao, mdao, session);

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(),anyString())).thenReturn(user);

        login.login(mail, password, strings);

        assertEquals("Il campo mail deve contenere @", strings[1]);
    }

    @Test
    public void loginTestPasswordScorretta()
    {
        String mail="pippo@mail.it";
        String password="P4ssword";
        String[] strings = new String[2];
        UtenteDAOInterface udao = mock(UtenteDAO.class);
        MensaDAOInterface mdao = mock(MensaDAO.class);
        HttpSession session = mock(HttpSession.class);
        login=new Login(udao, mdao, session);

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(),anyString())).thenReturn(user);

        login.login(mail, password, strings);

        assertEquals("Il campo password non ha caratteri speciali", strings[1]);
    }

    @Test
    public void loginTestNotAccepted()
    {
        String mail="pippo@mail.it";
        String password="P4ssword!";
        String[] strings = new String[2];
        UtenteDAOInterface udao = mock(UtenteDAO.class);
        MensaDAOInterface mdao = mock(MensaDAO.class);
        HttpSession session = mock(HttpSession.class);
        login=new Login(udao, mdao, session);

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);
        user.setAccepted(false);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(),anyString())).thenReturn(user);

        login.login(mail, password, strings);

        assertEquals("L'amministratore non ha ancora accettato la tua richiesta di registrazione.", strings[0]);
    }

    @Test
    public void loginTestCorretto()
    {
        String mail="pippo@mail.it";
        String password="P4ssword!";
        String CF="1234567890ABCDEF";
        String[] strings = new String[2];
        UtenteDAOInterface udao = mock(UtenteDAO.class);
        MensaDAOInterface mdao = mock(MensaDAO.class);
        HttpSession session = mock(HttpSession.class);
        login=new Login(udao, mdao, session);

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);
        user.setCF(CF);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(),anyString())).thenReturn(user);

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
        HttpServletRequest request= mock(HttpServletRequest.class);
        HttpServletResponse response= mock(HttpServletResponse.class);
        UtenteDAOInterface udao = mock(UtenteDAO.class);
        MensaDAOInterface mdao = mock(MensaDAO.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);


        ArrayList<String> strings = new ArrayList<>();
        strings.add("Mensa1");
        strings.add("10");
        strings.add("00:00:00");
        strings.add("00:00:00");


        login=new Login(udao, mdao, session);

        when(udao.doRetrieveUtenteByEmailPassword(anyString(),anyString())).thenReturn(user);
        when(request.getParameter("mail")).thenReturn("pippo@mail.it");
        when(request.getParameter("mail")).thenReturn("P4ssword!");
        when(mdao.doRetrieveMensaByKey(anyString())).thenReturn(strings);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        login.doPost(request,response);


    }
}
