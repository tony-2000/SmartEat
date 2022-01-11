import controller.GestioneUtenti.AdminUtentiArea;
import model.utente.*;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

public class AdminUtentiAreaTest {

    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private UtenteDAOInterface udao;
    private AdminUtentiArea adminUtentiArea;

    @Before
    public void setup() {
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        udao = mock(UtenteDAO.class);

        adminUtentiArea = new AdminUtentiArea(udao);
    }

    @Test
    public void ShowAllUsersToAccept()
    {
        Utente user1=new Utente();
        Utente user2=new Utente();
        Utente user3=new Utente();
        user1.setCF("AAAAAAAAAAAAAAAA");
        user2.setCF("BBBBBBBBBBBBBBBB");
        user3.setCF("CCCCCCCCCCCCCCCC");
        user1.setAccepted(false);
        user2.setAccepted(true);
        user3.setAccepted(true);
        ArrayList<Utente> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        when(udao.doRetrieveAllUtente()).thenReturn(users);
        ArrayList<Utente> listUser=adminUtentiArea.ShowAllUsers();
        assertNotNull(listUser);
    }

    @Test
    public void AdminUtentiAreaNonInSessioneTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        adminUtentiArea.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
    }

    @Test
    public void AdminUtentiAreaNonAdminTest() throws ServletException, IOException {
        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        adminUtentiArea.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");
    }

    @Test
    public void AdminUtentiAreaOk() throws ServletException, IOException {
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());

        Utente user1=new Utente();
        Utente user2=new Utente();
        Utente user3=new Utente();
        user1.setCF("AAAAAAAAAAAAAAAA");
        user2.setCF("BBBBBBBBBBBBBBBB");
        user3.setCF("CCCCCCCCCCCCCCCC");
        user1.setAccepted(false);
        user2.setAccepted(true);
        user3.setAccepted(true);
        ArrayList<Utente> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        when(udao.doRetrieveAllUtente()).thenReturn(users);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        adminUtentiArea.doGet(request, response);

        verify(dispatcher, atLeastOnce()).forward(request, response);
    }
}