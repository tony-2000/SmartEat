import model.pietanza.Pietanza;
import model.pietanza.PietanzaDAO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PietanzaDAOTest
{
    PietanzaDAO pietanzaDAO;

    @Before
    public void setup() {
        pietanzaDAO = new PietanzaDAO();
    }


    @Test
    public void doSaveTest()
    {
        String nome="Carbonara";
        String descrizione="temp";
        char tipo= 'P';
        String ingredienti="pasta,uova,guanciale";
        String immagine="temp.png";
        int acquisti=0;
        Pietanza temp=new Pietanza();
        temp.setNome(nome);
        temp.setDescrizione(descrizione);
        temp.setTipo(tipo);
        temp.setIngredienti(ingredienti);
        temp.setImmagine(immagine);
        temp.setNumeroAcquisti(acquisti);
        pietanzaDAO.doSave(temp);

        Pietanza pietanzaAgg;
        pietanzaAgg=pietanzaDAO.doRetrievePietanzaByKey(nome);

        assertEquals(pietanzaAgg.getNome(),temp.getNome());
        assertEquals(pietanzaAgg.getDescrizione(),temp.getDescrizione());
        assertEquals(pietanzaAgg.getTipo(),temp.getTipo());
        assertEquals(pietanzaAgg.getIngredienti(),temp.getIngredienti());
        assertEquals(pietanzaAgg.getImmagine(),temp.getImmagine());
        assertEquals(pietanzaAgg.getNumeroAcquisti(),temp.getNumeroAcquisti());
    }


    @Test
    public void doRetrieveAllPietanzaTest()
    {
        ArrayList<Pietanza> temp= (ArrayList<Pietanza>) pietanzaDAO.doRetrieveAllPietanza();
        for(Pietanza x: temp)
            assertNotNull(x.getNome());
    }



    @Test
    public void doRetrievePietanzaByKeyTest()
    {
        String nome="Cotoletta2";
        Pietanza pietanza;
        pietanza=pietanzaDAO.doRetrievePietanzaByKey(nome);
        assertEquals(nome,pietanza.getNome());
        assertNotNull(pietanza.getDescrizione());
        assertNotNull(pietanza.getImmagine());
        assertNotNull(pietanza.getIngredienti());
        assertNotNull(String.valueOf(pietanza.getTipo()));
    }


    @Test
    public void doUpdateTest()
    {
        Pietanza oldPietanza= pietanzaDAO.doRetrievePietanzaByKey("Cotoletta3");
        Pietanza PietanzaDaAggiornare=new Pietanza();

        PietanzaDaAggiornare.setNome(oldPietanza.getNome());
        PietanzaDaAggiornare.setDescrizione(oldPietanza.getDescrizione()+"temp100");
        PietanzaDaAggiornare.setTipo('P');
        PietanzaDaAggiornare.setIngredienti(oldPietanza.getIngredienti()+"ingrediente20");
        PietanzaDaAggiornare.setImmagine(oldPietanza.getImmagine()+"png");
        PietanzaDaAggiornare.setNumeroAcquisti(oldPietanza.getNumeroAcquisti()+21);

        pietanzaDAO.doUpdate(PietanzaDaAggiornare);

        Pietanza newPietanza;
        newPietanza=pietanzaDAO.doRetrievePietanzaByKey(PietanzaDaAggiornare.getNome());

        assertEquals(oldPietanza.getNome(),newPietanza.getNome());
        assertNotEquals(oldPietanza.getDescrizione(),newPietanza.getDescrizione());
        assertNotEquals(oldPietanza.getTipo(),newPietanza.getTipo());
        assertNotEquals(oldPietanza.getImmagine(),newPietanza.getImmagine());
        assertNotEquals(oldPietanza.getNumeroAcquisti(),newPietanza.getNumeroAcquisti());
        assertNotEquals(oldPietanza.getIngredienti(),newPietanza.getIngredienti());
    }


    @Test
    public void doDeleteTest()
    {
        String nome="Pasta2";
        pietanzaDAO.doDelete(nome);

        Pietanza pietanzaEl;
        pietanzaEl=pietanzaDAO.doRetrievePietanzaByKey(nome);

        assertNull(pietanzaEl.getNome());
        assertNull(pietanzaEl.getDescrizione());
        assertNull(pietanzaEl.getIngredienti());
        assertNull(pietanzaEl.getImmagine());
        assertEquals(pietanzaEl.getNumeroAcquisti(),0);

    }
}
