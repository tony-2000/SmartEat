package model;

import java.sql.Time;
import java.util.ArrayList;

public interface MensaDAOInterface
{
     ArrayList<String> doRetrieveMensaByKey(String nome);
     void doUpdate(String nome, int postiDisponibili, Time orarioApertura, Time orarioChiusura);
}
