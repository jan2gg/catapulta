package es.uab.tqs.catapulta.controlador;

import es.uab.tqs.catapulta.model.ModelJoc;
import es.uab.tqs.catapulta.vista.VistaJoc;

public class ControladorJoc {
    private ModelJoc model;
    private VistaJoc vista;

    public ControladorJoc(ModelJoc model, VistaJoc vista) {
        this.model = model;
        this.vista = vista;
    }

    public void iniciaJoc() {
        // Lògica per iniciar el joc, incloent el bucle del joc
    }

    public void jugadaUsuari(int x, int y) {
    }

    public void setConstruccions() {
        // Lògica per establir les construccions al tauler
    }
}
