package model;

import java.util.List;


/**
 * Questa interfaccia realizza il Pattern DAO per la classe TesseraDAO
 */
public interface TesseraDAOInterface
{
     Tessera doRetrieveTesseraByKey(String CF);
     void doSave(Tessera t);
     void doDelete(String CF);
     void doUpdate(Tessera t);
}
