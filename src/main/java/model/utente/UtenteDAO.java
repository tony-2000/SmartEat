package model.utente;

import model.utils.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe DAO implementa un'interfaccia per l'interrogazione al database per i metodi maggiormente usati di Utente.
 */
public class UtenteDAO implements UtenteDAOInterface
{

    /** Restituisce una lista con tutti gli utenti salvati.
     * @post {@literal List=utente->asSet()}
     * @return Lista di tutti gli utenti salvati
     */
    public List<Utente> doRetrieveAllUtente()
    {
        List<Utente> list = new ArrayList<>();
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
                boolean us=(rs.getBoolean(10));
                if(us)
                    u.setAmministratore(new RuoloAdmin());
                else
                    u.setAmministratore(new RuoloStandard());
                u.setAccepted(rs.getBoolean(11));
                list.add(u);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Restituisce l'utente con la chiave specificata, se presente, altrimenti restituisce un oggetto Utente vuoto.
     * @pre {@literal CF!=null}
     * @post {@literal Utente (empty) || utente->select(u|u.codiceFiscale==CF)}
     * @param CF Codice fiscale dell'utente. Lo identifica univocamente.
     * @return Utente con la chiave specificata.
     */
    public Utente doRetrieveUtenteByKey(String CF)
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
                boolean us=(rs.getBoolean(10));
                if(us)
                    u.setAmministratore(new RuoloAdmin());
                else
                    u.setAmministratore(new RuoloStandard());
                u.setAccepted(rs.getBoolean(11));
            }
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Salva un utente in database.
     * @pre {@literal u.codiceFiscale!=null && u.nome!=null && u.cognome!=null && u.sesso!=null && u.dataDiNascita!=null, u.luogoDiNascita!=null
     * && u.email!=null && u.residenza!=null && u.password!=null && u.amministratore!=null && u.accepted!=null
     * && !(utente->includes(u))}
     * @post {@literal utente->includes(u)}
     * @param u Utente da salvare in database.
     */
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
            RuoloUtente us=u.isAmministratore();
            ps.setBoolean(10,us.isAdmin());
            ps.setBoolean(11, u.isAccepted());
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Elimina un utente.
     * @pre {@literal CF!=null && utente->exists(u|u.codiceFiscale==CF)}
     * @post {@literal !(utente->exists(u|u.codiceFiscale==CF))}
     * @param CF Codice fiscale dell'utente da eliminare.
     */
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

    /** Aggiorna tutte le informazioni dell'utente
     * @pre {@literal u.codiceFiscale!=null && u.nome!=null && u.cognome!=null && u.sesso!=null && u.dataDiNascita!=null, u.luogoDiNascita!=null
     * && u.email!=null && u.residenza!=null && u.password!=null && u.amministratore!=null && u.accepted!=null
     * && utente->exists(ut|ut.codiceFiscale==u.codiceFiscale)}
     * @post {@literal utente->includes(u)}
     * @param u Utente con la stessa chiave di quello da aggiornare e le nuove informazioni
     */
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
            RuoloUtente us=u.isAmministratore();
            ps.setBoolean(9,us.isAdmin());
            ps.setBoolean(10, u.isAccepted());
            ps.setString(11, u.getCF());

            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    /** Aggiorna tutte e sole le informazioni dell'utente che questi può modificare.
     * @pre {@literal u.codiceFiscale!=null && u.nome!=null && u.cognome!=null && u.sesso!=null && u.dataDiNascita!=null, u.luogoDiNascita!=null
     * && && u.residenza!=null && u.password!=null && utente->exists(ut|ut.codiceFiscale==u.codiceFiscale)}
     * @post {@literal utente->exists(ut|ut.nome==u.nome && ut.cognome==u.cognome && ut.sesso==u.sesso && ut.codiceFiscale==u.codiceFiscale && ut.dataDiNascita==u.dataDiNascita
     * && ut.luogoDiNascita==u.luogoDiNascita && ut.residenza==u.residenza && ut.password==u.password)}
     * @param u Utente con la stessa chiave di quello da aggiornare e le nuove informazioni.
     */
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

    /** Restituisce un utente con email e password specificate, se presente, altrimenti restituisce un oggetto Utente vuoto.
     * @pre {@literal email!=null && password!=null}
     * @post {@literal Utente (empty) || utente->select(u|u.email=email && u.password==password)}
     * @param email Email dell'utente
     * @param password Password dell'utente
     * @return Utente con email e password specificate.
     */
    public Utente doRetrieveUtenteByEmailPassword(String email, String password)
    {
        Utente u = new Utente();
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE email=? AND password=SHA1(?)");
            ps.setString(1, email);
            ps.setString(2, password);
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
                boolean us=(rs.getBoolean(10));
                if(us)
                    u.setAmministratore(new RuoloAdmin());
                else
                    u.setAmministratore(new RuoloStandard());
                u.setAccepted(rs.getBoolean(11));
            }
            return u;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Accetta un utente.
     * @pre {@literal codiceFiscale!=null && utente->exists(u|u.codiceFiscale==codiceFiscale)}
     * @post {utente->exists(u|u.codiceFiscale==codiceFiscale && u.accepted==true)}
     * @param codiceFiscale codice fiscale dell'utente da accettare.
     */
    public void doAccept(String codiceFiscale)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("UPDATE utente SET accepted=true WHERE codiceFiscale=?",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,codiceFiscale);

            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

}
