import model.utente.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class UtenteDAOTest
{

    UtenteDAO utenteDAO;

    @Before
    public void setup() {
        utenteDAO = new UtenteDAO();
    }

    @Test
    public void doRetrieveAllUtenteTest()
    {
        ArrayList<Utente> list= (ArrayList<Utente>) utenteDAO.doRetrieveAllUtente();
        for(Utente x: list)
            assertNotNull(x.getCF());
    }


    @Test
    public void doRetrieveUtenteByKeyTest()
    {
        String CF="RSSMRA74D22A0011";
        Utente user;
        user=utenteDAO.doRetrieveUtenteByKey(CF);
        assertEquals(CF,user.getCF());
    }

    @Test
    public void doSaveTest()
    {
        String CF="RSSMRA74D22A0122";
        String nome="tempNome";
        String cognome="tempCognome";
        char sesso='M';
        Date nascita=Date.valueOf("2021-12-10");
        String luogoNascita="Napoli";
        String mail="aaaaaa@linn.comm";
        String residenza="Napoli";
        String password="AAAAAAnnnnnn7777.";
        boolean accepted=false;
        RuoloStandard RuoloUtente = new RuoloStandard();
        Utente user=new Utente();
        user.setCF(CF);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setSesso(sesso);
        user.setDataDiNascita(nascita);
        user.setLuogoDiNascita(luogoNascita);
        user.setEmail(mail);
        user.setResidenza(residenza);
        user.setPasswordHash(password);
        model.utente.RuoloUtente standard = new RuoloStandard();
        user.setAmministratore(standard);
        user.setAccepted(false);
        utenteDAO.doSave(user);

        Utente newUtente;
        newUtente=utenteDAO.doRetrieveUtenteByKey(CF);

        assertEquals(user.getCF(),newUtente.getCF());
        assertEquals(user.getNome(),newUtente.getNome());
        assertEquals(user.getCognome(),newUtente.getCognome());
        assertEquals(user.getSesso(),newUtente.getSesso());
        assertEquals(user.getDataDiNascita(),newUtente.getDataDiNascita());
        assertEquals(user.getLuogoDiNascita(),newUtente.getLuogoDiNascita());
        assertEquals(user.getEmail(),newUtente.getEmail());
        assertEquals(user.getResidenza(),newUtente.getResidenza());
        assertEquals(user.isAmministratore().isAdmin(),newUtente.isAmministratore().isAdmin());
        assertEquals(user.isAccepted(),newUtente.isAccepted());
    }


    @Test
    public void doDeleteTest()
    {
        String CF="RSSMRA74D22A0013";
        utenteDAO.doDelete(CF);

        Utente utente2;
        utente2=utenteDAO.doRetrieveUtenteByKey(CF);
        assertNull(utente2.getCF());
    }


    @Test
    public void doUpdateTest()
    {
        String CF="RSSMRA74D22A0012";
        Utente oldUser= utenteDAO.doRetrieveUtenteByKey(CF);
        Utente newUser=new Utente();
        newUser.setCF(oldUser.getCF());
        newUser.setNome(oldUser.getNome()+"temèii");
        newUser.setCognome(oldUser.getCognome()+"temèeee");
        newUser.setSesso('F');
        newUser.setDataDiNascita(Date.valueOf("2021-12-13"));
        newUser.setLuogoDiNascita(oldUser.getLuogoDiNascita()+"rrrr");
        newUser.setEmail(oldUser.getEmail()+"eeeeeeee");
        newUser.setResidenza(oldUser.getResidenza()+"rrrvvvvv");
        newUser.setPasswordHash(oldUser.getPassword()+"457b");
        RuoloUtente admin = new RuoloAdmin();
        newUser.setAmministratore(admin);
        newUser.setAccepted(!oldUser.isAccepted());

        utenteDAO.doUpdate(newUser);

        Utente newUser2;
        newUser2=utenteDAO.doRetrieveUtenteByKey(CF);

        assertEquals(newUser2.getCF(),oldUser.getCF());
        assertNotEquals(newUser2.getNome(),oldUser.getNome());
        assertNotEquals(newUser2.getCognome(),oldUser.getCognome());
        assertNotEquals(newUser2.getResidenza(),oldUser.getResidenza());
        assertNotEquals(newUser2.getLuogoDiNascita(),oldUser.getLuogoDiNascita());
        assertNotEquals(newUser2.isAccepted(),oldUser.isAccepted());
    }

    @Test
    public void doUpdateUtenteInfoTest()
    {
        String CF="RSSMRA74D22A0013";
        Utente oldUser= utenteDAO.doRetrieveUtenteByKey(CF);
        Utente newUser=new Utente();
        newUser.setCF(oldUser.getCF());
        newUser.setNome(oldUser.getNome()+"temèii");
        newUser.setCognome(oldUser.getCognome()+"temèeee");
        newUser.setSesso('F');
        newUser.setDataDiNascita(Date.valueOf("2021-12-13"));
        newUser.setLuogoDiNascita(oldUser.getLuogoDiNascita()+"rrrr");
        newUser.setEmail(oldUser.getEmail());
        newUser.setResidenza(oldUser.getResidenza()+"rrrvvvvv");
        newUser.setPasswordHash(oldUser.getPassword()+"457b");
        newUser.setAmministratore(oldUser.isAmministratore());
        newUser.setAccepted(oldUser.isAccepted());

        utenteDAO.doUpdate(newUser);

        Utente newUser2;
        newUser2=utenteDAO.doRetrieveUtenteByKey(CF);

        assertEquals(newUser2.getCF(),oldUser.getCF());
        assertNotEquals(newUser2.getNome(),oldUser.getNome());
        assertNotEquals(newUser2.getCognome(),oldUser.getCognome());
        assertNotEquals(newUser2.getResidenza(),oldUser.getResidenza());
        assertNotEquals(newUser2.getLuogoDiNascita(),oldUser.getLuogoDiNascita());
        assertEquals(newUser2.isAccepted(),oldUser.isAccepted());
        assertEquals(newUser2.isAmministratore().isAdmin(),oldUser.isAmministratore().isAdmin());
    }

    @Test
    public void doRetrieveUtenteByEmailPasswordTest()
    {
        String mail="Luigi.Green@gmail.com";
        String password="Mario.001";
        Utente user;
        user=utenteDAO.doRetrieveUtenteByEmailPassword(mail,password);
        assertNotNull(user.getCF());
        assertEquals(mail,user.getEmail());
        assertNotNull(user.getPassword());
    }

    @Test
    public void doAcceptTest()
    {
        String CF="RSSMRA74D22A0014";
        Utente user=utenteDAO.doRetrieveUtenteByKey(CF);
        utenteDAO.doAccept(CF);
        Utente newUser=utenteDAO.doRetrieveUtenteByKey(CF);
        assertEquals(newUser.getCF(),user.getCF());
        assertTrue(newUser.isAccepted());
    }

}
