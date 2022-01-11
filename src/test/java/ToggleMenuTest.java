import controller.GestioneMenu.ToggleMenu;
import model.mensa.Mensa;
import model.menu.Menu;
import model.menu.MenuDAO;
import model.menu.MenuDAOInterface;
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

public class ToggleMenuTest {

    private ToggleMenu toggleMenu;
    private MenuDAOInterface mdao;
    private MockedStatic<Mensa> mensa;
    private MockedStatic<Menu> menuStatic;
    HttpSession session;
    HttpServletRequest request;
    HttpServletResponse response;
    RequestDispatcher dispatcher;

    @Before
    public void setup() {
        mensa = mockStatic(Mensa.class);
        menuStatic = mockStatic(Menu.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        mdao = mock(MenuDAO.class);
        dispatcher = mock(RequestDispatcher.class);

        toggleMenu = new ToggleMenu(mdao);
    }

    @Test
    public void ToggleMenuOraTest() {

        boolean result;

        Menu menu = new Menu();
        menu.setAvailable(true);

        mensa.when(Mensa::isMensaConfig).thenReturn(true);
        when(mdao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);

        result = toggleMenu.toggleMenu(5);

        assertTrue(result);

        mensa.close();
        menuStatic.close();
    }

    @Test
    public void ToggleMenuDopoTest() {

        boolean result;

        Menu menu = new Menu();
        menu.setAvailable(false);
        ArrayList<Menu> menus = new ArrayList<>();

        menuStatic.when(Menu::getListToggleMenu).thenReturn(menus);
        mensa.when(Mensa::isMensaConfig).thenReturn(false);
        when(mdao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);


        result = toggleMenu.toggleMenu(5);

        assertFalse(result);

        mensa.close();
        menuStatic.close();
    }

    @Test
    public void ToggleMenuNonInSessioneTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        toggleMenu.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");

        mensa.close();
        menuStatic.close();
    }

    @Test
    public void ToggleMenuNonAdminTest() throws ServletException, IOException {
        when(request.getSession()).thenReturn(session);
        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        toggleMenu.doGet(request, response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");

        mensa.close();
        menuStatic.close();
    }

    @Test
    public void ToggleMenuCorrettoOraTest() throws ServletException, IOException {
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());
        Menu menu = new Menu();
        menu.setAvailable(true);

        mensa.when(Mensa::isMensaConfig).thenReturn(true);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter("codiceMenu")).thenReturn("5");
        when(mdao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(request.getSession()).thenReturn(session);

        toggleMenu.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "La disponibilita' del menu è stata cambiata");

        mensa.close();
        menuStatic.close();
    }

    @Test
    public void ToggleMenuCorrettoDopoTest() throws ServletException, IOException {
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());
        Menu menu = new Menu();
        menu.setAvailable(true);
        ArrayList<Menu> menus = new ArrayList<>();

        menuStatic.when(Menu::getListToggleMenu).thenReturn(menus);
        mensa.when(Mensa::isMensaConfig).thenReturn(false);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getParameter("codiceMenu")).thenReturn("5");
        when(mdao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(request.getSession()).thenReturn(session);

        toggleMenu.doGet(request, response);

        verify(request, atLeastOnce()).setAttribute("message", "La disponibilita' del menu verra' aggiornata dopo l'orario di chiusura");

        mensa.close();
        menuStatic.close();
    }
}
