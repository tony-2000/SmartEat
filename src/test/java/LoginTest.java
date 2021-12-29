import controller.Login;
import model.Utente;
import model.UtenteDAO;
import model.UtenteDAOInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


public class LoginTest {

    Login login;

    @Before
    public void setup()
    {
        login= new Login();
    }

    @Test
    public void loginTestUtenteNonTrovato()
    {
        String mail="pippo@mail.it";
        String password="P4ssword";
        String[] strings = new String[2];
        Utente user = new Utente();
        UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);
        login=new Login(udao);

        Mockito.when(udao.doRetrieveUtenteByEmailPassword(mail, password)).thenReturn(user);

        Utente user2 = login.login(mail, password, strings);

        assertEquals(user.getCF(),user2.getCF());
    }

    @Test
    public void loginTestMailScorretta()
    {
        String mail="pippo";
        String password="P4ssword";
        String[] strings = new String[2];
        UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);
        login=new Login(udao);

        Utente user = new Utente();
        user.setEmail(mail);
        user.setPasswordHash(password);

        Mockito.when(udao.doRetrieveUtenteByEmailPassword(mail, password)).thenReturn(user);

        Utente user2 = login.login(mail, password, strings);

        assertEquals(strings[1], "Il campo mail deve contenere @");
    }
}
