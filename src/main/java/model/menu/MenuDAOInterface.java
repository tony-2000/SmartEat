package model.menu;

import java.util.List;


/**
 * Questa interfaccia realizza il Pattern DAO per la classe MenuDAO
 */
public interface MenuDAOInterface
{
     List<Menu> doRetrieveAllMenu();
     Menu doRetrieveMenuByKey(int codiceMenu);
     void doSave(Menu cat);
     void doDelete(int codiceMenu);
     void doUpdateAvailable(int codiceMenu, Boolean bool);
}
