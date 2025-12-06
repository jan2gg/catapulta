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
        int[][] tauler = model.getTauler();
        char[][] taulerChar = converteixTauler(tauler);
        vista.mostraTauler(taulerChar);
        vista.mostraMissatges(null);
    }

    public boolean jugadaUsuari(int x, int y) {
        // Lògica per processar la jugada de l'usuari
        int[][] tauler = model.getTauler();
        
        if (x < 0 || x >= tauler.length || y < 0 || y >= tauler[0].length) {
            throw new IndexOutOfBoundsException("Coordenades fora de límits");
        }
        
        return model.atac(x, y);
    }

    public void setConstruccions() {
        // Lògica per establir les construccions al tauler
        int[][] tauler = model.getTauler();
        
        if (tauler.length == 0 || tauler[0].length == 0) {
            throw new IllegalStateException("El tauler no està inicialitzat correctament");
        }
    }

    private char[][] converteixTauler(int[][] tauler) {
        char[][] taulerChar = new char[tauler.length][tauler[0].length];
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                taulerChar[i][j] = tauler[i][j] == 0 ? '~' : 'X';
            }
        }
        return taulerChar;
    }
}
