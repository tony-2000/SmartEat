package controller.SmartEatConfig;

import model.mensa.Mensa;
import model.mensa.MensaDAO;
import model.mensa.MensaDAOInterface;
import model.menu.Menu;
import model.menu.MenuDAO;
import model.menu.MenuDAOInterface;
import model.pietanza.Pietanza;
import model.pietanza.PietanzaDAO;
import model.pietanza.PietanzaDAOInterface;

import java.sql.Time;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Questa classe estende TimerTask. Come tale contiene solo un metodo run()
 */
public class Timer extends TimerTask {

    /**
     * DAO di Menu
     */
    private final MenuDAOInterface menudao;
    /**
     * DAO di Mensa
     */
    private final MensaDAOInterface dao;
    /**
     * DAO di Pietanza
     */
    private final PietanzaDAOInterface pietanzaDao;

    /**
     * Costruttore Vuoto
     */
    public Timer() {
        super();
        menudao = new MenuDAO();
        dao=new MensaDAO();
        pietanzaDao=new PietanzaDAO();
    }

    /**Costruttore con parametri
     * @param dao DAO di Mensa
     * @param menudao DAO di Menu
     * @param pietanzaDao DAO di Pietanza
     */
    public Timer(MensaDAOInterface dao,MenuDAOInterface menudao,PietanzaDAOInterface pietanzaDao) {
        super();
        this.dao = dao;
        this.pietanzaDao=pietanzaDao;
        this.menudao=menudao;
    }

    /**
     * Metodo che resetta la variabile postiVuoti in mensa su quella postiDisponibili in database ed esegue
     * varie operazioni di aggiornamento per menu e pietanze.
     */
    public void run(){

        if(Mensa.isMensaConfig())
        {
            ArrayList<Menu> listAddMenu=Menu.getListAddMenu();
            ArrayList<Integer> listToggleMenu=Menu.getListToggleMenu();
            ArrayList<Integer> listDeleteMenu=Menu.getListDeleteMenu();
            ArrayList<String> listDeletePietanza= Pietanza.getListDeletePietanza();
            ArrayList<String> listUpdateMensa=Mensa.getListUpdateMensa();
            Mensa.setPostiVuoti(Mensa.mensa.getPostiDisponibili());
            System.out.println("Aggiornamento posti");

            while(!listAddMenu.isEmpty())
            {
                menudao.doSave(listAddMenu.get(0));
                listAddMenu.remove(0);
                System.out.println("aggiunto un menu");
            }

            while(!listToggleMenu.isEmpty())
            {
                int codiceMenu=listToggleMenu.get(0);
                boolean available=menudao.doRetrieveMenuByKey(codiceMenu).isAvailable();
                boolean change=!available;
                menudao.doUpdateAvailable(codiceMenu,change);
                listToggleMenu.remove(0);
            }

            while(!listDeleteMenu.isEmpty())
            {
                int codiceMenu=listDeleteMenu.get(0);
                menudao.doDelete(codiceMenu);
                listToggleMenu.remove(0);
            }

            while(!listDeletePietanza.isEmpty())
            {
                String nomePietanza=listDeletePietanza.get(0);
                pietanzaDao.doDelete(nomePietanza);
                listDeletePietanza.remove(0);
            }

            while(!listUpdateMensa.isEmpty())
            {
                String posti=listUpdateMensa.get(0);
                String apertura=listUpdateMensa.get(1);
                String chiusura=listUpdateMensa.get(2);
                dao.doUpdate("SmartEat",Integer.parseInt(posti), Time.valueOf(apertura),Time.valueOf(chiusura));
                int temp = Mensa.mensa.getPostiDisponibili()-Mensa.getPostiVuoti();
                Mensa.mensa.setPostiDisponibili(Integer.parseInt(posti));
                Mensa.setPostiVuoti(Mensa.mensa.getPostiDisponibili()-temp);
                Mensa.mensa.setOrarioApertura(Time.valueOf(apertura));
                Mensa.mensa.setOrarioChiusura(Time.valueOf(chiusura));
                listUpdateMensa.clear();
            }
        }
    }
}
