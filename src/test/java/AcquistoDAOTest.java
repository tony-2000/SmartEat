import model.Acquisto;
import model.AcquistoDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

}
