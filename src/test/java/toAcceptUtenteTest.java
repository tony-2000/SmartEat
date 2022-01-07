import controller.toAcceptUtente;
import model.*;
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

public class toAcceptUtenteTest
{
    private UtenteDAOInterface udao;
    private toAcceptUtente toAcceptUtente;


    @Before
    public void setup() {
        udao = mock(UtenteDAO.class);
        toAcceptUtente = new toAcceptUtente(udao);
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
        user2.setAccepted(false);
        user3.setAccepted(true);
        ArrayList<Utente> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        when(udao.doRetrieveAllUtente()).thenReturn(users);
        ArrayList<Utente> listUser=toAcceptUtente.ShowAllUsersToAccept();
        assertNotNull(listUser);
    }


    @Test
    public void toAcceptUtenteTestNonInSessione() throws ServletException, IOException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        HttpSession session=mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        toAcceptUtente.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
    }


    @Test
    public void toAcceptUtenteTestNonAdmin() throws ServletException, IOException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        HttpSession session=mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        toAcceptUtente.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");
    }


    @Test
    public void toAcceptUtenteTestValid() throws ServletException, IOException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session=mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setCF("1234567890ABCDEF");
        user.setAmministratore(new RuoloAdmin());
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        toAcceptUtente.doGet(request,response);

        verify(dispatcher, atLeastOnce()).forward(request, response);
    }




}
