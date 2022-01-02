import model.Acquisto;
import model.AcquistoDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AcquistoDAOTest extends Mockito
{

    AcquistoDAO acquistoDAO;

    @Before
    public void setup() {
        acquistoDAO = new AcquistoDAO();
    }

    @Test
    public void doRetrieveAcquistoByKeyTest()
    {
        Date dataAcquisto=Date.valueOf("2021-12-10");
        String CF="RSSMRA74D22A0011";
        int codiceMenu=1;
        Acquisto acquisto;
        acquisto=acquistoDAO.doRetrieveAcquistoByKey(dataAcquisto,CF,codiceMenu);
        assertEquals(CF,acquisto.getCF());
        assertEquals(codiceMenu,acquisto.getCodiceMenu());
        assertEquals(dataAcquisto,acquisto.getDataAcquisto());
        assertTrue(acquisto.isPostoMensa());
    }


    @Test
    public void doRetrieveAllAcquistoByCFTest()
    {
        String CF="RSSMRA74D22A0012";
        ArrayList<Acquisto> acquisto= (ArrayList<Acquisto>) acquistoDAO.doRetrieveAllAcquistoByCF(CF);
        int i=0;
        for(Acquisto x: acquisto)
        {
            assertEquals(CF,acquisto.get(i).getCF());
            i++;
        }
    }

    @Test
    public void doSaveTest()
    {
        Date dataAcquisto=Date.valueOf("2021-11-19");
        String CF="RSSMRA74D22A0014";
        int codiceMenu=4;
        boolean pm=true;
        Acquisto acquisto=new Acquisto();
        acquisto.setDataAcquisto(dataAcquisto);
        acquisto.setPostoMensa(pm);
        acquisto.setCodiceMenu(codiceMenu);
        acquisto.setCF(CF);
        acquistoDAO.doSave(acquisto);

        Acquisto acquisto2;
        acquisto2=acquistoDAO.doRetrieveAcquistoByKey(dataAcquisto,CF,codiceMenu);

        assertEquals(acquisto2.getCF(),acquisto.getCF());
        assertEquals(acquisto2.getCodiceMenu(),acquisto.getCodiceMenu());
        assertEquals(acquisto2.getDataAcquisto(),acquisto.getDataAcquisto());
        assertEquals(acquisto2.isPostoMensa(),acquisto.isPostoMensa());
    }


    @Test
    public void doUpdateTest()
    {
        Acquisto acquisto= acquistoDAO.doRetrieveAcquistoByKey(Date.valueOf("2021-12-12"),"RSSMRA74D22A0013",1);
        boolean postoMensa=acquisto.isPostoMensa();

        acquisto.setPostoMensa(false);
        acquistoDAO.doUpdate(acquisto);

        Acquisto acquisto2;
        acquisto2=acquistoDAO.doRetrieveAcquistoByKey(Date.valueOf("2021-12-12"),"RSSMRA74D22A0013",1);

        assertEquals(acquisto2.getCF(),acquisto.getCF());
        assertEquals(acquisto2.getCodiceMenu(),acquisto.getCodiceMenu());
        assertEquals(acquisto2.getDataAcquisto(),acquisto.getDataAcquisto());
    }



    @Test
    public void doDeleteTest()
    {
        Date dataAcquisto=Date.valueOf("2021-12-11");
        String CF="RSSMRA74D22A0012";
        int codiceMenu=1;
        acquistoDAO.doDelete(dataAcquisto,CF,codiceMenu);

        Acquisto acquisto2;
        acquisto2=acquistoDAO.doRetrieveAcquistoByKey(dataAcquisto,CF,codiceMenu);

        assertNull(acquisto2.getCF());
        assertNull(acquisto2.getDataAcquisto());
        assertEquals(acquisto2.getCodiceMenu(),0);
        assertFalse(acquisto2.isPostoMensa());

    }

}
