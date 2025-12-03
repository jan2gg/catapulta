package es.uab.tqs.catapulta.controlador;

import static org.junit.jupiter.api.Assertions.*;

import es.uab.tqs.catapulta.model.ModelJoc;
import es.uab.tqs.catapulta.vista.VistaJoc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ControladorJocTest {

    private ModelJoc model;
    private VistaJoc vista;
    private ControladorJoc controlador;

    @BeforeEach
    public void setUp() {
        model = new ModelJoc(5, 5);
        vista = Mockito.mock(VistaJoc.class);
        controlador = new ControladorJoc(model, vista);
    }

    @Test
    public void testIniciaJoc() {
        controlador.iniciaJoc();

        // Verifica que el tauler s'ha inicialitzat i que la vista se ha configurat
        assertEquals(5, model.getTauler().length);
        Mockito.verify(vista).mostraTauler(Mockito.any());
        Mockito.verify(vista).mostraMissatges(null);
    }

    @Test
    public void testJugadaUsuari() {
        // Cas 1: Atacar una posició vàlida
        boolean resultat = controlador.jugadaUsuari(2, 2);
        assertFalse(resultat);

        // Cas 2: Coordenades valors fora de límits
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(-1, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(5, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(0, 6));
        assertThrows(IndexOutOfBoundsException.class, () -> controlador.jugadaUsuari(6, 0));
        
        // Cas 3: Coordenades de valors frontera
        assertFalse(controlador.jugadaUsuari(0, 0)); // Cantonada superior esquerra
        assertFalse(controlador.jugadaUsuari(0, 4)); // Cantonada superior dreta
        assertFalse(controlador.jugadaUsuari(4, 0)); // Cantonada inferior esquerra
        assertFalse(controlador.jugadaUsuari(4, 4)); // Cantonada inferior dreta
    }

    @Test
    public void testSetConstruccions() {
        // Lògica per provar la configuració de construccions

        // Cas 1: Establir construccions correctament
        controlador.setConstruccions();
        assertEquals(0, model.getConstruccions().size());

        // Cas 2: Intentar establir construccions sense inicialitzar el model
        ModelJoc emptyModel = new ModelJoc(0, 0);
        ControladorJoc emptyControlador = new ControladorJoc(emptyModel, vista);
        assertThrows(Exception.class, () -> emptyControlador.setConstruccions());
    }
}

