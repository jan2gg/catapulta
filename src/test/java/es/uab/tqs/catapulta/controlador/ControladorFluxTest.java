package es.uab.tqs.catapulta.controlador;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControladorFluxTest {

    private ByteArrayOutputStream out;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testRun() {
        // Mock del controlador
        ControladorJoc controlador = mock(ControladorJoc.class);

        // Configura 4 lectures (la 4a no s'usarà però prevé error)
        when(controlador.obtenirCoodenades())
                .thenReturn(new int[]{-1, -1}, new int[]{1, 1}, new int[]{2, 2}, new int[]{3, 3});
        
        // Primera iteració: error de coordenades
        // Segona iteració: jugada vàlida però joc no finalitzat
        // Tercera iteració: jugada vàlida i joc finalitzat
        when(controlador.jocFinalitzat())
                .thenReturn(false, false, true);

        // Primera crida llança excepció, les altres retornen true
        doThrow(new IndexOutOfBoundsException("Coordenades fora de límits"))
            .doReturn(true)
            .doReturn(true)
            .doReturn(true)
            .when(controlador).jugadaUsuari(anyInt(), anyInt());

        ControladorFlux runner = new ControladorFlux(controlador);
        runner.run();

        // Verifica les interaccions clau del flux
        verify(controlador, times(1)).setConstruccions();
        verify(controlador, times(1)).iniciaJoc();
        verify(controlador, atLeast(1)).mostrarTauler();
        verify(controlador, times(4)).obtenirCoodenades();  // 4 cops
        verify(controlador, times(4)).jugadaUsuari(anyInt(), anyInt());  // 4 cops
        verify(controlador, times(3)).jocFinalitzat();  // 3 cops

        // Verifica que els missatges de sortida són correctes
        String output = out.toString();
        assertTrue(output.contains("Benvingut a Catapulta!"));
        assertTrue(output.contains("Joc finalitzat"));
    }
}
