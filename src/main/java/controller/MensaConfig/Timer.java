package controller.MensaConfig;

import model.*;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Questa classe estende TimerTask. Come tale contiene solo un metodo run()
 */
public class Timer extends TimerTask {

    /**
     * Metodo che resetta la variabile postiVuoti in mensa su quella postiDisponibili in database
     */
    public void run(){

        System.out.println("run Timer");
        System.out.println(Mensa.isMensaConfig());

        if(Mensa.isMensaConfig())
        {
            MenuDAOInterface menudao=new MenuDAO();
            ArrayList<Menu> listAddMenu=Menu.getListAddMenu();
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
        }
    }
}
