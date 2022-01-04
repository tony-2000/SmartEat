import model.ConPool;
import model.MensaDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MensaDAOTest
{
    private Connection c;
    private PreparedStatement stmt;
    private ResultSet rs;

    MensaDAO mensaDAO;

    @Before
    public void setup() {
        mensaDAO = new MensaDAO();
        c=mock(Connection.class);
        stmt=mock(PreparedStatement.class);
        rs=mock(ResultSet.class);
    }


    @Test
    public void doRetrieveMensaByKey()
    {
        String nome = "Mensa1";
        ArrayList<String> mensa;
        try {
            Mockito.mockStatic(ConPool.class);

            when(ConPool.getConnection()).thenReturn(c);
            when(c.prepareStatement(any(String.class))).thenReturn(stmt);
            when(rs.getString(1)).thenReturn("Mensa1");
            when(rs.getInt(2)).thenReturn(499);
            when(rs.getTime(3)).thenReturn(Time.valueOf("13:10:00"));
            when(rs.getTime(4)).thenReturn(Time.valueOf("15:10:00"));
            when(rs.next()).thenReturn(true).thenReturn(false);
            when(stmt.executeQuery()).thenReturn(rs);

            mensa = mensaDAO.doRetrieveMensaByKey(nome);

            assertEquals(nome, mensa.get(0));
        }
        catch (Exception ignored)
        {}
    }
/*

    @Test
    public void doUpdateTest()
    {
        ArrayList<String> mensa= mensaDAO.doRetrieveMensaByKey("Mensa1");
        String nome=mensa.get(0);
        int postiDisponibili= Integer.parseInt(mensa.get(1));
        Time orarioApertura= Time.valueOf(mensa.get(2));
        Time orarioChiusura= Time.valueOf(mensa.get(3));


        int postiDisponibiliAggiornati=postiDisponibili+1;
        Time orarioAperturaAggiornato= Time.valueOf(mensa.get(2)+200000);
        Time orarioChiusuraAggiornato= Time.valueOf(mensa.get(3)+200000);


        mensaDAO.doUpdate(nome,postiDisponibiliAggiornati,orarioAperturaAggiornato,orarioChiusuraAggiornato);

        ArrayList<String> mensaAggiornata= mensaDAO.doRetrieveMensaByKey("Mensa1");

        assertEquals(mensaAggiornata.get(0),mensa.get(0));
        assertNotEquals(mensaAggiornata.get(1),mensa.get(1));
        assertNotEquals(mensaAggiornata.get(2),mensa.get(2));
        assertNotEquals(mensaAggiornata.get(3),mensa.get(3));
    }
*/
}
