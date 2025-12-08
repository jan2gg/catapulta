package es.uab.tqs.catapulta.controlador;

import es.uab.tqs.catapulta.model.ModelJoc;
import es.uab.tqs.catapulta.model.ModelConstruccio;
import es.uab.tqs.catapulta.vista.VistaJoc;

public class ControladorJoc {
    private ModelJoc model;
    private VistaJoc vista;

    public ControladorJoc(ModelJoc model, VistaJoc vista) {
        this.model = model;
        this.vista = vista;
    }

    public void iniciaJoc() {
        // Lògica per iniciar el joc
        mostrarTauler();
        vista.mostraMissatges("Joc iniciat. Comença a atacar!");
    }

    public boolean jugadaUsuari(int x, int y) {
        // Lògica per processar la jugada de l'usuari (per tests i ús general)
        int[][] tauler = model.getTauler();
        
        if (x < 0 || x >= tauler.length || y < 0 || y >= tauler[0].length) {
            throw new IndexOutOfBoundsException("Coordenades fora de límits");
        }
        
        if (model.estaCasellAtacada(x, y)) {
            return false; // Ja ha estat atacada
        }
        
        boolean atac = model.atac(x, y);
        
        if (atac) {
            vista.mostraMissatges("Atac correcte! Has colpejat una construcció!");
        } else {
            vista.mostraMissatges("Aigua! No hi ha res en aquesta posició.");
        }
        
        return atac;
    }

    public int[] obtenirCoodenades() {
        // Retorna les coordenades introduïdes per l'usuari des de la vista
        return vista.obtenirCoordenades();
    }

    public void setConstruccions() {
        // Establir les construccions al tauler amb construccions de 1x1
        int[][] tauler = model.getTauler();
        
        if (tauler.length == 0 || tauler[0].length == 0) {
            throw new IllegalStateException("El tauler no està inicialitzat correctament");
        }
        
        // Afegir construccions de 1x1 (una casella cada una)
        // Distribució en el tauler 5x5
        model.addConstruccio(new ModelConstruccio(1, 1, 0, 0));  // Posició (0, 0)
        model.addConstruccio(new ModelConstruccio(1, 1, 0, 2));  // Posició (0, 2)
        model.addConstruccio(new ModelConstruccio(1, 1, 2, 2));  // Posició (2, 2)
        model.addConstruccio(new ModelConstruccio(1, 1, 3, 1));  // Posició (3, 1)
        model.addConstruccio(new ModelConstruccio(1, 1, 4, 3));  // Posició (4, 3)
    }

    public boolean jocFinalitzat() {
        return model.totesConstruccionsDemolides();
    }

    public void mostrarTauler() {
        int[][] tauler = model.getTauler();
        boolean[][] atacades = model.getAtacades();
        char[][] taulerChar = converteixTauler(tauler, atacades);
        vista.mostraTauler(taulerChar);
    }

    private char[][] converteixTauler(int[][] tauler, boolean[][] atacades) {
        char[][] taulerChar = new char[tauler.length][tauler[0].length];
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                if (!atacades[i][j]) {
                    taulerChar[i][j] = '~'; // Casella no atacada
                } else if (tauler[i][j] == 0) {
                    taulerChar[i][j] = 'O'; // Agua (atac buit)
                } else {
                    taulerChar[i][j] = 'X'; // Construcció colpejada
                }
            }
        }
        return taulerChar;
    }
}
