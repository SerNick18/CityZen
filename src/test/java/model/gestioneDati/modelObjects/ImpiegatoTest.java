package model.gestioneDati.modelObjects;

import controller.operazioni_cittadino.EliminaSegnalazione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImpiegatoTest {

    @Test
    void testImpiegatoCostruttoreVuoto() {
        Impiegato impiegato = new Impiegato();
        assertNotNull(impiegato);
    }

    @Test
    void testGetEmail() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals("Fiordellisi@scafati.it", impiegato.getEmail());
    }

    @Test
    void testSetEmail() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setEmail("alessioF@scafati.it");
        assertEquals("alessioF@scafati.it", impiegato.getEmail());
    }

    @Test
    void testGetMatricola() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals("12345", impiegato.getMatricola());
    }

    @Test
    void testSetMatricola() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setMatricola("54321");
        assertEquals("54321", impiegato.getMatricola());
    }

    @Test
    void testGetPwd() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals("AlessioF123", impiegato.getPwd());
    }

    @Test
    void testSetPwd() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setPwd("FAlessio321");
        assertEquals("FAlessio321", impiegato.getPwd());
    }

    @Test
    void testGetCF() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals("FRDLSS00C01A509O", impiegato.getCF());
    }

    @Test
    void testSetCF() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setCF("FLDLSS00C01A509P");
        assertEquals("FLDLSS00C01A509P", impiegato.getCF());
    }

    @Test
    void testGetNome() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals("Alessio", impiegato.getNome());
    }

    @Test
    void testSetNome() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setNome("Peppe");
        assertEquals("Peppe", impiegato.getNome());
    }

    @Test
    void testGetCognome() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals("Fiordellisi", impiegato.getCognome());
    }

    @Test
    void testSetCognome() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setCognome("Breesss");
        assertEquals("Breesss", impiegato.getCognome());
    }

    @Test
    void testGetVia() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals("Pirandello", impiegato.getVia());
    }

    @Test
    void testSetVia() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setVia("Leonardo");
        assertEquals("Leonardo", impiegato.getVia());
    }

    @Test
    void testGetCivico() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals(33, impiegato.getCivico());
    }

    @Test
    void testSetCivico() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setCivico(11);
        assertEquals(11, impiegato.getCivico());
    }

    @Test
    void testGetCitta() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals("Milano", impiegato.getCitta());
    }

    @Test
    void testSetCitta() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setCitta("Napoli");
        assertEquals("Napoli", impiegato.getCitta());
    }

    @Test
    void testGetNumSegnalazioniApp() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals(10, impiegato.getNumSegnalazioniApp());
    }

    @Test
    void testSetNumSegnalazioniApp() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setNumSegnalazioniApp(6);
        assertEquals(6, impiegato.getNumSegnalazioniApp());
    }

    @Test
    void testGetNumSegnalazioniChiuse() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals(7, impiegato.getNumSegnalazioniChiuse());
    }

    @Test
    void testSetNumSegnalazioniChiuse() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        impiegato.setNumSegnalazioniChiuse(2);
        assertEquals(2, impiegato.getNumSegnalazioniChiuse());
    }

    @Test
    void testToString() {
        Impiegato impiegato = new Impiegato("Fiordellisi@scafati.it", "12345", "AlessioF123", "FRDLSS00C01A509O", "Alessio", "Fiordellisi",
                "Pirandello", 33, "Milano", 10, 7);
        assertEquals("Impiegato{"
                + "email='" + impiegato.getEmail() + '\''
                + ", matricola='" + impiegato.getMatricola() + '\''
                + ", pwd='" + impiegato.getPwd() + '\''
                + ", CF='" + impiegato.getCF() + '\''
                + ", nome='" + impiegato.getNome() + '\''
                + ", cognome='" + impiegato.getCognome() + '\''
                + ", via='" + impiegato.getVia() + '\''
                + ", civico='" + impiegato.getCivico() + '\''
                + ", città='" + impiegato.getCitta() + '\''
                + ", numSegnalazioniApp=" + impiegato.getNumSegnalazioniApp()
                + ", numSegnalazioniChiuse=" + impiegato.getNumSegnalazioniChiuse()
                + '}', impiegato.toString());
    }
}