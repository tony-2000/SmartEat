package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe DAO implementa un'interfaccia per l'interrogazione al database per i metodi maggiormente usati di Acquisto.
 */
public class AcquistoDAO implements AcquistoDAOInterface
{

    /** Restituisce una lista con tutti gli acquisti salvati.
     * @post {@literal List=acquisto->asSet()}
     * @return Lista di tutti gli acquisti salvati
     */
    public List<Acquisto> doRetrieveAllAcquisto()
    {
        List<Acquisto> list = new ArrayList<>();
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

    /** Restituisce l'acquisto con una chiave specifica, se presente, altrimenti restituisce un oggetto Acquisto vuoto.
     * @pre {@literal dataAcquisto!=null && CF!=null && codiceMenu!=null}
     * @post {@literal Acquisto (empty) || acquisto->select(Acquisto|Acquisto.dataAcquisto==dataAcquisto && Acquisto.codiceFiscale==codiceFiscale && Acquisto.codiceMenu==codiceMenu)}
     * @return Acquisto con la chiave richiesta, se presente
     */
    public Acquisto doRetrieveAcquistoByKey(Date dataAcquisto, String CF, int codiceMenu)
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


    /** Salva l'acquisto in database.
     * @pre {@literal acq.dataAcquisto!=null && acq.codiceFiscale!=null && acq.codiceMenu!=null && acq.postoMensa!=null &&
     * !(acquisto->includes(acq))}
     * @post {@literal acquisto->include(acq)}
     * @param acq Acquisto da salvare in database
     */
    public void doSave(Acquisto acq)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("INSERT INTO acquisto (dataAcquisto,codiceFiscale,codiceMenu,postoMensa) VALUES(?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, acq.getDataAcquisto());
            ps.setString(2,acq.getCF());
            ps.setInt(3, acq.getCodiceMenu());
            ps.setBoolean(4,acq.isPostoMensa());
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Elimina l'acquisto dal database.
     * @pre {@literal dataAcquisto!=null && CF!=null && codiceMenu!=null
     * && acquisto->exists(a|a.dataAcquisto==dataAcquisto && a.codiceFiscale==CF && a.codiceMenu==codiceMenu)}
     * @post {@literal !acquisto->exists(a|a.dataAcquisto==dataAcquisto && a.codiceFiscale==CF && a.codiceMenu==codiceMenu)}
     * @param dataAcquisto Data di acquisto
     * @param CF Codice fiscale
     * @param codiceMenu chiave che identifica il menu
     */
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

    /** Aggiorna il menu con quella chiave.
     * @pre {@literal temp.dataAcquisto!=null && temp.codiceFiscale!=null && temp.codiceMenu!=null && temp.postoMensa!=null
     * && acquisto->exists(a|a.dataAcquisto==temp.dataAcquisto && a.codiceFiscale==temp.codiceFiscale && a.codiceMenu==temp.codiceMenu)}
     * @post {@literal acquisto->includes(temp)}
     * @param temp Acquisto con la stessa chiave di quello da aggiornare e le nuove informazioni
     */
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

    /** Restituisce tutti gli acquisti fatti dall'utente.
     * @pre {@literal CF!=null}
     * @post {@literal List=acquisto->select(a|a.CF=CF)}
     * @param CF codice fiscale dell'utente di cui si vogliono gli acquisti
     * @return Lista di acquisti fatti dall'utente
     */
    public List<Acquisto> doRetrieveAllAcquistoByCF(String CF)
    {
        List<Acquisto> list = new ArrayList<>();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM acquisto where codiceFiscale=?");
            ps.setString(1, CF);
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

}
