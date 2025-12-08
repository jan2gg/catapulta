package es.uab.tqs.catapulta.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelConstruccioTest {
    private ModelConstruccio mc;

    @BeforeEach
    public void setUp() {
        mc = new ModelConstruccio();
    }

    @Test
    public void testConstructor() {
        // Particions equivalents - constructor per defecte
        assertEquals(1, mc.getMidaVertical());
        assertEquals(1, mc.getMidaHoritzontal());
        assertEquals(0, mc.getCopsRebuts());
        assertFalse(mc.estaDemolida());
        
        // Valors límit - paràmetres
        ModelConstruccio mc2 = new ModelConstruccio(2, 3, 1, 2);
        assertEquals(2, mc2.getMidaVertical());
        assertEquals(3, mc2.getMidaHoritzontal());
        assertEquals(1, mc2.getFilaInicial());
        assertEquals(2, mc2.getColumnaInicial());
        
        // Valors límit - origen
        assertEquals(0, mc.getFilaInicial());
        assertEquals(0, mc.getColumnaInicial());
        
        // Valors límit - negatius
        ModelConstruccio mcNeg = new ModelConstruccio(1, 1, -1, -5);
        assertEquals(-1, mcNeg.getFilaInicial());
        assertEquals(-5, mcNeg.getColumnaInicial());
    }

    @Test
    public void testRebeCop() {
        // Particions equivalents - no demolida
        assertFalse(mc.estaDemolida());
        mc.rebeCop();
        assertEquals(1, mc.getCopsRebuts());
        
        // Decision coverage - demolida
        assertTrue(mc.estaDemolida());
        int copsAnteriors = mc.getCopsRebuts();
        mc.rebeCop();
        assertEquals(copsAnteriors, mc.getCopsRebuts());
        
        // Valors límit - construcció 2x2
        ModelConstruccio mc2x2 = new ModelConstruccio(2, 2, 0, 0);
        assertFalse(mc2x2.estaDemolida());
        mc2x2.rebeCop();
        assertFalse(mc2x2.estaDemolida());
        mc2x2.rebeCop();
        mc2x2.rebeCop();
        assertFalse(mc2x2.estaDemolida());
        mc2x2.rebeCop();
        assertTrue(mc2x2.estaDemolida());
        
        // Condition coverage - condicions múltiples
        assertEquals(4, mc2x2.getCopsRebuts());
        
        // Loop testing - construcció 3x3
        ModelConstruccio mc3x3 = new ModelConstruccio(3, 3, 0, 0);
        for (int i = 0; i < 8; i++) {
            mc3x3.rebeCop();
            assertFalse(mc3x3.estaDemolida());
        }
        mc3x3.rebeCop();
        assertTrue(mc3x3.estaDemolida());
        
        // Path coverage - multiples paths
        ModelConstruccio mcPath = new ModelConstruccio(1, 2, 0, 0);
        mcPath.rebeCop();
        assertEquals(1, mcPath.getCopsRebuts());
        mcPath.rebeCop();
        assertTrue(mcPath.estaDemolida());
        mcPath.rebeCop();
        assertEquals(2, mcPath.getCopsRebuts());
        
        // Pairwise testing - combinacions
        ModelConstruccio mcPair = new ModelConstruccio(2, 1, 0, 0);
        assertFalse(mcPair.estaDemolida());
        mcPair.rebeCop();
        assertTrue(mcPair.estaDemolida());
    }
}
