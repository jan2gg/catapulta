package es.uab.tqs.catapulta.model;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelJocTest {

    private ModelJoc model;

    @BeforeEach
    public void setUp() {
        model = new ModelJoc(5, 5);
    }

    @Test
    public void testInicialitzaTauler() {
        int[][] tauler = model.getTauler();
        assertEquals(5, tauler.length);
        assertEquals(5, tauler[0].length);
    }

    @Test
    public void testAddConstruccio() {
        ModelConstruccio construccio = new ModelConstruccio();
        model.addConstruccio(construccio);
        assertEquals(1, model.getConstruccions().size());
    }

    @Test
    public void testAtacNoConstruccio() {
        boolean resultat = model.atac(2, 2);
        assertFalse(resultat);
    }
}
