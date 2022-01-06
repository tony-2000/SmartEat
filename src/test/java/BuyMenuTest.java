import controller.BuyMenu;
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
import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BuyMenuTest {

    private TesseraDAOInterface tdao;
    private AcquistoDAOInterface acquistodao;
    private MenuDAOInterface menudao;
    private PietanzaDAOInterface pdao;
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private BuyMenu buyMenu;
    private MockedStatic<Mensa> mensa;
    private ArrayList<Acquisto> acquistos;
    private Menu menu;
    private Tessera tessera;

    @Before
    public void setup() {
        float prezzo = 2.5F;

        request=mock(HttpServletRequest.class);
        response=mock(HttpServletResponse.class);
        session=mock(HttpSession.class);
        tdao = mock(TesseraDAO.class);
        acquistodao = mock(AcquistoDAO.class);
        menudao = mock(MenuDAO.class);
        pdao = mock(PietanzaDAO.class);
        mensa = mockStatic(Mensa.class);

        acquistos = new ArrayList<>();

        Acquisto acq1 = new Acquisto();
        acq1.setDataAcquisto(Date.valueOf("2020-12-20"));
        Acquisto acq2 = new Acquisto();
        acq2.setDataAcquisto(Date.valueOf("2020-12-31"));
        Acquisto acq3 = new Acquisto();
        acq3.setDataAcquisto(Date.valueOf("2020-11-30"));

        acquistos.add(acq1);
        acquistos.add(acq2);
        acquistos.add(acq3);

        menu = new Menu();
        menu.setPrezzo(prezzo);

        tessera = new Tessera();

        buyMenu = new BuyMenu(tdao, acquistodao, menudao, pdao);
    }

    @Test
    public void buyIsMensaPurchaseFalseTest() {
        Date actual = new Date(System.currentTimeMillis());
        String[] message = new String[2];
        boolean result;

        mensa.when(Mensa::isMensaPurchase).thenReturn(false);
        when(menudao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(acquistodao.doRetrieveAllAcquistoByCF(anyString())).thenReturn(acquistos);

        result = buyMenu.buy(5, actual, true, "1234567890aBCDEF", tessera, message);

        assertFalse(result);
        assertEquals("Il periodo per effettuare acquisti è terminato, ritorni nella fascia d'orario consentita.", message[0]);

        mensa.close();
    }

    @Test
    public void buySaldoTesseraInsufficienteTest() {
        Date actual = new Date(System.currentTimeMillis());
        String[] message = new String[2];
        boolean result;

        tessera.setSaldo(1);

        mensa.when(Mensa::isMensaPurchase).thenReturn(true);
        when(menudao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(acquistodao.doRetrieveAllAcquistoByCF(anyString())).thenReturn(acquistos);

        result = buyMenu.buy(5, actual, true, "1234567890aBCDEF", tessera, message);

        assertFalse(result);
        assertEquals("Saldo insufficiente, l'operazione non ha avuto successo.", message[0]);

        mensa.close();
    }

    @Test
    public void buyHasPurchaseAlreadyTest() {
        Date actual = new Date(System.currentTimeMillis());
        String[] message = new String[2];
        boolean result;

        tessera.setSaldo(5);

        acquistos.get(0).setDataAcquisto(actual);
        mensa.when(Mensa::isMensaPurchase).thenReturn(true);
        when(menudao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(acquistodao.doRetrieveAllAcquistoByCF(anyString())).thenReturn(acquistos);

        result = buyMenu.buy(5, actual, true, "1234567890aBCDEF", tessera, message);

        assertFalse(result);
        assertEquals("Limite acquisto giornaliero raggiunto, l'operazione non ha avuto successo.", message[0]);

        mensa.close();
    }

    @Test
    public void buyPostiPieniTest() {
        Date actual = new Date(System.currentTimeMillis());
        String[] message = new String[2];
        boolean result;

        tessera.setSaldo(5);

        mensa.when(Mensa::getPostiVuoti).thenReturn(0);
        mensa.when(Mensa::isMensaPurchase).thenReturn(true);
        when(menudao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(acquistodao.doRetrieveAllAcquistoByCF(anyString())).thenReturn(acquistos);

        result = buyMenu.buy(5, actual, true, "1234567890aBCDEF", tessera, message);

        assertFalse(result);
        assertEquals("Posto non disponibile, l'operazione non ha avuto successo", message[0]);

        mensa.close();
    }

    @Test
    public void buyOkConPostiTest() {
        Date actual = new Date(System.currentTimeMillis());
        String[] message = new String[2];
        boolean result;

        tessera.setSaldo(5);
        menu.setPrimo("Pasta");
        menu.setSecondo("Carne");
        menu.setDessert("Torta");

        Pietanza primo = new Pietanza();
        primo.setNumeroAcquisti(5);
        Pietanza secondo = new Pietanza();
        secondo.setNumeroAcquisti(5);
        Pietanza dessert = new Pietanza();
        dessert.setNumeroAcquisti(5);

        mensa.when(Mensa::getPostiVuoti).thenReturn(100);
        mensa.when(Mensa::isMensaPurchase).thenReturn(true);
        when(menudao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(acquistodao.doRetrieveAllAcquistoByCF(anyString())).thenReturn(acquistos);
        when(pdao.doRetrievePietanzaByKey("Pasta")).thenReturn(primo);
        when(pdao.doRetrievePietanzaByKey("Carne")).thenReturn(secondo);
        when(pdao.doRetrievePietanzaByKey("Torta")).thenReturn(dessert);

        result = buyMenu.buy(5, actual, true, "1234567890aBCDEF", tessera, message);

        assertTrue(result);
        assertEquals("Operazione di acquisto completata con successo.", message[1]);
        verify(acquistodao, atLeastOnce()).doSave(any(Acquisto.class));

        mensa.close();
    }

    @Test
    public void doGetNoBuyTest() throws ServletException, IOException {
        String[] message = new String[2];
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        Utente user = new Utente();
        user.setCF("1234567890ABCDEF");

        mensa.when(Mensa::isMensaPurchase).thenReturn(false);
        when(menudao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(acquistodao.doRetrieveAllAcquistoByCF(anyString())).thenReturn(acquistos);
        when(tdao.doRetrieveTesseraByKey(anyString())).thenReturn(tessera);
        when(request.getParameter("codiceMenu")).thenReturn("5");
        when(request.getParameter("postoMensa")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(session.getAttribute("utenteSessione")).thenReturn(user);

        buyMenu.doGet(request,response);

        verify(request, atLeastOnce()).setAttribute("message","Il periodo per effettuare acquisti è terminato, ritorni nella fascia d'orario consentita.");

        mensa.close();
    }

    @Test
    public void doGetBuyOkTest() throws ServletException, IOException {
        String[] message = new String[2];
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        Utente user = new Utente();
        user.setCF("1234567890ABCDEF");
        tessera.setSaldo(5);
        menu.setPrimo("Pasta");
        menu.setSecondo("Carne");
        menu.setDessert("Torta");
        Pietanza primo = new Pietanza();
        primo.setNumeroAcquisti(5);
        Pietanza secondo = new Pietanza();
        secondo.setNumeroAcquisti(5);
        Pietanza dessert = new Pietanza();
        dessert.setNumeroAcquisti(5);

        mensa.when(Mensa::isMensaPurchase).thenReturn(false);
        when(menudao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(acquistodao.doRetrieveAllAcquistoByCF(anyString())).thenReturn(acquistos);
        when(tdao.doRetrieveTesseraByKey(anyString())).thenReturn(tessera);
        when(request.getParameter("codiceMenu")).thenReturn("5");
        when(request.getParameter("postoMensa")).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(session.getAttribute("utenteSessione")).thenReturn(user);
        mensa.when(Mensa::getPostiVuoti).thenReturn(100);
        mensa.when(Mensa::isMensaPurchase).thenReturn(true);
        when(menudao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(acquistodao.doRetrieveAllAcquistoByCF(anyString())).thenReturn(acquistos);
        when(pdao.doRetrievePietanzaByKey("Pasta")).thenReturn(primo);
        when(pdao.doRetrievePietanzaByKey("Carne")).thenReturn(secondo);
        when(pdao.doRetrievePietanzaByKey("Torta")).thenReturn(dessert);

        buyMenu.doGet(request,response);

        verify(request, atLeastOnce()).setAttribute("message","Operazione di acquisto completata con successo.");

        mensa.close();
    }
}
