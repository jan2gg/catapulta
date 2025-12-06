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
        for (int i = 0; i < tauler.length; i++) {
            assertEquals(5, tauler[i].length);
        }

        // Verifica que totes les posicions són inicialment buides.
        for (int[] fila : tauler) {
            for (int cell : fila) {
                assertEquals(0, cell); // '0' representa un espai buit
            }
        }
    }

    @Test
    public void testAddConstruccio() {
        // Cas 1: Afegir primera construcció
        ModelConstruccio construccio = new ModelConstruccio();
        model.addConstruccio(construccio);
        assertEquals(1, model.getConstruccions().size());
        
        // Cas 2: Afegir segona construcció
        ModelConstruccio altraConstruccio = new ModelConstruccio();
        model.addConstruccio(altraConstruccio);
        assertEquals(2, model.getConstruccions().size());
        
        // Cas 3: Afegir construcció fora del tauler
        //ModelConstruccio construccioForaDeRang = new ModelConstruccio();
        //assertThrows(IllegalArgumentException.class, () -> model.addConstruccio(construccioForaDeRang));
    }

    @Test
    public void testAtac() {
        boolean resultat = model.atac(2, 2);
        assertFalse(resultat);

        // Valors límit i frontera
        assertFalse(model.atac(0, 0)); // Cantonada superior esquerra
        assertFalse(model.atac(0, 4)); // Cantonada superior dreta
        assertFalse(model.atac(4, 0)); // Cantonada inferior esquerra
        assertFalse(model.atac(4, 4)); // Cantonada inferior dreta
        assertFalse(model.atac(-1, -1));    // Fora de límits negatiu
        assertFalse(model.atac(5, 5)); // Fora de límits positiu
    }
}
