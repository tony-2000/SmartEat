import model.mensa.MensaDAO;
import org.junit.Before;
import org.junit.Test;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MensaDAOTest
{

    MensaDAO mensaDAO;

    @Before
    public void setup() {
        mensaDAO = new MensaDAO();
    }


    @Test
    public void doRetrieveMensaByKey()
    {
        String nome="SmartEat";
        ArrayList<String> mensa;
        mensa=mensaDAO.doRetrieveMensaByKey(nome);
        assertEquals(nome,mensa.get(0));
    }


    @Test
    public void doUpdateTest()
    {
        ArrayList<String> mensa= mensaDAO.doRetrieveMensaByKey("SmartEat");
        String nome=mensa.get(0);
        int postiDisponibili= Integer.parseInt(mensa.get(1));
        Time orarioApertura= Time.valueOf(mensa.get(2));
        Time orarioChiusura= Time.valueOf(mensa.get(3));


        int postiDisponibiliAggiornati=postiDisponibili+1;
        Time orarioAperturaAggiornato= Time.valueOf(mensa.get(2)+200000);
        Time orarioChiusuraAggiornato= Time.valueOf(mensa.get(3)+200000);


        mensaDAO.doUpdate(nome,postiDisponibiliAggiornati,orarioAperturaAggiornato,orarioChiusuraAggiornato);

        ArrayList<String> mensaAggiornata= mensaDAO.doRetrieveMensaByKey("SmartEat");

        assertEquals(mensaAggiornata.get(0),mensa.get(0));
        assertNotEquals(mensaAggiornata.get(1),mensa.get(1));
        assertNotEquals(mensaAggiornata.get(2),mensa.get(2));
        assertNotEquals(mensaAggiornata.get(3),mensa.get(3));
    }

}
