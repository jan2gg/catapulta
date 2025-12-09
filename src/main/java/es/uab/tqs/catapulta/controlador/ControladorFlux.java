package es.uab.tqs.catapulta.controlador;

public class ControladorFlux {
    private final ControladorJoc controlador;

    public ControladorFlux(ControladorJoc controlador) {
        this.controlador = controlador;
    }

    public void run() {
        System.out.println("Benvingut a Catapulta!");
        controlador.setConstruccions();
        controlador.iniciaJoc();
        controlador.mostrarTauler();

        boolean jocActiu = true;
        int intents = 0;

        while (jocActiu) {
            int[] coords = controlador.obtenirCoodenades();
            int x = coords[0];
            int y = coords[1];
            try {
                controlador.jugadaUsuari(x, y);
                intents++;
                controlador.mostrarTauler();
                if (controlador.jocFinalitzat()) {
                    System.out.println("\n¡Felicitats! Has destruït totes les construccions en " + intents + " intents!");
                    jocActiu = false;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Joc finalitzat. Fins aviat!");
    }
}
