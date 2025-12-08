package es.uab.tqs.catapulta.controlador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import es.uab.tqs.catapulta.model.ModelConstruccio;
import es.uab.tqs.catapulta.model.ModelJoc;
import es.uab.tqs.catapulta.vista.VistaJoc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControladorJocTest {

    private ModelJoc model;
    private VistaJoc vista;
    private ControladorJoc controlador;

    @BeforeEach
    public void setUp() {
        model = new ModelJoc(5, 5);
        vista = mock(VistaJoc.class);
        controlador = new ControladorJoc(model, vista);
    }

    @Test
    public void testControladorEnUnSolMetode() {
        // Particions equivalents - iniciaJoc
        controlador.iniciaJoc();
        verify(vista).mostraTauler(any());
        verify(vista).mostraMissatges("Joc iniciat. Comença a atacar!");

        // Valors límit i frontera - jugadaUsuari fora de límits
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(5, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(0, 5));

        // Particions equivalents - jugadaUsuari aigua
        assertFalse(controlador.jugadaUsuari(1, 1));
        verify(vista, atLeastOnce()).mostraMissatges(contains("Aigua"));

        // Decision/Condition coverage - casella ja atacada
        assertFalse(controlador.jugadaUsuari(1, 1));

        // Pairwise + decision coverage - jugadaUsuari encert
        model.addConstruccio(new ModelConstruccio(1, 1, 2, 2));
        assertTrue(controlador.jugadaUsuari(2, 2));
        verify(vista, atLeastOnce()).mostraMissatges(contains("Atac correcte"));

        // Loop testing - mostrarTauler recorre tot
        controlador.mostrarTauler();
        verify(vista, atLeast(2)).mostraTauler(any());

        // Path coverage - setConstruccions correcte
        controlador.setConstruccions();
        assertEquals(6, model.getConstruccions().size()); // 1 afegida manual + 5 predefinides

        // Path/exception - setConstruccions sobre tauler buit
        ControladorJoc buit = new ControladorJoc(new ModelJoc(0, 0), vista);
        assertThrows(IllegalStateException.class, buit::setConstruccions);

        // Path coverage - jocFinalitzat (demolir totes les construccions)
        ModelJoc model2 = new ModelJoc(5, 5);
        VistaJoc vista2 = mock(VistaJoc.class);
        ControladorJoc ctrl2 = new ControladorJoc(model2, vista2);
        ctrl2.setConstruccions();
        int[][] coords = {{0,0},{0,2},{2,2},{3,1},{4,3}};
        for (int[] c : coords) {
            ctrl2.jugadaUsuari(c[0], c[1]);
        }
        assertTrue(ctrl2.jocFinalitzat());
    }
}
