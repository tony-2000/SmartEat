import controller.ToggleMenu;
import model.Mensa;
import model.Menu;
import model.MenuDAO;
import model.MenuDAOInterface;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        menu.setAvailable(true);
        ArrayList<Menu> menus = new ArrayList<>();

        menuStatic.when(Menu::getListToggleMenu).thenReturn(menus);
        mensa.when(Mensa::isMensaConfig).thenReturn(false);
        when(mdao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);


        result = toggleMenu.toggleMenu(5);

        assertFalse(result);

        mensa.close();
        menuStatic.close();
    }
}
