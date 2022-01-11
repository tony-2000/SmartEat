import controller.GestionePietanze.DeletePietanza;
import model.mensa.Mensa;
import model.pietanza.Pietanza;
import model.pietanza.PietanzaDAO;
import model.pietanza.PietanzaDAOInterface;
import model.utente.RuoloAdmin;
import model.utente.RuoloStandard;
import model.utente.Utente;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class DeletePietanzaTest {

    private DeletePietanza deletePietanza;
    private MockedStatic<Mensa> mensa;
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private PietanzaDAOInterface pdao;
    private MockedStatic<Pietanza> pietanzaStatic;

    @Before
    public void setup() {
        dispatcher = mock(RequestDispatcher.class);
        mensa = mockStatic(Mensa.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        pdao = mock(PietanzaDAO.class);
        pietanzaStatic = mockStatic(Pietanza.class);

        deletePietanza = new DeletePietanza(pdao);
    }

    @Test
    public void deletePietanzaOraTest() {

        boolean result;

        mensa.when(Mensa::isMensaConfig).thenReturn(true);

        result = deletePietanza.deletePietanza("Carne");

        assertTrue(result);

        mensa.close();
        pietanzaStatic.close();
    }

    @Test
    public void deletePietanzaDopoTest() {

        boolean result;

        ArrayList<Pietanza> pietanzas = new ArrayList<>();

        mensa.when(Mensa::isMensaConfig).thenReturn(false);
        pietanzaStatic.when(Pietanza::getListDeletePietanza).thenReturn(pietanzas);

        result = deletePietanza.deletePietanza("Carne");

        assertFalse(result);

        mensa.close();
        pietanzaStatic.close();
    }

    @Test
    public void DeletePietanzaNonInSessioneTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        deletePietanza.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");

        mensa.close();
        pietanzaStatic.close();
    }

    @Test
    public void DeletePietanzaNonAdminTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        deletePietanza.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");

        mensa.close();
        pietanzaStatic.close();
    }

    @Test
    public void DoGetDeleteOraTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getParameter("nomePietanza")).thenReturn("Carne");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        mensa.when(Mensa::isMensaConfig).thenReturn(true);

        deletePietanza.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "La pietanza è stata eliminata correttamente");
        verify(dispatcher, atLeastOnce()).forward(request, response);

        mensa.close();
        pietanzaStatic.close();
    }

    @Test
    public void DoGetDeleteDopoTest() throws ServletException, IOException {
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());
        ArrayList<Pietanza> pietanzas = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getParameter("nomePietanza")).thenReturn("Carne");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        mensa.when(Mensa::isMensaConfig).thenReturn(false);
        pietanzaStatic.when(Pietanza::getListDeletePietanza).thenReturn(pietanzas);

        deletePietanza.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "La pietanza verrà eliminata dopo l'orario di chiusura");
        verify(dispatcher, atLeastOnce()).forward(request, response);

        mensa.close();
        pietanzaStatic.close();
    }
}
