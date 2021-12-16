package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PietanzaDAO {

    public List<Pietanza> doRetrieveAllPietanza()
    {
        List<Pietanza> list = new ArrayList<Pietanza>();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pietanza");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pietanza p = new Pietanza();
                p.setNome(rs.getString(1));
                p.setDescrizione(rs.getString(2));
                p.setTipo(rs.getInt(3));
                p.setIngredienti(rs.getString(4));
                p.setImmagine(rs.getString(5));
                p.setNumeroAcquisti(rs.getInt(6));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pietanza doRetrievePietanzaByKey(String Nome)
    {
        Pietanza pi = new Pietanza();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pietanza WHERE nome=?");
            ps.setString(1, Nome);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pi.setNome(rs.getString(1));
                pi.setDescrizione(rs.getString(2));
                pi.setTipo(rs.getInt(3));
                pi.setIngredienti(rs.getString(4));
                pi.setImmagine(rs.getString(5));
                pi.setNumeroAcquisti(rs.getInt(6));
            }
            return pi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Pietanza p)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("INSERT INTO pietanza (nome, descrizione, tipo, ingredienti, immagine, numeroAcquisti) VALUES(?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNome());
            ps.setString(2,p.getDescrizione());
            ps.setInt(3, p.getTipo());
            ps.setString(4,p.getIngredienti());
            ps.setString(5, p.getImmagine());
            ps.setInt(6,p.getNumeroAcquisti());
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String Nome)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("Delete FROM pietanza WHERE nome=?",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Nome);
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Pietanza p)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("UPDATE pietanza SET descrizione=?, tipo=?, " +
                            "ingredienti=?, immagine=?, numeroAcquisti=? WHERE nome=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getDescrizione());
            ps.setInt(2, p.getTipo());
            ps.setString(3, p.getIngredienti());
            ps.setString(4, p.getImmagine());
            ps.setInt(5, p.getNumeroAcquisti());
            ps.setString(6, p.getNome());

            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
