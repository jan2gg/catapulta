package es.uab.tqs.catapulta;

import es.uab.tqs.catapulta.controlador.ControladorJoc;
import es.uab.tqs.catapulta.controlador.ControladorFlux;
import es.uab.tqs.catapulta.model.ModelJoc;
import es.uab.tqs.catapulta.vista.VistaJoc;

public class Main {
    public static void main(String[] args) {
        ModelJoc model = new ModelJoc(5, 5);
        VistaJoc vista = new VistaJoc();
        ControladorJoc controlador = new ControladorJoc(model, vista);
        new ControladorFlux(controlador).run();
    }
}
