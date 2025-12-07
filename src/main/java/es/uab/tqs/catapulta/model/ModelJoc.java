package es.uab.tqs.catapulta.model;

import java.util.ArrayList;
import java.util.List;

public class ModelJoc {
    
    private int[][] tauler; // Matriu que representa el tauler del joc (0=Buit, >0=ID construcció)
    private List<ModelConstruccio> construccions; // Llista de construccions
    private boolean[][] atacades; // Matriu que marca caselles ja atacades

    public ModelJoc(int width, int height) {
        this.tauler = new int[height][width];
        this.atacades = new boolean[height][width];
        this.construccions = new ArrayList<>();
        inicialitzaTauler();
    }

    private void inicialitzaTauler() {
        // Inicialitzar el tauler amb espais buits
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                tauler[i][j] = 0; // 0 representa un espai buit
                atacades[i][j] = false;
            }
        }
    }

    public void addConstruccio(ModelConstruccio construccio) {
        // Lògica per afegir una construcció al tauler
        if (construccio == null) {
            throw new IllegalArgumentException("La construcció no pot ser null");
        }
        
        int id = construccions.size() + 1;
        construccions.add(construccio);
        
        // Marcar la construcció al tauler
        for (int i = 0; i < construccio.getMidaVertical(); i++) {
            for (int j = 0; j < construccio.getMidaHoritzontal(); j++) {
                int fila = construccio.getFilaInicial() + i;
                int columna = construccio.getColumnaInicial() + j;
                
                if (fila >= 0 && fila < tauler.length && columna >= 0 && columna < tauler[0].length) {
                    tauler[fila][columna] = id;
                }
            }
        }
    }

    public boolean atac(int x, int y) {
        // Lògica per atacar una posició i verificar si hi ha una construcció
        if (x < 0 || x >= tauler.length || y < 0 || y >= tauler[0].length) {
            return false; // Fora de límits
        }
        
        if (atacades[x][y]) {
            return false; // Ja ha estat atacada
        }
        
        atacades[x][y] = true;
        int construccioId = tauler[x][y];
        
        if (construccioId > 0) {
            // Hi ha una construcció en aquesta posició
            ModelConstruccio construccio = construccions.get(construccioId - 1);
            construccio.rebeCop();
            return true; // Atac correcte
        }
        
        return false; // Casella buida
    }

    public boolean estaCasellAtacada(int x, int y) {
        if (x < 0 || x >= tauler.length || y < 0 || y >= tauler[0].length) {
            return false;
        }
        return atacades[x][y];
    }

    public boolean existeixConstruccioEnPosicio(int x, int y) {
        if (x < 0 || x >= tauler.length || y < 0 || y >= tauler[0].length) {
            return false;
        }
        return tauler[x][y] > 0;
    }

    public boolean totesConstruccionsDemolides() {
        for (ModelConstruccio construccio : construccions) {
            if (!construccio.estaDemolida()) {
                return false;
            }
        }
        return true;
    }

    public int[][] getTauler() {
        return this.tauler;
    }

    public List<ModelConstruccio> getConstruccions() {
        return this.construccions;
    }

    public boolean[][] getAtacades() {
        return this.atacades;
    }
}
