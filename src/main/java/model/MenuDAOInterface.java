package model;

import java.util.List;

public interface MenuDAOInterface
{
     List<Menu> doRetrieveAllmenu();
     Menu doRetrieveMenuByKey(int codiceMenu);
     void doSave(Menu cat);
     void doDelete(int codiceMenu);
     void doUpdateAvailable(int codiceMenu, Boolean bool);
}
