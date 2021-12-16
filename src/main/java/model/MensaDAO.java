package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MensaDAO
{

    public List<Mensa> doRetrieveAllMensa()
    {
        List<Mensa> list = new ArrayList<Mensa>();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mensa");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mensa p = new Mensa();
                p.setNome(rs.getString(1));
                p.setPostiDisponibili(rs.getInt(2));
                p.setOrarioApertura(rs.getTime(3));
                p.setOrarioChiusura(rs.getTime(4));
                list.add(p);
            }
            return list;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


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



    public void doSave(Mensa cat)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("INSERT INTO mensa (nome,postiDisponibili,orarioApertura,orarioChiusura) VALUES(?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cat.getNome());
            ps.setInt(2,cat.getPostiDisponibili());
            ps.setTime(3, cat.getOrarioApertura());
            ps.setTime(4,cat.getOrarioChiusura());
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void doDelete(String nome)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("Delete FROM mensa WHERE nome=?",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nome);
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
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
