import controller.DeleteMenu;
import controller.DeletePietanza;
import model.*;
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

public class DeleteMenuTest {

    private DeleteMenu deleteMenu;
    private MockedStatic<Mensa> mensa;
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private MenuDAOInterface mdao;
    private MockedStatic<Menu> menuStatic;

    @Before
    public void setup() {
        dispatcher = mock(RequestDispatcher.class);
        mensa = mockStatic(Mensa.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        mdao = mock(MenuDAO.class);
        menuStatic = mockStatic(Menu.class);

        deleteMenu = new DeleteMenu(mdao);
    }

    @Test
    public void deleteMenuOraTest() {

        boolean result;

        mensa.when(Mensa::isMensaConfig).thenReturn(true);

        result = deleteMenu.deleteMenu(2);

        assertTrue(result);

        mensa.close();
        menuStatic.close();
    }

    @Test
    public void deletePietanzaDopoTest() {

        boolean result;

        ArrayList<Menu> menus = new ArrayList<>();

        mensa.when(Mensa::isMensaConfig).thenReturn(false);
        menuStatic.when(Menu::getListDeleteMenu).thenReturn(menus);

        result = deleteMenu.deleteMenu(2);

        assertFalse(result);

        mensa.close();
        menuStatic.close();
    }

    @Test
    public void DeletePietanzaNonInSessioneTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        deleteMenu.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");

        mensa.close();
        menuStatic.close();
    }

    @Test
    public void DeletePietanzaNonAdminTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        deleteMenu.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");

        mensa.close();
        menuStatic.close();
    }

    @Test
    public void DoGetDeleteOraTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getParameter("codiceMenu")).thenReturn("2");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        mensa.when(Mensa::isMensaConfig).thenReturn(true);

        deleteMenu.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "Il menu è stato eliminato correttamente");
        verify(dispatcher, atLeastOnce()).forward(request, response);

        mensa.close();
        menuStatic.close();
    }

    @Test
    public void DoGetDeleteDopoTest() throws ServletException, IOException {
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());
        ArrayList<Menu> menus = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getParameter("codiceMenu")).thenReturn("2");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        mensa.when(Mensa::isMensaConfig).thenReturn(false);
        menuStatic.when(Menu::getListDeleteMenu).thenReturn(menus);

        deleteMenu.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "Il menu verrà eliminato dopo l'orario di chiusura");
        verify(dispatcher, atLeastOnce()).forward(request, response);

        mensa.close();
        menuStatic.close();
    }
}