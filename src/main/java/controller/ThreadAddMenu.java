package controller;

import model.MensaDAO;
import model.MensaDAOInterface;


import java.sql.Time;
import java.util.ArrayList;

public class ThreadAddMenu extends Thread
{
    public void run()
    {
        Time actualtime=new Time(System.currentTimeMillis());
        MensaDAOInterface mensadao=new MensaDAO();
        ArrayList<String> mensa=mensadao.doRetrieveMensaByKey("mensa1");
        Time chiusura= Time.valueOf(mensa.get(3));
        long attesa=chiusura.getTime()-actualtime.getTime();
        System.out.println(new Time(attesa));
        try
        {
            System.out.println("Attesa per aggiungere il menu");
           // Thread.sleep(attesa+10000);
            Thread.sleep(3000);
            System.out.println("Attesa Terminata, aggiunta in corso");

        }
            catch (InterruptedException ignored)
        {}

    }
}
