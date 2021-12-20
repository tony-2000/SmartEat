package model;

public interface MensaDAOInterface
{
    Mensa doRetrieveMensaByKey(String nome);
    void doUpdate(Mensa temp);
}
