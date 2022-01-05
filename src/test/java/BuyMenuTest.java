import controller.BuyMenu;
import model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import javax.servlet.http.HttpSession;

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
    private BuyMenu buyMenu;
    private MockedStatic<Mensa> mensa;
    private ArrayList<Acquisto> acquistos;
    private Menu menu;
    private Tessera tessera;

    @Before
    public void setup()
    {
        float prezzo = 2.5F;

        tdao=mock(TesseraDAO.class);
        acquistodao=mock(AcquistoDAO.class);
        menudao=mock(MenuDAO.class);
        pdao=mock(PietanzaDAO.class);
        mensa = mockStatic(Mensa.class);
        acquistos=new ArrayList<>();
        Acquisto acq1= new Acquisto();
        acq1.setDataAcquisto(new Date(System.currentTimeMillis()));
        Acquisto acq2= new Acquisto();
        acq2.setDataAcquisto(Date.valueOf("2020-12-31"));
        Acquisto acq3= new Acquisto();
        acq3.setDataAcquisto(Date.valueOf("2020-11-30"));
        acquistos.add(acq1);
        acquistos.add(acq2);
        acquistos.add(acq3);
        menu = new Menu();
        menu.setPrezzo(prezzo);
        tessera = new Tessera();
        buyMenu=new BuyMenu(tdao, acquistodao, menudao, pdao);


    }

    @Test
    public void buyIsMensaPurchaseFalseTest()
    {
        Date actual=new Date(System.currentTimeMillis());
        String[] message=new String[2];
        boolean result;

        mensa.when(Mensa::isMensaConfig).thenReturn(false);
        when(menudao.doRetrieveMenuByKey(anyInt())).thenReturn(menu);
        when(acquistodao.doRetrieveAllAcquistoByCF(anyString())).thenReturn(acquistos);

        result=buyMenu.buy(5, actual, true,"1234567890aBCDEF", tessera, message);

        assertFalse(result);
        assertEquals("Il periodo per effettuare acquisti è terminato, ritorni nella fascia d'orario consentita.", message[0]);

        mensa.close();
    }
}
