package model.pietanza;

import java.util.List;


/**
 * Questa interfaccia realizza il Pattern DAO per la classe PietanzaDAO
 */
public interface PietanzaDAOInterface
{
     List<Pietanza> doRetrieveAllPietanza();
     Pietanza doRetrievePietanzaByKey(String Nome);
     void doSave(Pietanza p);
     void doDelete(String Nome);
     void doUpdate(Pietanza p);

}
