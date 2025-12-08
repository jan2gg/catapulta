package es.uab.tqs.catapulta.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelJocTest {
    private ModelJoc model;
    private ModelConstruccio construccio;

    @BeforeEach
    public void setUp() {
        model = new ModelJoc(5, 5);
        construccio = new ModelConstruccio(2, 2, 1, 1);
    }

    @Test
    public void testInicialitzaTauler() {
        int[][] tauler = model.getTauler();
        assertEquals(5, tauler.length);
        assertEquals(5, tauler[0].length);
        for (int[] fila : tauler) {
            for (int cell : fila) {
                assertEquals(0, cell);
            }
        }
    }

    @Test
    public void testAddConstruccio() {
        // Particions equivalents
        model.addConstruccio(construccio);
        assertEquals(1, model.getConstruccions().size());
        assertTrue(model.existeixConstruccioEnPosicio(1, 1));
        
        // Valors límit i frontera
        model.addConstruccio(new ModelConstruccio(1, 1, 0, 0));
        assertEquals(2, model.getConstruccions().size());
        model.addConstruccio(new ModelConstruccio(3, 3, 4, 4));
        assertEquals(3, model.getConstruccions().size());
        
        // Decision coverage - null
        assertThrows(IllegalArgumentException.class, () -> model.addConstruccio(null));
    }

    @Test
    public void testAtac() {
        // Particions equivalents - casella buida
        assertFalse(model.atac(2, 2));
        assertTrue(model.estaCasellAtacada(2, 2));
        
        // Valors límit i frontera - cantonades
        assertFalse(model.atac(0, 0));
        assertFalse(model.atac(0, 4));
        assertFalse(model.atac(4, 0));
        assertFalse(model.atac(4, 4));
        
        // Valors límit i frontera - fora límits
        assertFalse(model.atac(-1, -1));
        assertFalse(model.atac(-1, 2));
        assertFalse(model.atac(2, -1));
        assertFalse(model.atac(5, 5));
        assertFalse(model.atac(5, 2));
        assertFalse(model.atac(2, 5));
        
        // Decision coverage - casella atacada
        assertFalse(model.atac(2, 2));
        
        // Decision coverage - construcció present
        model.addConstruccio(construccio);
        assertTrue(model.atac(1, 1));
        assertEquals(1, construccio.getCopsRebuts());
        
        // Condition coverage - múltiples condicions
        assertFalse(model.atac(3, 3));
        model.atac(3, 3);
        assertFalse(model.atac(3, 3));
        
        // Path coverage - pairwise
        model.addConstruccio(new ModelConstruccio(2, 2, 2, 2));
        assertTrue(model.atac(2, 2));
        assertTrue(model.atac(3, 3));
        
        // Loop testing - bucles interiores
        assertFalse(model.atac(1, 0));
    }

    @Test
    public void testTotesConstruccionsDemolides() {
        // Particions equivalents - cap demolida
        model.addConstruccio(construccio);
        assertFalse(model.totesConstruccionsDemolides());
        
        // Valors límit - una demolida parcialment
        construccio.rebeCop();
        assertFalse(model.totesConstruccionsDemolides());
        
        // Decision coverage - totes demolides
        construccio.rebeCop();
        construccio.rebeCop();
        construccio.rebeCop();
        assertTrue(model.totesConstruccionsDemolides());
        
        // Loop testing - múltiples construccions
        model.addConstruccio(new ModelConstruccio(1, 1, 0, 0));
        assertFalse(model.totesConstruccionsDemolides());
        model.getConstruccions().get(1).rebeCop();
        assertTrue(model.totesConstruccionsDemolides());
    }
}
