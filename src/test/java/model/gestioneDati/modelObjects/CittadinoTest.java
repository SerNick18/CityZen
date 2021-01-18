package model.gestioneDati.modelObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CittadinoTest {
    private Cittadino cittadino = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
            "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);

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

    @Test
    void testEqualsFalse4() {
        Cittadino cittadino2 = new Cittadino("CPNLLD11S19A489S", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        assertEquals(false, cittadino.equals(cittadino2));
    }

    @Test
    void testEqualsFalse5() {
        Cittadino cittadino2 = new Cittadino("CPNLLD11S19A489D", "Pasquale", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        assertEquals(false, cittadino.equals(cittadino2));
    }

    @Test
    void testEqualsFalse6() {
        Cittadino cittadino2 = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Napolitano", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        assertEquals(false, cittadino.equals(cittadino2));
    }

    @Test
    void testEqualsFalse7() {
        Cittadino cittadino2 = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "Pippotto8.",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,0);
        assertEquals(false, cittadino.equals(cittadino2));
    }

    @Test
    void testEqualsFalse8() {
        Cittadino cittadino2 = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via gramsci",3,"Fisciano","cattaneo@gmail.com",0,0);
        assertEquals(false, cittadino.equals(cittadino2));
    }

    @Test
    void testEqualsFalse9() {
        Cittadino cittadino2 = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",6,"Fisciano","cattaneo@gmail.com",0,0);
        assertEquals(false, cittadino.equals(cittadino2));
    }

    @Test
    void testEqualsFalse10() {
        Cittadino cittadino2 = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","catt@gmail.com",0,0);
        assertEquals(false, cittadino.equals(cittadino2));
    }

    @Test
    void testEqualsFalse11() {
        Cittadino cittadino2 = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",1,0);
        assertEquals(false, cittadino.equals(cittadino2));
    }

    @Test
    void testEqualsFalse12() {
        Cittadino cittadino2 = new Cittadino("CPNLLD11S19A489D", "Giuseppe", "Cattaneo", "32ca9fc1a0f5b6330e3f4c8c1bbecde9bedb9573",
                "via roma",3,"Fisciano","cattaneo@gmail.com",0,1);
        assertEquals(false, cittadino.equals(cittadino2));
    }
}