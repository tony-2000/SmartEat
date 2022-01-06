import controller.AddMenu;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class AddMenuTest
{
    private AddMenu addMenu;
    private MenuDAOInterface mdao;
    private HttpSession session;
    private MockedStatic<Mensa> mensa;
    private  MockedStatic<Menu> menuStatic;

    @Before
    public void setup()
    {
        mensa = mockStatic(Mensa.class);
        menuStatic = mockStatic(Menu.class);
        mdao = mock(MenuDAO.class);
        session = mock(HttpSession.class);
        addMenu = new AddMenu(mdao);
    }

    @Test
    public void addMenuConfigTrueTest()
    {
        mensa.when(Mensa::isMensaConfig).thenReturn(true);

        Menu menu=new Menu();

        assertTrue(addMenu.addMenu(menu));
        mensa.close();
        menuStatic.close();
    }

    @Test
    public void addMenuConfigFalseTest()
    {
        mensa.when(Mensa::isMensaConfig).thenReturn(false);

        Menu menu=new Menu();
        ArrayList<Menu> menus = new ArrayList<>();

        menuStatic.when(Menu::getListAddMenu).thenReturn(menus);

        assertFalse(addMenu.addMenu(menu));
        mensa.close();
        menuStatic.close();
    }

    @Test
    public void AddMenuNonInSessioneTest() throws ServletException, IOException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        addMenu.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
        mensa.close();
        menuStatic.close();
    }

    @Test
    public void AddMenuNonAdminTest() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        Utente user=new Utente();
        user.setAmministratore(new RuoloStandard());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        addMenu.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");
        mensa.close();
        menuStatic.close();
    }

    @Test
    public void AddMenuNowTest() throws ServletException, IOException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Part part = mock(Part.class);
        InputStream inputStream = mock(InputStream.class);
        MockedStatic<Files> file = mockStatic(Files.class);
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getPart("image")).thenReturn(part);
        when(part.getSubmittedFileName()).thenReturn("image");
        when(part.getInputStream()).thenReturn(inputStream);
        when(request.getParameter("nome")).thenReturn("Nome Menu");
        when(request.getParameter("primo")).thenReturn("Primo");
        when(request.getParameter("secondo")).thenReturn("Secondo");
        when(request.getParameter("dessert")).thenReturn("Dessert");
        when(request.getParameter("descrizione")).thenReturn("Desc test");
        when(request.getParameter("prezzo")).thenReturn("5");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        mensa.when(Mensa::isMensaConfig).thenReturn(true);

        addMenu.doGet(request,response);

        verify(request, atLeastOnce()).setAttribute(anyString(), eq("Il menu è stato aggiunto correttamente"));
        mensa.close();
        file.close();
        menuStatic.close();
    }

    @Test
    public void AddMenuLaterTest() throws ServletException, IOException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Part part = mock(Part.class);
        InputStream inputStream = mock(InputStream.class);
        MockedStatic<Files> file = mockStatic(Files.class);
        ArrayList<Menu> menus = new ArrayList<>();

        menuStatic.when(Menu::getListAddMenu).thenReturn(menus);
        Utente user = new Utente();
        user.setAmministratore(new RuoloAdmin());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getPart("image")).thenReturn(part);
        when(part.getSubmittedFileName()).thenReturn("image");
        when(part.getInputStream()).thenReturn(inputStream);
        when(request.getParameter("nome")).thenReturn("Nome Menu");
        when(request.getParameter("primo")).thenReturn("Primo");
        when(request.getParameter("secondo")).thenReturn("Secondo");
        when(request.getParameter("dessert")).thenReturn("Dessert");
        when(request.getParameter("descrizione")).thenReturn("Desc test");
        when(request.getParameter("prezzo")).thenReturn("5");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        mensa.when(Mensa::isMensaConfig).thenReturn(false);

        addMenu.doGet(request,response);

        verify(request, atLeastOnce()).setAttribute(anyString(), eq("Il menu verrà aggiunto dopo l'orario di chiusura"));
        mensa.close();
        file.close();
        menuStatic.close();
    }
}
