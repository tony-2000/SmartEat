package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * Questa classe DAO implementa un'interfaccia per l'interrogazione al database per i metodi più usati di Mensa
 */
public class MensaDAO implements MensaDAOInterface
{

    /**
     * @pre {@literal nome!=null}
     * @post {@literal mensa->select(m|m.nome=nome)}
     * @param nome Il della mensa. La indentifica univocamente.
     * @return La mensa con la chiave richiesta
     */
    public ArrayList<String> doRetrieveMensaByKey(String nome)
    {
        ArrayList<String> cat = new ArrayList<>();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("SELECT * FROM mensa WHERE nome=?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                cat.add(rs.getString(1));
                cat.add(String.valueOf(rs.getInt(2)));
                cat.add(String.valueOf(rs.getTime(3)));
                cat.add(String.valueOf(rs.getTime(4)));
            }
            return cat;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @pre {@literal temp.nome!=null && temp.postiDisponibili!=null && temp.orarioApertura!=null && temp.orarioChiusura!=null
     * && mensa->exists(m|m.nome=temp.nome)}
     * @post {@literal mensa->includes(temp)}
     * Mensa con nuove informazioni da aggiornare.
     */

    public void doUpdate(String nome, int postiDisponibili, Time orarioApertura, Time orarioChiusura)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("UPDATE mensa SET postiDisponibili=?, orarioApertura=?, orarioChiusura=? WHERE nome=?",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, postiDisponibili);
            ps.setTime(2, orarioApertura);
            ps.setTime(3, orarioChiusura);
            ps.setString(4,nome);
            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
