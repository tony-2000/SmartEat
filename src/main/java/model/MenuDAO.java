package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO
{
    public List<Menu> doRetrieveAllmenu()
    {
        List<Menu> list = new ArrayList<Menu>();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM menu");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Menu p = new Menu();
                list.add(p);
                p.setCodiceMenu(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setPrimo(rs.getString(3));
                p.setSecondo(rs.getString(4));
                p.setDessert(rs.getString(5));
                p.setDescrizione(rs.getString(6));
                p.setImmagine(rs.getString(7));
                p.setPrezzo(rs.getFloat(8));
                p.setAvailable(rs.getBoolean(9));
            }
            return list;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    public Menu doRetrieveMenuByKey(int codiceMenu) throws NumberFormatException
    {
        Menu p = new Menu();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("SELECT * FROM menu WHERE codiceMenu=?");
            ps.setInt(1,codiceMenu);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                p.setCodiceMenu(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setPrimo(rs.getString(3));
                p.setSecondo(rs.getString(4));
                p.setDessert(rs.getString(5));
                p.setDescrizione(rs.getString(6));
                p.setImmagine(rs.getString(7));
                p.setPrezzo(rs.getFloat(8));
                p.setAvailable(rs.getBoolean(9));
            }
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void doSave(Menu cat)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("INSERT INTO menu (codiceMenu,nome,primo,secondo,dessert,descrizione,immagine,prezzo,available) VALUES(?,?,?,?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cat.getCodiceMenu());
            ps.setString(2,cat.getNome());
            ps.setString(3, cat.getPrimo());
            ps.setString(4,cat.getSecondo());
            ps.setString(5, cat.getDessert());
            ps.setString(6,cat.getDescrizione());
            ps.setString(7, cat.getImmagine());
            ps.setFloat(8,cat.getPrezzo());
            ps.setBoolean(9,cat.isAvailable());
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void doDelete(int codiceMenu)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("Delete FROM menu WHERE codiceMenu=?",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, codiceMenu);
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doUpdate(Menu temp)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("UPDATE menu SET nome=?, primo=?, secondo=?, dessert=?, descrizione=?, immagine=?, prezzo=?, available=? WHERE codiceMenu=?",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, temp.getNome());
            ps.setString(2, temp.getPrimo());
            ps.setString(3, temp.getSecondo());
            ps.setString(4, temp.getDessert());
            ps.setString(1, temp.getDescrizione());
            ps.setString(2, temp.getImmagine());
            ps.setFloat(3, temp.getPrezzo());
            ps.setBoolean(4, temp.isAvailable());
            ps.setInt(4, temp.getCodiceMenu());
            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
