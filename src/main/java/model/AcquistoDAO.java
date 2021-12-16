package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcquistoDAO
{

    public List<Acquisto> doRetrieveAllAcquisto()
    {
        List<Acquisto> list = new ArrayList<Acquisto>();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM acquisto");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Acquisto p = new Acquisto();
                p.setDataAcquisto(rs.getDate(1));
                p.setCF(rs.getString(2));
                p.setCodiceMenu(rs.getInt(3));
                p.setPostoMensa(rs.getBoolean(4));
                list.add(p);
            }
            return list;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    public Acquisto doRetrieveAcquistoByKey(Date dataAcquisto, String CF, int codiceMenu) throws NumberFormatException
    {
        Acquisto cat = new Acquisto();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM acquisto WHERE dataAcquisto=? AND codiceFiscale=? AND codiceMenu=?");
            ps.setDate(1, dataAcquisto);
            ps.setString(2, CF);
            ps.setInt(3, codiceMenu);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                cat.setDataAcquisto(rs.getDate(1));
                cat.setCF(rs.getString(2));
                cat.setCodiceMenu(rs.getInt(3));
                cat.setPostoMensa(rs.getBoolean(4));
            }
            return cat;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void doSave(Acquisto cat)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("INSERT INTO acquisto (dataAcquisto,codiceFiscale,codiceMenu,postoMensa) VALUES(?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, cat.getDataAcquisto());
            ps.setString(2,cat.getCF());
            ps.setInt(3, cat.getCodiceMenu());
            ps.setBoolean(4,cat.isPostoMensa());
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void doDelete(Date dataAcquisto, String CF, int codiceMenu)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("Delete FROM acquisto WHERE dataAcquisto=? AND codiceFiscale=? AND codiceMenu=?",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, dataAcquisto);
            ps.setString(2, CF);
            ps.setInt(3, codiceMenu);
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doUpdate(Acquisto temp)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("UPDATE acquisto SET postoMensa=? WHERE dataAcquisto=? AND codiceFiscale=? AND codiceMenu=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, temp.isPostoMensa());
            ps.setDate(2, temp.getDataAcquisto());
            ps.setString(3, temp.getCF());
            ps.setInt(4, temp.getCodiceMenu());
            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


}
