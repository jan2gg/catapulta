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

    public boolean jugadaUsuari(int x, int y) {
        // Lògica per processar la jugada de l'usuari
        return false; // Retorna true si l'atac és un èxit, false en cas contrari
    }

    public void setConstruccions() {
        // Lògica per establir les construccions al tauler
    }
}
