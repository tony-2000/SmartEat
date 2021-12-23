package controller.MensaConfig;

import model.*;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Questa classe estende TimerTask. Come tale contiene solo un metodo run()
 */
public class Timer extends TimerTask {

    /**
     * Metodo che resetta la variabile postiVuoti in mensa su quella postiDisponibili in database ed esegue
     * varie operazioni di aggiornamento per menu e pietanze.
     */
    public void run(){

        System.out.println("run Timer");
        System.out.println(Mensa.isMensaConfig());

        if(Mensa.isMensaConfig())
        {
            ArrayList<Menu> listAddMenu=Menu.getListAddMenu();
            ArrayList<Integer> listToggleMenu=Menu.getListToggleMenu();
            ArrayList<Integer> listDeleteMenu=Menu.getListDeleteMenu();
            MenuDAOInterface menudao=new MenuDAO();
            MensaDAO dao=new MensaDAO();
            ArrayList<String> mensa=dao.doRetrieveMensaByKey("mensa1");
            Mensa.setPostiVuoti(Integer.parseInt(mensa.get(1)));
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
        }
    }
}