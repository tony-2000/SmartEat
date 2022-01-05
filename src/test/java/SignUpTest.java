import controller.SignUp;
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
import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

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

        String res;

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

        String res;

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

        String res;

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

        String res;

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

        String res;

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

        String res;

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

        String res;

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

        String res;

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

        String res;

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

        String res;

        res=signup.registrazione(CF,nome,cognome,gender,data,luogoDN,mail,residenza,password,passwordCheck);
        assertEquals("",res);
    }


    @Test
    public void doPostTestErrore() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("CF")).thenReturn("AAAAAAAAAAAAAAAA");
        when(request.getParameter("nome_utente")).thenReturn("antonio");
        when(request.getParameter("cognome")).thenReturn("Aschettino");
        when(request.getParameter("gender")).thenReturn("M");
        when(request.getParameter("dataDiNacita")).thenReturn("2000-06-24");
        when(request.getParameter("luogoDiNascita")).thenReturn("Nola");
        when(request.getParameter("mail")).thenReturn("antonio@gmail.com");
        when(request.getParameter("residenza")).thenReturn("Lauro");
        when(request.getParameter("password")).thenReturn("P4ssword!");
        when(request.getParameter("passwordCheck")).thenReturn("P4ssword!");

        when(signup.registrazione(anyString(),anyString(),anyString(),anyString(),any(Date.class),anyString(),anyString(),anyString(),anyString(),anyString())).thenReturn("errore");

        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }

    @Test
    public void doPostTestCorretto() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("CF")).thenReturn("AAAAAAAAAAAAAAAA");
        when(request.getParameter("nome_utente")).thenReturn("antonio");
        when(request.getParameter("cognome")).thenReturn("Aschettino");
        when(request.getParameter("gender")).thenReturn("M");
        when(request.getParameter("dataDiNacita")).thenReturn("2000-06-24");
        when(request.getParameter("luogoDiNascita")).thenReturn("Nola");
        when(request.getParameter("mail")).thenReturn("antonio@gmail.com");
        when(request.getParameter("residenza")).thenReturn("Lauro");
        when(request.getParameter("password")).thenReturn("P4ssword!");
        when(request.getParameter("passwordCheck")).thenReturn("P4ssword!");

        when(signup.registrazione(anyString(), anyString(), anyString(), anyString(), any(Date.class), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn("");

        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }
}
