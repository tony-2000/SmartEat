package controller.MensaConfig;

import model.Mensa;
import model.MensaDAO;

import java.util.ArrayList;
import java.util.TimerTask;

public class MensaTimer extends TimerTask {

    public void run(){
        if(!Mensa.mensaAperta())
        {
            MensaDAO dao=new MensaDAO();
            ArrayList<String> mensa=dao.doRetrieveMensaByKey("mensa1");
            Mensa.setPostiVuoti(Integer.parseInt(mensa.get(1)));
            System.out.println(Mensa.getPostiVuoti());
        }
    }
}
