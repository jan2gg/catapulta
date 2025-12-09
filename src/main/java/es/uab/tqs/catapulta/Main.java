package es.uab.tqs.catapulta;

import es.uab.tqs.catapulta.controlador.ControladorJoc;
import es.uab.tqs.catapulta.controlador.ControladorFlux;
import es.uab.tqs.catapulta.model.ModelJoc;
import es.uab.tqs.catapulta.vista.VistaJoc;

// Punt d'entrada de l'aplicacio: crea model, vista i controlador i inicia el joc.
public class Main {
    public static void main(String[] args) {
        // Tauler 5x5 per a una partida rapida i senzilla de provar.
        ModelJoc model = new ModelJoc(5, 5);
        // Vista de consola per mostrar el tauler i llegir coordenades de l'usuari.
        VistaJoc vista = new VistaJoc();
        // Controlador que coordina la logica entre model i vista.
        ControladorJoc controlador = new ControladorJoc(model, vista);
        // Llança el flux principal del joc amb totes les dependencies preparades.
        new ControladorFlux(controlador).run();
    }
}
