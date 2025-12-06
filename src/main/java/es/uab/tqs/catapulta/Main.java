package es.uab.tqs.catapulta;

import es.uab.tqs.catapulta.controlador.ControladorJoc;
import es.uab.tqs.catapulta.model.ModelJoc;
import es.uab.tqs.catapulta.vista.VistaJoc;

public class Main {
    public static void main(String[] args) {
        // Crear el model, vista i controlador
        ModelJoc model = new ModelJoc(5, 5);  // Tauler de 5x5
        VistaJoc vista = new VistaJoc();
        ControladorJoc controlador = new ControladorJoc(model, vista);
        
        // Iniciar el joc
        System.out.println("Benvingut a Catapulta!");
        controlador.iniciaJoc();
        
        // Bucle principal del joc
        boolean jocActiu = true;
        while (jocActiu) {
            vista.jugadaUsuari();
            // Precessar la jugada de l'usuari
            // Per ara només mostrem un missatge
            System.out.println("Jugada processada.");
        }
    }
}
