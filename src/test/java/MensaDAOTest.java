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
import static org.mockito.Mockito.*;

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

    @Test
    public void doUpdateTest()
    {
        ArrayList<String> mensa =new ArrayList<>();
        mensa.add(0,"Mensa1");
        mensa.add(1,"499");
        mensa.add(2,"13:10:00");
        mensa.add(3,"15:10:00");


        int postiDisponibiliAggiornati = Integer.parseInt(mensa.get(1) + 1);
        Time orarioAperturaAggiornato = Time.valueOf(mensa.get(2) + 200000);
        Time orarioChiusuraAggiornato = Time.valueOf(mensa.get(3) + 200000);

        try {
            Mockito.mockStatic(ConPool.class);

            when(ConPool.getConnection()).thenReturn(c);
            when(c.prepareStatement(any(String.class))).thenReturn(stmt);

            when(rs.next()).thenReturn(true).thenReturn(false);
            when(stmt.executeQuery()).thenReturn(rs);

            mensaDAO.doUpdate(mensa.get(0), postiDisponibiliAggiornati, orarioAperturaAggiornato, orarioChiusuraAggiornato);
            verify(stmt,atLeastOnce()).executeUpdate();
        }
        catch (Exception ignored){}
    }
}
