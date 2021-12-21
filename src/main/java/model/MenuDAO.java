package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe DAO implementa un'interfaccia per l'interrogazione al database per i metodi maggiormente usati di Menu
 */
public class MenuDAO implements MenuDAOInterface
{

    /** Restituisce una lista con tutti i menu salvati
     * @post {@literal List=menu->asSet()}
     * @return Lista di tutti i menu salvati
     */
    public List<Menu> doRetrieveAllmenu()
    {
        List<Menu> list = new ArrayList<>();
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

    /** Restituisce il menu con la chiave inserita, se presente, altrimenti restituisce un oggetto Menu vuoto.
     * @pre {@literal codiceMenu!=null}
     * @post {@literal menu->select(m|m.codiceMenu=codiceMenu)}
     * @param codiceMenu Codice che identifica univocamente un menu
     * @return Menu con la chiave richiesta
     */
    public Menu doRetrieveMenuByKey(int codiceMenu)
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


    /** Salva nel database un menu
     * @pre {@literal menu.codiceMenu!=null && menu.nome!=null && menu.primo!=null && menu.secondo!=null && menu.dessert!=null
     * && menu.descrizione!=null && menu.immagine!=null && menu.prezzo!=null && menu.available!=null
     * && !(menu->includes(menu))}
     * @post {@literal Menu (empty) || menu->includes(menu)}
     * @param menu il menu da salvare in database
     */
    public void doSave(Menu menu)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("INSERT INTO menu (codiceMenu,nome,primo,secondo,dessert,descrizione,immagine,prezzo,available) VALUES(?,?,?,?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, menu.getCodiceMenu());
            ps.setString(2,menu.getNome());
            ps.setString(3, menu.getPrimo());
            ps.setString(4,menu.getSecondo());
            ps.setString(5, menu.getDessert());
            ps.setString(6,menu.getDescrizione());
            ps.setString(7, menu.getImmagine());
            ps.setFloat(8,menu.getPrezzo());
            ps.setBoolean(9,menu.isAvailable());
            if (ps.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /** Elimina un menu
     * @pre {@literal codiceMenu!=null && menu->exists(m|m.codiceMenu=codiceMenu)}
     * @post {@literal !menu->exist(m|m.codiceMenu!=codiceMenu)}
     * @param codiceMenu la chiave del menu da eliminare
     */
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


    /** Stabilisce la visibilità del menu per i clienti
     * @pre {@literal codiceMenu!=null && bool!=null && menu->exists(m|m.codiceMenu==codiceMenu)}
     * @post {@literal menu->exists(m|m.codiceMenu==codiceMenu && m.available==bool)}
     * @param codiceMenu chiave del menu da aggiornare
     * @param bool booleano che stabilisce se il menu è visibile ai clienti
     */
    public void doUpdateAvailable(int codiceMenu, Boolean bool)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps = con.prepareStatement
                    ("UPDATE menu SET available=? WHERE codiceMenu=?",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, bool);
            ps.setInt(2, codiceMenu);
            ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
