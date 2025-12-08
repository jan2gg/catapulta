package es.uab.tqs.catapulta.vista;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VistaJocTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testObtenirCoordenades() {
        // Particions equivalents + valors frontera + loop + decision coverage:
        // Primer input invàlid (mismatch) -> es neteja buffer
        // Després entrada vàlida (2,3)
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextInt())
            .thenThrow(new InputMismatchException()) // invàlid
            .thenReturn(2, 3);                       // parella vàlid

        VistaJoc vista = new VistaJoc(scanner);
        int[] coords = vista.obtenirCoordenades();

        assertArrayEquals(new int[]{2, 3}, coords);
        verify(scanner, times(3)).nextInt(); // 1 error + 2 lectures bones
        verify(scanner, times(1)).nextLine(); // neteja del buffer després de l'excepció
    }

    @Test
    void testMostraMissatges() {
        // Particions equivalents + frontera (null / buit) + decision coverage
        VistaJoc vista = new VistaJoc(new Scanner(System.in));
        vista.mostraMissatges(null);
        vista.mostraMissatges("");
        vista.mostraMissatges("Hola");

        String output = outContent.toString();
        assertFalse(output.contains("null"));
        assertFalse(output.isEmpty());        // hi ha sortida
        assertTrue(output.contains("Hola"));  // missatge vàlid es mostra
    }

    @Test
    void testMostraTauler() {
        // Particions equivalents + loop testing filas/columnes + frontera tauler 2x2
        VistaJoc vista = new VistaJoc(new Scanner(System.in));
        char[][] tauler = {
            {'A', 'B'},
            {'C', 'D'}
        };

        vista.mostraTauler(tauler);

        String out = outContent.toString();
        // Capçalera amb columnes
        assertTrue(out.contains("0"));
        assertTrue(out.contains("1"));
        // Contingut del tauler
        assertTrue(out.contains("A"));
        assertTrue(out.contains("B"));
        assertTrue(out.contains("C"));
        assertTrue(out.contains("D"));
        // Separadors
        assertTrue(out.contains("-"));
        assertTrue(out.contains("|"));
    }

    @Test
    void testJugadaUsuari() {
        // Particions equivalents: lectura directa de dos enters
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextInt()).thenReturn(4, 0); // valors frontera

        VistaJoc vista = new VistaJoc(scanner);
        vista.jugadaUsuari(); // no retorna, però ha de llegir dues vegades

        verify(scanner, times(2)).nextInt();
        String out = outContent.toString();
        assertTrue(out.contains("Fila"));
        assertTrue(out.contains("Columna"));
    }
}
