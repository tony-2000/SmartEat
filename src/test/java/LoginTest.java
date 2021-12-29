import controller.Login;
import model.Utente;
import model.UtenteDAO;
import model.UtenteDAOInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class LoginTest {

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
        UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);
        login=new Login(udao);

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);

        Mockito.when(udao.doRetrieveUtenteByEmailPassword(Mockito.anyString(),Mockito.anyString())).thenReturn(user);

        login.login(mail, password, strings);

        assertEquals("Il campo mail deve contenere @", strings[1]);
    }

    @Test
    public void loginTestPasswordScorretta()
    {
        String mail="pippo@mail.it";
        String password="P4ssword";
        String[] strings = new String[2];
        UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);
        login=new Login(udao);

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);

        Mockito.when(udao.doRetrieveUtenteByEmailPassword(Mockito.anyString(),Mockito.anyString())).thenReturn(user);

        login.login(mail, password, strings);

        assertEquals("Il campo password non ha caratteri speciali", strings[1]);
    }

    @Test
    public void loginTestNotAccepted()
    {
        String mail="pippo@mail.it";
        String password="P4ssword!";
        String[] strings = new String[2];
        UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);
        login=new Login(udao);

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);
        user.setAccepted(false);

        Mockito.when(udao.doRetrieveUtenteByEmailPassword(Mockito.anyString(),Mockito.anyString())).thenReturn(user);

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
        UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);
        login=new Login(udao);

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);
        user.setCF(CF);

        Mockito.when(udao.doRetrieveUtenteByEmailPassword(Mockito.anyString(),Mockito.anyString())).thenReturn(user);

        Utente user2 = login.login(mail, password, strings);

        assertEquals(user, user2);
        assertNotEquals(null, user2.getCF());
    }

    @Test
    public void doPostTestCorretto()
    {
        Utente user = new Utente();
        user.setEmail("pippo@mail.it");
        user.setPasswordHash("P4ssword!");
        user.setCF("1234567890ABCDEF");
        UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);
        login=new Login(udao);

        Mockito.when(udao.doRetrieveUtenteByEmailPassword(Mockito.anyString(),Mockito.anyString())).thenReturn(user);

    }
}
