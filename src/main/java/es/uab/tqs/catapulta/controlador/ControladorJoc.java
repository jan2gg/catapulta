package es.uab.tqs.catapulta.controlador;

import es.uab.tqs.catapulta.model.ModelJoc;
import es.uab.tqs.catapulta.model.ModelConstruccio;
import es.uab.tqs.catapulta.vista.VistaJoc;

// Coordina la interacció entre el model del joc i la vista de consola.
public class ControladorJoc {
    // Estat intern del joc i canal de sortida/entrada amb l'usuari.
    private ModelJoc model;
    private VistaJoc vista;

    // Injecció de dependències per treballar amb el model i la vista.
    public ControladorJoc(ModelJoc model, VistaJoc vista) {
        this.model = model;
        this.vista = vista;
    }

    // Inicialitza la partida i mostra l'estat inicial del tauler.
    public void iniciaJoc() {
        mostrarTauler();
        vista.mostraMissatges("Joc iniciat. Comença a atacar!");
    }

    public boolean jugadaUsuari(int x, int y) {
        // Processa una jugada d'usuari (ús habitual i proves unitàries).
        int[][] tauler = model.getTauler();

        // Cas límit (frontera): coordenades fora del tauler -> excepció controlada.
        if (x < 0 || x >= tauler.length || y < 0 || y >= tauler[0].length) {
            throw new IndexOutOfBoundsException("Coordenades fora de límits");
        }

        // Partició equivalent: casella ja atacada, no es torna a processar.
        if (model.estaCasellAtacada(x, y)) {
            return false;
        }

        boolean atac = model.atac(x, y);

        // Feedback de caixa negra a l'usuari segons l'èxit de l'atac.
        if (atac) {
            vista.mostraMissatges("Atac correcte! Has colpejat una construcció.");
        } else {
            vista.mostraMissatges("Mala sort! No hi ha res en aquesta posició.");
        }

        return atac;
    }

    public int[] obtenirCoodenades() {
        // Entrada d'usuari delegada a la vista (pot ser simulada en tests).
        return vista.obtenirCoordenades();
    }

    public void setConstruccions() {
        // Configura l'escenari inicial amb construccions 1x1 per a un tauler 5x5.
        int[][] tauler = model.getTauler();

        // Precondició: el tauler ha d'estar inicialitzat abans de col·locar peces.
        if (tauler.length == 0 || tauler[0].length == 0) {
            throw new IllegalStateException("El tauler no està inicialitzat correctament");
        }

        // Distribució fixa per facilitar proves reproductibles (particions equivalents).
        model.addConstruccio(new ModelConstruccio(1, 1, 0, 0));  // Posició (0, 0)
        model.addConstruccio(new ModelConstruccio(1, 1, 0, 2));  // Posició (0, 2)
        model.addConstruccio(new ModelConstruccio(1, 1, 2, 2));  // Posició (2, 2)
        model.addConstruccio(new ModelConstruccio(1, 1, 3, 1));  // Posició (3, 1)
        model.addConstruccio(new ModelConstruccio(1, 1, 4, 3));  // Posició (4, 3)
    }

    public boolean jocFinalitzat() {
        // Condició d'aturada: totes les construccions estan demolides.
        return model.totesConstruccionsDemolides();
    }

    public void mostrarTauler() {
        // Converteix l'estat intern a símbols i el mostra via la vista.
        int[][] tauler = model.getTauler();
        boolean[][] atacades = model.getAtacades();
        char[][] taulerChar = converteixTauler(tauler, atacades);
        vista.mostraTauler(taulerChar);
    }

    private char[][] converteixTauler(int[][] tauler, boolean[][] atacades) {
        // Tradueix l'estat del model a símbols: ~ sense atacar, O aigua, X encert.
        char[][] taulerChar = new char[tauler.length][tauler[0].length];
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                if (!atacades[i][j]) {
                    taulerChar[i][j] = '~'; // Casella no atacada
                } else if (tauler[i][j] == 0) {
                    taulerChar[i][j] = 'O'; // Atac fallat (casella buida)
                } else {
                    taulerChar[i][j] = 'X'; // Construcció colpejada
                }
            }
        }
        return taulerChar;
    }
}
