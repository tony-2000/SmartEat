import model.Tessera;
import model.TesseraDAO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TesseraDAOTest
{
    TesseraDAO tesseraDAO;

    @Before
    public void setup() {
        tesseraDAO = new TesseraDAO();
    }

    @Test
    public void doRetrieveTesseraByKeyTest()
    {
        String CF="RSSMRA74D22A0010";

        Tessera tessera;
        tessera=tesseraDAO.doRetrieveTesseraByKey(CF);
        assertEquals(CF,tessera.getCF());
    }

    @Test
    public void doSaveTest()
    {
        String CF="RSSMRA74D22A0015";
        int saldo=24;
        Tessera tessera=new Tessera();
        tessera.setCF(CF);
        tessera.setSaldo(saldo);
        tesseraDAO.doSave(tessera);

        Tessera tessera2;
        tessera2=tesseraDAO.doRetrieveTesseraByKey(CF);

        assertEquals(tessera.getCF(),tessera2.getCF());
        assertEquals(tessera.getSaldo(),tessera2.getSaldo(),0.0001);
    }


    @Test
    public void doDeleteTest()
    {
        String CF="RSSMRA74D22A0011";
        tesseraDAO.doDelete(CF);

        Tessera tessera2;
        tessera2=tesseraDAO.doRetrieveTesseraByKey(CF);
        assertNull(tessera2.getCF());
        assertEquals(tessera2.getSaldo(),0,0.0001);
    }

    @Test
    public void doUpdateTest()
    {
        String CF="RSSMRA74D22A0010";
        Tessera tessera= tesseraDAO.doRetrieveTesseraByKey(CF);
        float vecchioSaldo=tessera.getSaldo();

        tessera.setSaldo(vecchioSaldo+22);
        tesseraDAO.doUpdate(tessera);

        Tessera newTessera;
        newTessera=tesseraDAO.doRetrieveTesseraByKey(CF);

        assertEquals(tessera.getCF(),newTessera.getCF());
        assertNotEquals(vecchioSaldo,newTessera.getSaldo());
    }

}
