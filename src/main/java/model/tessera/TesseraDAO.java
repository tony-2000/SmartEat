package model.tessera;

import model.utils.ConPool;

import java.sql.*;

/**
 * Questa classe DAO implementa un'interfaccia per l'interrogazione al database per i metodi maggiormente usati di Tessera
 */
public class TesseraDAO implements TesseraDAOInterface
{
    /** Restituisce la tessera con la chiave richiesta, se presente, altrimenti restituisce un oggetto Tessera vuoto.
     * @pre {@literal CF!=null}
     * @post {@literal Tessera (empty) || tessera->select(t|t.codiceFiscale==CF)}
     * @param CF Codice fiscale. Identifica univocamente una tessera.
     * @return Tessera con la chiave richiesta
     */
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

    /** Salva una tessera in database
     * @pre {@literal t.codiceFiscale!=null && t.saldo!=null && !(tessera->includes(t))}
     * @post {@literal tessera->includes(t)}
     * @param t Tessera da salvare in database
     */
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

    /** Elimina la tessera con una chiave
     * @pre {@literal CF!=null && tessera->exists(t|t.codiceFiscale==CF)}
     * @post {@literal !(tessera->exists(t|t.codiceFiscale==CF))}
     * @param CF codice fiscale della tessera da eliminare
     */
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

    /** Aggiorna le informazioni di una tessera
     * @pre {@literal t.codiceFiscale!=null && t.saldo!=null && tessera->exists(tes|tes.codiceFiscale==t.codiceFiscale)}
     * @post {@literal tessera->includes(t)}
     * @param t tessera con le informazioni aggiornate
     */
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
