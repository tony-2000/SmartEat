package model.pietanza;

import model.utils.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Questa classe DAO implementa un'interfaccia per l'interrogazione al database per i metodi maggiormente usati di Pietanza.
 */
public class PietanzaDAO implements PietanzaDAOInterface
{
    /** Restituisce la lista delle pietanze salvate.
     * @post {@literal List=pietanza->asSet()}
     * @return Lista di tutte le pietanze salvate
     */
    public List<Pietanza> doRetrieveAllPietanza()
    {
        List<Pietanza> list = new ArrayList<>();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pietanza");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pietanza p = new Pietanza();
                p.setNome(rs.getString(1));
                p.setDescrizione(rs.getString(2));
                p.setTipo(rs.getString(3).charAt(0));
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

    /** Restituisce una pietanza con la chiave inserita, se presente, altrimenti restituisce un oggetto Pietanza vuoto.
     * @pre {@literal nome!=null}
     * @post {@literal pietanza->select(p|p.nome=nome)}
     * @param Nome Il nome della pietanza. La identifica univocamente.
     * @return Pietanza con la chiave inserita
     */
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
                pi.setTipo(rs.getString(3).charAt(0));
                pi.setIngredienti(rs.getString(4));
                pi.setImmagine(rs.getString(5));
                pi.setNumeroAcquisti(rs.getInt(6));
            }
            return pi;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Salva una pietanza in database.
     * @pre {@literal p.nome!=null && p.descrizione!=null && p.tipo!=null && p.ingredienti!=null && p.immagine!=null && p.numeroAcquisti!=null
     * && !(pietanza->includes(p))}
     * @post {@literal pietanza->includes(p)}
     * @param p Pietanza da salvare in database
     */
    public void doSave(Pietanza p)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("INSERT INTO pietanza (nome, descrizione, tipo, ingredienti, immagine, numeroAcquisti) VALUES(?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNome());
            ps.setString(2,p.getDescrizione());
            ps.setString(3, Character.toString(p.getTipo()));
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

    /** Elimina una pietanza dal database
     * @pre {@literal Nome!=null && pietanza->exists(piet|piet.nome==nome)}
     * @post {@literal !(pietanza->exists(piet|piet.nome=nome))}
     * @param Nome Il nome della pietanza da eliminare
     */
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

    /** Aggiorna le informazioni di una pietanza
     * @pre {@literal p.nome!=null && p.descrizione!=null && p.tipo!=null && p.ingredienti!=null && p.immagine!=null && p.numeroAcquisti!=null
     * && pietanza->exists(piet|piet.nome=p.nome)}
     * @post {@literal pietanza->includes(p)}
     * @param p Pietanza con le informazioni aggiornate
     */
    public void doUpdate(Pietanza p)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("UPDATE pietanza SET descrizione=?, tipo=?, " +
                            "ingredienti=?, immagine=?, numeroAcquisti=? WHERE nome=?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getDescrizione());
            ps.setString(2, Character.toString(p.getTipo()));
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
