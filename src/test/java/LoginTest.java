import controller.Login;
import model.Utente;
import model.UtenteDAO;
import model.UtenteDAOInterface;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest {

    Login login = new Login();

    @Test
    public void loginTest1()
    {
        String mail="pippo@mail.it";
        String password="P4ssword";
        String[] strings = new String[2];
        Utente user = new Utente();

        UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);
        Mockito.when(udao.doRetrieveUtenteByEmailPassword(mail, password)).thenReturn(user);

        Utente user2 = login.login(mail, password, strings);

        assertEquals(user,user2);
    }
}
