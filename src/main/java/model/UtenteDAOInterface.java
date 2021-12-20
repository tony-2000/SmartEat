package model;

import java.util.List;

public interface UtenteDAOInterface
{
     List<Utente> doRetrieveAllUtente();
     Utente doRetrieveUtenteByKey(String CF);
     void doSave(Utente u);
     void doDelete(String CF);
     void doUpdate(Utente u);
     void doUpdateUtenteInfo(Utente u);
     Utente doRetrieveUtenteByEmailPassword(String email, String password);
}
