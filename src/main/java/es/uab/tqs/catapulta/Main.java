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
        
        // Establir les construccions al tauler
        controlador.setConstruccions();
        
        // Iniciar el joc i mostrar el tauler
        controlador.iniciaJoc();
        controlador.mostrarTauler();
        
        // Bucle principal del joc
        boolean jocActiu = true;
        int intentos = 0;
        
        while (jocActiu) {
            // Obtenir les coordenades del jugador
            int[] coordenades = controlador.obtenirCoodenades();
            int x = coordenades[0];
            int y = coordenades[1];
            
            // Processar la jugada del usuari
            try {
                controlador.jugadaUsuari(x, y);
                intentos++;
                
                // Mostrar el tauler actualitzat
                controlador.mostrarTauler();
                
                // Verificar si el joc ha finalitzat
                if (controlador.jocFinalitzat()) {
                    System.out.println("\n¡Felicitats! Has destruït totes les construccions en " + intentos + " intents!");
                    jocActiu = false;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        System.out.println("Joc finalitzat. Fins aviat!");
    }
}
