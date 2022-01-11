import controller.GestioneProfilo.UpdateProfile;
import model.tessera.Tessera;
import model.tessera.TesseraDAO;
import model.utente.*;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

public class UpdateProfileTest
{
    private UtenteDAOInterface udao;
    private TesseraDAO tdao;
    private UpdateProfile updateProfile;

    @Before
    public void setup()
    {
        udao = mock(UtenteDAO.class);
        tdao=mock(TesseraDAO.class);
        updateProfile=new UpdateProfile(tdao,udao);
    }


    @Test
    public void updateProfileTestNomeInvalidTest()
    {
        String oldCF="AAAAAAAAAAAAAAAA";
        String oldmail="antonio@gmail.com";
        RuoloUtente oldruolo=new RuoloStandard();
        Utente oldUser=new Utente();
        oldUser.setCF(oldCF);
        oldUser.setEmail(oldmail);
        oldUser.setAmministratore(oldruolo);
        oldUser.setAccepted(true);

        String nome="AntonioAntonioAntonioAntonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res;
        res=updateProfile.updateProfile(oldUser,nome,cognome,gender,data,luogoDN,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }



    @Test
    public void updateProfileTestCognomeInvalidTest()
    {
        String oldCF="AAAAAAAAAAAAAAAA";
        String oldmail="antonio@gmail.com";
        RuoloUtente oldruolo=new RuoloStandard();
        Utente oldUser=new Utente();
        oldUser.setCF(oldCF);
        oldUser.setEmail(oldmail);
        oldUser.setAmministratore(oldruolo);
        oldUser.setAccepted(true);

        String nome="Antonio";
        String cognome="AschettinoAschettinoAschettinoAschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res;
        res=updateProfile.updateProfile(oldUser,nome,cognome,gender,data,luogoDN,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }


    @Test
    public void updateProfileTestSessoInvalidTest()
    {
        String oldCF="AAAAAAAAAAAAAAAA";
        String oldmail="antonio@gmail.com";
        RuoloUtente oldruolo=new RuoloStandard();
        Utente oldUser=new Utente();
        oldUser.setCF(oldCF);
        oldUser.setEmail(oldmail);
        oldUser.setAmministratore(oldruolo);
        oldUser.setAccepted(true);

        String nome="Antonio";
        String cognome="Aschettino";
        String gender="D";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res;
        res=updateProfile.updateProfile(oldUser,nome,cognome,gender,data,luogoDN,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }


    @Test
    public void updateProfileTestNascitaInvalidTest()
    {
        String oldCF="AAAAAAAAAAAAAAAA";
        String oldmail="antonio@gmail.com";
        RuoloUtente oldruolo=new RuoloStandard();
        Utente oldUser=new Utente();
        oldUser.setCF(oldCF);
        oldUser.setEmail(oldmail);
        oldUser.setAmministratore(oldruolo);
        oldUser.setAccepted(true);

        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2022-06-24");
        String luogoDN="Nola";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res;
        res=updateProfile.updateProfile(oldUser,nome,cognome,gender,data,luogoDN,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }


    @Test
    public void updateProfileTestLuogoDNInvalidTest()
    {
        String oldCF="AAAAAAAAAAAAAAAA";
        String oldmail="antonio@gmail.com";
        RuoloUtente oldruolo=new RuoloStandard();
        Utente oldUser=new Utente();
        oldUser.setCF(oldCF);
        oldUser.setEmail(oldmail);
        oldUser.setAmministratore(oldruolo);
        oldUser.setAccepted(true);

        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="NolaNolaNolaNolaNolaNolaNolaNolaNolaNolaNolaNola";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res;
        res=updateProfile.updateProfile(oldUser,nome,cognome,gender,data,luogoDN,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }



    @Test
    public void updateProfileTestResidenzaInvalidTest()
    {
        String oldCF="AAAAAAAAAAAAAAAA";
        String oldmail="antonio@gmail.com";
        RuoloUtente oldruolo=new RuoloStandard();
        Utente oldUser=new Utente();
        oldUser.setCF(oldCF);
        oldUser.setEmail(oldmail);
        oldUser.setAmministratore(oldruolo);
        oldUser.setAccepted(true);

        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String residenza="LauroLauroLauroLauroLauroLauroLauroLauroLauroLauroLauroLauroLauroLauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res;
        res=updateProfile.updateProfile(oldUser,nome,cognome,gender,data,luogoDN,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }


    @Test
    public void updateProfileTestPasswordInvalidTest()
    {
        String oldCF="AAAAAAAAAAAAAAAA";
        String oldmail="antonio@gmail.com";
        RuoloUtente oldruolo=new RuoloStandard();
        Utente oldUser=new Utente();
        oldUser.setCF(oldCF);
        oldUser.setEmail(oldmail);
        oldUser.setAmministratore(oldruolo);
        oldUser.setAccepted(true);

        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String residenza="Lauro";
        String password="Asche";
        String passwordCheck="Asche";

        String res;
        res=updateProfile.updateProfile(oldUser,nome,cognome,gender,data,luogoDN,residenza,password,passwordCheck);
        assertNotEquals("",res);
    }



    @Test
    public void updateProfileTestIsValidTest()
    {
        String oldCF="AAAAAAAAAAAAAAAA";
        String oldmail="antonio@gmail.com";
        RuoloUtente oldruolo=new RuoloStandard();
        Utente oldUser=new Utente();
        oldUser.setCF(oldCF);
        oldUser.setEmail(oldmail);
        oldUser.setAmministratore(oldruolo);
        oldUser.setAccepted(true);

        String nome="Antonio";
        String cognome="Aschettino";
        String gender="M";
        Date data= Date.valueOf("2000-06-24");
        String luogoDN="Nola";
        String residenza="Lauro";
        String password="Aschettino1.";
        String passwordCheck="Aschettino1.";

        String res;
        res=updateProfile.updateProfile(oldUser,nome,cognome,gender,data,luogoDN,residenza,password,passwordCheck);
        assertEquals("",res);
    }


    @Test
    public void doPostTestErrore() throws ServletException, IOException
    {
        HttpSession session=mock(HttpSession.class);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        Utente user=new Utente();
        user.setCF("AAAAAAAAAAAAAAAA");
        user.setEmail("antonio@gmail.com");
        user.setAccepted(true);
        user.setAmministratore(new RuoloStandard());

        Tessera tessera=new Tessera();
        tessera.setCF(user.getCF());
        tessera.setSaldo(100);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getParameter("nome_utente")).thenReturn("");
        when(request.getParameter("cognome")).thenReturn("Aschettino");
        when(request.getParameter("gender")).thenReturn("M");
        when(request.getParameter("dataDiNascita")).thenReturn("2000-06-24");
        when(request.getParameter("luogoDiNascita")).thenReturn("Nola");
        when(request.getParameter("residenza")).thenReturn("Lauro");
        when(request.getParameter("password")).thenReturn("P4ssword!");
        when(request.getParameter("passwordCheck")).thenReturn("P4ssword!");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        updateProfile.doPost(request,response);

        when(udao.doRetrieveUtenteByKey(user.getCF())).thenReturn(user);
        when(tdao.doRetrieveTesseraByKey(user.getCF())).thenReturn(tessera);

        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/results/utente/updateProfile.jsp");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }

    @Test
    public void doPostTestValid() throws ServletException, IOException
    {
        HttpSession session=mock(HttpSession.class);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        Utente user=new Utente();
        user.setCF("AAAAAAAAAAAAAAAA");
        user.setEmail("antonio@gmail.com");
        user.setAccepted(true);
        user.setAmministratore(new RuoloStandard());

        Tessera tessera=new Tessera();
        tessera.setCF(user.getCF());
        tessera.setSaldo(100);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getParameter("nome_utente")).thenReturn("antonio");
        when(request.getParameter("cognome")).thenReturn("Aschettino");
        when(request.getParameter("gender")).thenReturn("M");
        when(request.getParameter("dataDiNascita")).thenReturn("2000-06-24");
        when(request.getParameter("luogoDiNascita")).thenReturn("Nola");
        when(request.getParameter("residenza")).thenReturn("Lauro");
        when(request.getParameter("password")).thenReturn("P4ssword!");
        when(request.getParameter("passwordCheck")).thenReturn("P4ssword!");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        updateProfile.doPost(request,response);

        when(udao.doRetrieveUtenteByKey(user.getCF())).thenReturn(user);
        when(tdao.doRetrieveTesseraByKey(user.getCF())).thenReturn(tessera);

        verify(request, atLeastOnce()).getRequestDispatcher("/WEB-INF/results/utente/showProfile.jsp");
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }

}
