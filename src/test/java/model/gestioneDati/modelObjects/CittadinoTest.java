package model.gestioneDati.modelObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CittadinoTest {
    private Cittadino cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
            "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
    @Test
    void testCF() {
        cittadino.setCF("NPLPQL97B13A509L");
            assertEquals("NPLPQL97B13A509L", cittadino.getCF());
    }

    @Test
    void testNome() {
        cittadino.setNome("Pasquale");
        assertEquals("Pasquale", cittadino.getNome());
    }

    @Test
    void testCognome() {
        cittadino.setCognome("Napolitano");
        assertEquals("Napolitano", cittadino.getCognome());
    }

    @Test
    void testPwd() {
        cittadino.setPwd("Pippotto8.");
        assertEquals("Pippotto8.", cittadino.getPwd());
    }

    @Test
    void testVia() {
        cittadino.setVia("autostrada");
        assertEquals("autostrada", cittadino.getVia());
    }

    @Test
    void testCvicio() {
        cittadino.setCivico(23);
        assertEquals(23, cittadino.getCivico());
    }

    @Test
    void testCitta() {
        cittadino.setCitta("Bologna");
        assertEquals("Bologna", cittadino.getCitta());
    }

    @Test
    void testEmail() {
        cittadino.setEmail("asd@gmail.com");
        assertEquals("asd@gmail.com", cittadino.getEmail());
    }

    @Test
    void testNumSegnalazioni() {
        cittadino.setNumSegnalazioni(40);
        assertEquals(40, cittadino.getNumSegnalazioni());
    }

    @Test
    void testNumSegnAppr() {
        cittadino.setNumSegnApp(10);
        assertEquals(10, cittadino.getNumSegnApp());
    }

    @Test
    void testToString() {
        assertEquals("Cittadino{"
                + "email='" + cittadino.getEmail() + '\''
                + ", pwd='" + cittadino.getPwd() + '\''
                + ", CF='" + cittadino.getCF() + '\''
                + ", nome='" + cittadino.getNome() + '\''
                + ", cognome='" + cittadino.getCognome() + '\''
                + ", via='" + cittadino.getVia() + '\''
                + ", civico='" + cittadino.getCivico() + '\''
                + ", città='" + cittadino.getCitta() + '\''
                + ", numSegnalazioni=" + cittadino.getNumSegnalazioni()
                + ", numSegnApp=" + cittadino.getNumSegnApp()
                + '}', cittadino.toString());
    }

    @Test
    void testEqualsTrue() {
        assertEquals(true, cittadino.equals(cittadino));
    }

    @Test
    void testEqualsFalse() {
        assertEquals(false, cittadino.equals(null));
    }

    @Test
    void testEqualsFalse2() {
        assertEquals(false, cittadino.equals(new Impiegato()));
    }

    @Test
    void testEqualsFalse3() {
        Cittadino cittadino2 = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Baronissi","cattaneo@gmail.com",0,0);
        assertEquals(false, cittadino.equals(cittadino2));
    }
}
