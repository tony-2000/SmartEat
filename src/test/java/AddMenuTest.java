import controller.AddMenu;
import model.MenuDAO;
import model.MenuDAOInterface;
import org.junit.Before;
import org.mockito.Mockito;

import javax.servlet.http.HttpSession;

public class AddMenuTest extends Mockito
{
    private AddMenu addMenu;
    private MenuDAOInterface mdao;
    private HttpSession session;

    @Before
    public void setup()
    {
        mdao = mock(MenuDAO.class);
        session = mock(HttpSession.class);
        addMenu = new AddMenu(mdao, session);
    }

}
