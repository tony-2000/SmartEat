package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    public List<Utente> doRetrieveAllUtente()
    {
        List<Utente> list = new ArrayList<Utente>();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utente u = new Utente();
                u.setCF(rs.getString(1));
                u.setNome(rs.getString(2));
                u.setCognome(rs.getString(3));
                u.setSesso(rs.getString(4).toCharArray()[0]);
                u.setDataDiNascita(rs.getDate(5));
                u.setLuogoDiNascita(rs.getString(6));
                u.setEmail(rs.getString(7));
                u.setResidenza(rs.getString(8));
                u.setPasswordHash(rs.getString(9));
                u.setAmministratore(rs.getBoolean(10));
                u.setAccepted(rs.getBoolean(11));
                list.add(u);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrievePietanzaByKey(String CF)
    {
        Utente u = new Utente();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE codiceFiscale=?");
            ps.setString(1, CF);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u.setCF(rs.getString(1));
                u.setNome(rs.getString(2));
                u.setCognome(rs.getString(3));
                u.setSesso(rs.getString(4).toCharArray()[0]);
                u.setDataDiNascita(rs.getDate(5));
                u.setLuogoDiNascita(rs.getString(6));
                u.setEmail(rs.getString(7));
                u.setResidenza(rs.getString(8));
                u.setPasswordHash(rs.getString(9));
                u.setAmministratore(rs.getBoolean(10));
                u.setAccepted(rs.getBoolean(11));
            }
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Utente u)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("INSERT INTO utente(CODICEFISCALE, NOME, COGNOME, SESSO, DATADINASCITA, LUOGODINASCITA, " +
                                    "EMAIL, RESIDENZA, PASSWORD, AMMINISTRATORE, ACCEPTED) VALUES(?,?,?,?,?,?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getCF());
            ps.setString(2,u.getNome());
            ps.setString(3, u.getCognome());
            ps.setString(4, Character.toString(u.getSesso()));
            ps.setDate(5, u.getDataDiNascita());
            ps.setString(6,u.getLuogoDiNascita());
            ps.setString(7, u.getEmail());
            ps.setString(8,u.getResidenza());
            ps.setString(9, u.getPassword());
            ps.setBoolean(10,u.isAmministratore());
            ps.setBoolean(11, u.isAccepted());
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
                    ("Delete FROM utente WHERE codiceFiscale=?",
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

    public void doUpdate(Utente u)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("UPDATE utente SET nome=?, cognome=?, sesso=?, dataDiNascita=?, luogoDiNascita=?, email=?, residenza=?, password=?, amministratore=?, accepted=? WHERE codiceFiscale=?",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, Character.toString(u.getSesso()));
            ps.setDate(4, u.getDataDiNascita());
            ps.setString(5,u.getLuogoDiNascita());
            ps.setString(6, u.getEmail());
            ps.setString(7,u.getResidenza());
            ps.setString(8, u.getPassword());
            ps.setBoolean(9,u.isAmministratore());
            ps.setBoolean(10, u.isAccepted());
            ps.setString(11, u.getCF());

            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateUtenteInfo(Utente u)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("UPDATE utente SET nome=?, cognome=?, sesso=?, dataDiNascita=?, luogoDiNascita=?, residenza=?, password=? WHERE codiceFiscale=?",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, Character.toString(u.getSesso()));
            ps.setDate(4, u.getDataDiNascita());
            ps.setString(5,u.getLuogoDiNascita());
            ps.setString(6,u.getResidenza());
            ps.setString(7, u.getPassword());
            ps.setString(8, u.getCF());

            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Utente doRetrieveUtenteByEmailPassword(String email, String password)
    {
        Utente u = new Utente();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u.setCF(rs.getString(1));
                u.setNome(rs.getString(2));
                u.setCognome(rs.getString(3));
                u.setSesso(rs.getString(4).toCharArray()[0]);
                u.setDataDiNascita(rs.getDate(5));
                u.setLuogoDiNascita(rs.getString(6));
                u.setEmail(rs.getString(7));
                u.setResidenza(rs.getString(8));
                u.setPasswordHash(rs.getString(9));
                u.setAmministratore(rs.getBoolean(10));
                u.setAccepted(rs.getBoolean(11));
            }
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
