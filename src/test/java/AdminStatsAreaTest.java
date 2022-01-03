import controller.AdminStatsArea;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AdminStatsAreaTest extends Mockito
{
    AdminStatsArea adminStatsArea;
    PietanzaDAOInterface pdao;
    MenuDAOInterface mdao;
    HttpSession session;
    ArrayList<Pietanza> listPietanza;

    @Before
    public void setup() {
        session = mock(HttpSession.class);
        pdao=mock(PietanzaDAO.class);
        mdao=mock(MenuDAO.class);
        adminStatsArea = new AdminStatsArea(pdao,mdao,session);
        listPietanza=new ArrayList<>();
    }

    @Test
    public void mostraStatisticheTest()
    {
        Pietanza primo=new Pietanza();
        primo.setNome("primoTest");
        primo.setTipo('P');
        primo.setIngredienti("IngredientiTest");
        primo.setNumeroAcquisti(1);
        primo.setImmagine("test,png");
        primo.setDescrizione("testDescrizione");
        Pietanza secondo=new Pietanza();
        secondo.setNome("secondoTest");
        secondo.setTipo('S');
        secondo.setIngredienti("IngredientiTest");
        secondo.setNumeroAcquisti(1);
        secondo.setImmagine("test,png");
        secondo.setDescrizione("testDescrizione");
        Pietanza dessert=new Pietanza();
        dessert.setNome("dessertTest");
        dessert.setTipo('D');
        dessert.setIngredienti("IngredientiTest");
        dessert.setNumeroAcquisti(1);
        dessert.setImmagine("test,png");
        dessert.setDescrizione("testDescrizione");
        listPietanza.add(primo);
        listPietanza.add(secondo);
        listPietanza.add(dessert);

        ArrayList<Menu> listMenu=new ArrayList<>();
        Menu menu=new Menu();
        menu.setCodiceMenu(10);
        menu.setNome("test");
        menu.setPrimo("primoTest");
        menu.setSecondo("secondoTest");
        menu.setDessert("dessertTest");
        menu.setDescrizione("testDescrizione");
        menu.setImmagine("test.png");
        menu.setAvailable(true);
        listMenu.add(menu);
        when(mdao.doRetrieveAllMenu()).thenReturn(listMenu);
        ArrayList<Integer> counter= (ArrayList<Integer>) adminStatsArea.mostraStatistiche(listPietanza);
        assertEquals(counter.get(0),1,0.001);
        assertEquals(counter.get(1),1,0.001);
        assertEquals(counter.get(2),1,0.001);

    }

    @Test
    public void  AdminStatsAreaTestNonInSessione() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        when(session.getAttribute("utenteSessione")).thenReturn(null);
        when(request.getContextPath()).thenReturn("context");

        adminStatsArea.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
    }

    @Test
    public void  AdminStatsAreaTestNonAdmin() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);

        Utente user = new Utente();
        user.setAmministratore(new RuoloStandard());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getContextPath()).thenReturn("context");

        adminStatsArea.doGet(request,response);

        verify(response, atLeastOnce()).sendRedirect("context/toHome");
    }


    @Test
    public void  AdminStatsAreaTestTrue() throws IOException, ServletException {
        HttpServletResponse response= mock(HttpServletResponse.class);
        HttpServletRequest request= mock(HttpServletRequest.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        Utente user = new Utente();
        user.setCF("1234567890ABCDEF");
        user.setAmministratore(new RuoloAdmin());

        when(session.getAttribute("utenteSessione")).thenReturn(user);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(pdao.doRetrieveAllPietanza()).thenReturn(listPietanza);

        adminStatsArea.doGet(request,response);

        verify(dispatcher, atLeastOnce()).forward(request, response);
        verify(request,atLeastOnce()).setAttribute(eq("pietanze"),any(ArrayList.class));
        verify(request,atLeastOnce()).setAttribute(eq("statsMenu"),any(ArrayList.class));
    }
}
