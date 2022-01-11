import controller.GestionePietanze.AdminPietanzeArea;
import model.pietanza.Pietanza;
import model.pietanza.PietanzaDAO;
import model.pietanza.PietanzaDAOInterface;
import model.utente.RuoloAdmin;
import model.utente.RuoloStandard;
import model.utente.Utente;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AdminPietanzeAreaTest
{
        AdminPietanzeArea adminPietanzeArea;
        PietanzaDAOInterface pdao;
        HttpSession session;

        @Before
        public void setup() {
            session = mock(HttpSession.class);
            pdao=mock(PietanzaDAO.class);
            adminPietanzeArea = new AdminPietanzeArea(pdao);
        }

        @Test
        public void AdminShowPietanzeTest()
        {
            ArrayList<Pietanza> lista=new ArrayList<>();
            when(pdao.doRetrieveAllPietanza()).thenReturn(lista);
            assertNotNull(lista);
        }

        @Test
        public void  AdminPietanzeAreaTestNonInSessione() throws IOException, ServletException {
            HttpServletResponse response= mock(HttpServletResponse.class);
            HttpServletRequest request= mock(HttpServletRequest.class);
            when(request.getSession()).thenReturn(session);

            when(session.getAttribute("utenteSessione")).thenReturn(null);
            when(request.getContextPath()).thenReturn("context");

            adminPietanzeArea.doGet(request,response);

            verify(response, atLeastOnce()).sendRedirect("context/index.jsp");
        }

        @Test
        public void  AdminPietanzeAreaTestNonAdmin() throws IOException, ServletException {
            HttpServletResponse response= mock(HttpServletResponse.class);
            HttpServletRequest request= mock(HttpServletRequest.class);
            when(request.getSession()).thenReturn(session);

            Utente user = new Utente();
            user.setAmministratore(new RuoloStandard());

            when(session.getAttribute("utenteSessione")).thenReturn(user);
            when(request.getContextPath()).thenReturn("context");

            adminPietanzeArea.doGet(request,response);

            verify(response, atLeastOnce()).sendRedirect("context/toHome");
        }


        @Test
        public void  AdminPietanzeAreaTestTrue() throws IOException, ServletException {
            HttpServletResponse response= mock(HttpServletResponse.class);
            HttpServletRequest request= mock(HttpServletRequest.class);
            RequestDispatcher dispatcher = mock(RequestDispatcher.class);
            when(request.getSession()).thenReturn(session);

            Utente user = new Utente();
            user.setCF("1234567890ABCDEF");
            user.setAmministratore(new RuoloAdmin());
            ArrayList<Pietanza> lista=new ArrayList<>();

            when(session.getAttribute("utenteSessione")).thenReturn(user);
            when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
            when(pdao.doRetrieveAllPietanza()).thenReturn(lista);

            adminPietanzeArea.doGet(request,response);

            verify(dispatcher, atLeastOnce()).forward(request, response);
            verify(request,atLeastOnce()).setAttribute(eq("listaPietanze"),any(ArrayList.class));
        }
}
