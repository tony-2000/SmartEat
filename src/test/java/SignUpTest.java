import model.Check;
import model.Utente;
import model.UtenteDAO;
import model.UtenteDAOInterface;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import controller.SignUp;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SignUpTest {

    SignUp sign= new SignUp();

    @Test
    public void registrazioneTest1()
    {
        String CF="0123456789ABCDEF";
        String nome="ABCdes";
        String cognome="ABCdes";
        char sesso = 'M';
        Date dataNasc = Date.valueOf("2021-10-12");
        String luogoNasc = "Roma";
        String mail = "miamail@mail.it";
        String residenza = "Pippo";
        String password = "Password.01";
        Utente user = new Utente();
        user.setCF(CF);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setSesso(sesso);
        user.setDataDiNascita(dataNasc);
        user.setLuogoDiNascita(luogoNasc);
        user.setEmail(mail);
        user.setResidenza(residenza);
        user.setPasswordHash(password);

        UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);


        //UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);

        String error = sign.registrazione(CF, nome, cognome, sesso, dataNasc, luogoNasc, mail, residenza, password, password);

        assertEquals("", error);
    }
}
