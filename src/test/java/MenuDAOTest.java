import model.Menu;
import model.MenuDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MenuDAOTest extends Mockito
{
    MenuDAO menuDAO;

    @Before
    public void setup() {
        menuDAO = new MenuDAO();
    }


    @Test
    public void doSaveTest()
    {
        Menu menu=new Menu();
        menu.setNome("test");
        menu.setPrezzo((float) 9.99);
        menu.setPrimo("Pasta2");
        menu.setSecondo("Cotoletta");
        menu.setDessert("Torta");
        menu.setDescrizione("Testing");
        menu.setImmagine("png");
        menu.setAvailable(true);
        menuDAO.doSave(menu);

        Menu menu2;
        menu2=menuDAO.doRetrieveMenuByKey(12);

        assertEquals(menu.getPrimo(),menu2.getPrimo());
        assertEquals(menu.getSecondo(),menu2.getSecondo());
        assertEquals(menu.getDessert(),menu2.getDessert());
        assertEquals(menu.getNome(),menu2.getNome());
        assertEquals(menu.getPrezzo(),menu2.getPrezzo(),0.0001);
        assertEquals(menu.getDescrizione(),menu2.getDescrizione());
        assertEquals(menu.isAvailable(),menu2.isAvailable());
        assertEquals(menu.getImmagine(),menu2.getImmagine());
    }


    @Test
    public void doRetrieveAllMenuTest()
    {
        ArrayList<Menu> menu= (ArrayList<Menu>) menuDAO.doRetrieveAllMenu();
        for(Menu x: menu)
            assertNotEquals(x.getCodiceMenu(),0);
    }



    @Test
    public void doRetrieveMenuByKeyTest()
    {
        int codiceMenu=1;
        Menu menu;
        menu= menuDAO.doRetrieveMenuByKey(codiceMenu);
        assertEquals(codiceMenu,menu.getCodiceMenu());
    }


    @Test
    public void doUpdateAvailableTest()
    {
        int codiceMenu=8;
        Menu menu= menuDAO.doRetrieveMenuByKey(codiceMenu);
        boolean available=menu.isAvailable();

        menu.setAvailable(!available);
        menuDAO.doUpdateAvailable(codiceMenu,available);

        Menu menu2;
        menu2=menuDAO.doRetrieveMenuByKey(codiceMenu);

        assertEquals(menu.getCodiceMenu(),menu2.getCodiceMenu());
        assertNotEquals(menu.isAvailable(),menu2.isAvailable());
    }


    @Test
    public void doDeleteTest()
    {
        int codiceMenu=12;
        menuDAO.doDelete(codiceMenu);

        Menu menu2;
        menu2=menuDAO.doRetrieveMenuByKey(codiceMenu);

        assertNull(menu2.getPrimo());
        assertNull(menu2.getSecondo());
        assertNull(menu2.getDessert());
        assertNull(menu2.getNome());
        assertEquals(menu2.getPrezzo(),0,0.0001);
        assertNull(menu2.getDescrizione());
        assertFalse(menu2.isAvailable());
        assertNull(menu2.getImmagine());

    }



}
