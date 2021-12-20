package model;

import java.sql.Date;
import java.util.List;

/**
 * Questa interfaccia realizza il Pattern DAO per la classe AcquistoDAO
 */
public interface AcquistoDAOInterface
{
    List<Acquisto> doRetrieveAllAcquisto();
    Acquisto doRetrieveAcquistoByKey(Date dataAcquisto, String CF, int codiceMenu);
    void doSave(Acquisto acq);
    void doDelete(Date dataAcquisto, String CF, int codiceMenu);
    void doUpdate(Acquisto temp);
    List<Acquisto> doRetrieveAllAcquistoByCF(String CF);
}
