import controller.Login;
import controller.SignUp;
import model.Utente;
import model.UtenteDAO;
import model.UtenteDAOInterface;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginServletTest {

    Login servlet = new Login();

    @Test
    public void ServletTest1() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(request.getParameter("mail")).thenReturn("alba@gmail.com");
        Mockito.when(request.getParameter("password")).thenReturn("P4ssword");
        Mockito.when(request.getRequestDispatcher(Mockito.any(String.class))).thenReturn(dispatcher);

        UtenteDAOInterface udao = Mockito.mock(UtenteDAO.class);
        Mockito.when(udao.doRetrieveUtenteByEmailPassword("alba@gmail.com","P4ssword")).thenReturn(new Utente());

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        Mockito.verify(request, Mockito.atLeast(1)).getParameter("mail");
        Mockito.verify(request, Mockito.atLeast(1)).getParameter("password");
        writer.flush();
        assertTrue(stringWriter.toString().contains("Dati utente scorretti"));

    }
}
