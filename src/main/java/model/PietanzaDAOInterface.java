package model;

import java.util.List;

public interface PietanzaDAOInterface
{
     List<Pietanza> doRetrieveAllPietanza();
     Pietanza doRetrievePietanzaByKey(String Nome);
     void doSave(Pietanza p);
     void doDelete(String Nome);
     void doUpdate(Pietanza p);
     void doUpdateNumeroAcquisti(int na);

}
