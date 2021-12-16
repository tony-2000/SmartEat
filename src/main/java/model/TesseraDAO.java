package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TesseraDAO {

    //doRetrieveAll
    //doRetrieveByKey
    //doSave
    //doDelete
    //doUpdate

    public List<Tessera> doRetrieveAllTessera()
    {
        List<Tessera> list = new ArrayList<Tessera>();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tessera");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tessera p = new Tessera();
                p.setCF(rs.getString(1));
                p.setSaldo(rs.getFloat(2));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tessera doRetrieveTesseraByKey(String CF)
    {
        Tessera t = new Tessera();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tessera WHERE codiceFiscale=?");
            ps.setString(1, CF);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t.setCF(rs.getString(1));
                t.setSaldo(rs.getFloat(2));
            }
            return t;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Tessera t)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("INSERT INTO tessera (codiceFiscale, saldo) VALUES(?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getCF());
            ps.setFloat(2,t.getSaldo());
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String CF)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("Delete FROM tessera WHERE codiceFiscale=?",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, CF);
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Tessera t)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("UPDATE tessera SET saldo=? WHERE codiceFiscale=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, t.getSaldo());
            ps.setString(2, t.getCF());

            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}