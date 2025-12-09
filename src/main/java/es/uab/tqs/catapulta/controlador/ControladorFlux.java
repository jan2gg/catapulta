package es.uab.tqs.catapulta.controlador;

// Controla el flux principal del joc de Catapulta des de la consola.
public class ControladorFlux {
    // Controlador de la lògica del joc (configuració, jugades i finalització).
    private final ControladorJoc controlador;

    // Injecció del controlador per preparar el flux d'execució.
    public ControladorFlux(ControladorJoc controlador) {
        this.controlador = controlador;
    }

    // Execució principal: inicia el joc, demana jugades i compta intents fins acabar.
    public void run() {
        System.out.println("Benvingut a Catapulta!");
        controlador.setConstruccions();
        controlador.iniciaJoc();
        controlador.mostrarTauler();

        boolean jocActiu = true;
        int intents = 0;

        // Bucle de partida: rep jugades de l'usuari fins eliminar totes les construccions.
        while (jocActiu) {
            // Coordenades facilitades per l'usuari via consola.
            int[] coords = controlador.obtenirCoodenades();
            int x = coords[0];
            int y = coords[1];
            try {
                // Processa la jugada, incrementa intents i refresca el tauler.
                controlador.jugadaUsuari(x, y);
                intents++;
                controlador.mostrarTauler();
                if (controlador.jocFinalitzat()) {
                    // Cas d'èxit: totes les construccions han estat destruïdes.
                    System.out.println("\n¡Felicitats! Has destruït totes les construccions en " + intents + " intents!");
                    jocActiu = false;
                }
            } catch (IndexOutOfBoundsException e) {
                // Coordenades fora del tauler: s'informa i es demanen noves jugades.
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Joc finalitzat. Fins aviat!");
    }
}
