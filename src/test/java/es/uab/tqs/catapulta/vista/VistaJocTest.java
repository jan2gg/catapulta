package es.uab.tqs.catapulta.vista;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class VistaJocTest {
    @Test
    public void testMockObtenirCoordenades() throws Exception {
        String entrada = "1\n2\n";
        var originalIn = System.in;
        var originalOut = System.out;
      
        try {
            var input = new java.io.ByteArrayInputStream(entrada.getBytes());
            var out = new java.io.ByteArrayOutputStream();
        
            System.setIn(input);
            System.setOut(new java.io.PrintStream(out));

            VistaJoc vista = new VistaJoc();
            int[] coords = vista.obtenirCoordenades();

            assertArrayEquals(new int[] {1, 2}, coords);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}