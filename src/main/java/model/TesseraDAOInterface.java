package model;

import java.util.List;

public interface TesseraDAOInterface
{
     List<Tessera> doRetrieveAllTessera();
     Tessera doRetrieveTesseraByKey(String CF);
     void doSave(Tessera t);
     void doDelete(String CF);
     void doUpdate(Tessera t);
}
