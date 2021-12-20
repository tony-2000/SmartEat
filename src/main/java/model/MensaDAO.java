package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe DAO implementa un'interfaccia per l'interrogazione al database per i metodi più usati di Mensa
 */
public class MensaDAO implements MensaDAOInterface
{

    public Mensa doRetrieveMensaByKey(String nome) throws NumberFormatException
    {
        Mensa cat = new Mensa();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("SELECT * FROM mensa WHERE nome=?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                cat.setNome(rs.getString(1));
                cat.setPostiDisponibili(rs.getInt(2));
                cat.setOrarioApertura(rs.getTime(3));
                cat.setOrarioChiusura(rs.getTime(4));
            }
            return cat;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Mensa temp)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("UPDATE mensa SET postiDisponibili=?, orarioApertura=?, orarioChiusura=? WHERE nome=?",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, temp.getPostiDisponibili());
            ps.setTime(2, temp.getOrarioApertura());
            ps.setTime(3, temp.getOrarioChiusura());
            ps.setString(4, temp.getNome());
            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
