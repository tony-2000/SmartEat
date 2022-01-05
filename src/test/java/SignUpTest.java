import controller.SignUp;
import model.UtenteDAO;
import model.UtenteDAOInterface;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.sql.Date;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

public class SignUpTest
{
    private UtenteDAOInterface udao;
    private SignUp signup;
    private HttpSession session;

    @Before
    public void setup()
    {
        udao = mock(UtenteDAO.class);
        session = mock(HttpSession.class);
        signup=new SignUp(udao);
    }

    @Test
    public void RegistrazioneCFInvalidTest()
    {
        String CF="AAAAAAAA";
        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String mail="antonio@gmail.com";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res="";

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }

    @Test
    public void RegistrazioneNomeInvalidTest()
    {
        String CF="AAAAAAAAAAAAAAAA";
        String nome="aaaaaaaaaaaaaaaaaaaaaaa";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String mail="antonio@gmail.com";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res="";

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }

    @Test
    public void RegistrazioneCognomeInvalidTest()
    {
        String CF="AAAAAAAAAAAAAAAA";
        String nome="Antonio";
        String cognome="aaaaaaaaaaaaaaaaaaaaaaaaaa";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String mail="antonio@gmail.com";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res="";

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }

    @Test
    public void RegistrazioneGenderInvalidTest()
    {
        String CF="AAAAAAAAAAAAAAAA";
        String nome="Antonio";
        String cognome="Aschettino";
        String gender="D";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String mail="antonio@gmail.com";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res="";

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }


    @Test
    public void RegistrazioneDataInvalidTest()
    {
        String CF="AAAAAAAAAAAAAAAA";
        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2022-06-24");
        String luogoDN="Nola";
        String mail="antonio@gmail.com";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res="";

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }

    @Test
    public void RegistrazioneLuogoInvalidTest()
    {
        String CF="AAAAAAAAAAAAAAAA";
        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String mail="antonio@gmail.com";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res="";

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }



    @Test
    public void RegistrazioneMailInvalidTest()
    {
        String CF="AAAAAAAAAAAAAAAA";
        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String mail=".@c";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res="";

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }


    @Test
    public void RegistrazioneResidenzaInvalidTest()
    {
        String CF="AAAAAAAAAAAAAAAA";
        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String mail="antonio@gmail.com";
        String residenza="";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res="";

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }


    @Test
    public void RegistrazionePasswordInvalidTest()
    {
        String CF="AAAAAAAAAAAAAAAA";
        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String mail="antonio@gmail.com";
        String residenza="Lauro";
        String password="Asche";
        String passwordCheck="Asche";

        String res="";

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }

    @Test
    public void RegistrazioneValidTest()
    {
        String CF="AAAAAAAAAAAAAAAA";
        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String mail="antonio@gmail.com";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res="";

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }


}
