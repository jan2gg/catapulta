package es.uab.tqs.catapulta.controlador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

import java.util.Scanner;
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
    public void testIniciaJoc() {
        // Particions equivalents - iniciaJoc executa operacions correctament
        controlador.iniciaJoc();
        verify(vista).mostraTauler(any());
        verify(vista).mostraMissatges("Joc iniciat. Comença a atacar!");
    }

    @Test
    public void testJugadaUsuari() {
        // Valors límit i frontera - jugadaUsuari fora de límits
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(0, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(5, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(0, 5));

        // Particions equivalents - jugadaUsuari aigua (casella buida)
        assertFalse(controlador.jugadaUsuari(1, 1));
        verify(vista, atLeastOnce()).mostraMissatges(contains("Mala sort"));

        // Decision/Condition coverage - casella ja atacada
        assertFalse(controlador.jugadaUsuari(1, 1));

        // Pairwise + decision coverage - jugadaUsuari encert (construcció colpejada)
        model.addConstruccio(new ModelConstruccio(1, 1, 2, 2));
        assertTrue(controlador.jugadaUsuari(2, 2));
        verify(vista, atLeastOnce()).mostraMissatges(contains("Atac correcte"));
    }

    @Test
    public void testMostrarTauler() {
        // Loop testing - mostrarTauler recorre tot el tauler correctament
        controlador.mostrarTauler();
        verify(vista, atLeast(1)).mostraTauler(any());
        
        // Particions equivalents - after construcció afegida
        model.addConstruccio(new ModelConstruccio(1, 1, 0, 0));
        controlador.mostrarTauler();
        verify(vista, atLeast(2)).mostraTauler(any());
    }

    @Test
    public void testSetConstruccions() {
        // Path coverage - setConstruccions correcte afegeix les construccions predefinides
        controlador.setConstruccions();
        assertEquals(5, model.getConstruccions().size());
        
        // Valors límit - setConstruccions sobre tauler buit (branch coverage)
        ControladorJoc buit = new ControladorJoc(new ModelJoc(0, 0), vista);
        assertThrows(IllegalStateException.class, buit::setConstruccions);
    }

    @Test
    public void testJocFinalitzat() {
        // Particions equivalents - joc no finalitzat (construccions intactes)
        controlador.setConstruccions();  // Afegeix les 5 construccions primer
        assertFalse(controlador.jocFinalitzat());
        
        // Path coverage - jocFinalitzat (demolir totes les construccions)
        // Les 5 construccions estan en: (0,0), (0,2), (2,2), (3,1), (4,3)
        int[][] coords = {{0, 0}, {0, 2}, {2, 2}, {3, 1}, {4, 3}};
        for (int[] c : coords) {
            controlador.jugadaUsuari(c[0], c[1]);
        }
        assertTrue(controlador.jocFinalitzat());
    }

    @Test
    public void testObtenirCoodenades() {
        // Particions equivalents - obtenirCoodenades retorna coordenades valides
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextInt()).thenReturn(2, 3);
        VistaJoc vistaReal = new VistaJoc(scanner);
        ControladorJoc controladorReal = new ControladorJoc(model, vistaReal);
        
        int[] coords = controladorReal.obtenirCoodenades();
        assertArrayEquals(new int[]{2, 3}, coords);
        
        // Valors límit i frontera - coordenades límit
        scanner = mock(Scanner.class);
        when(scanner.nextInt()).thenReturn(0, 4);
        vistaReal = new VistaJoc(scanner);
        controladorReal = new ControladorJoc(model, vistaReal);
        
        coords = controladorReal.obtenirCoodenades();
        assertArrayEquals(new int[]{0, 4}, coords);
    }
}
