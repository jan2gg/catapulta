package es.uab.tqs.catapulta.model;

import java.util.ArrayList;
import java.util.List;

public class ModelJoc {
    
    private int[][] tauler; // Matriu que representa el tauler del joc
    private List<ModelConstruccio> construccions; // Llista de construccions

    public ModelJoc(int width, int height) {
        this.tauler = new int[height][width];
        this.construccions = new ArrayList<>();
        inicialitzaTauler();
    }

    private void inicialitzaTauler() {
        // Inicialitzar el tauler amb espais buits
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[i].length; j++) {
                tauler[i][j] = 0; // 0 representa un espai buit
            }
        }
    }

    public void addConstruccio(ModelConstruccio construccio) {
        // Lògica per afegir una construcció al tauler
        if (construccio == null) {
            throw new IllegalArgumentException("La construcció no pot ser null");
        }
        construccions.add(construccio);
    }

    public boolean atac(int x, int y) {
        // Lògica per atacar una posició i verificar si hi ha una construcció
        if (x < 0 || x >= tauler.length || y < 0 || y >= tauler[0].length) {
            return false; // Fora de límits
        }
        return false; // De moment sempre retorna false
    }

    public int[][] getTauler() {
        return this.tauler; // Retorna l'estat del tauler
    }

    public List<ModelConstruccio> getConstruccions() {
        return this.construccions; // Retorna la llista de construccions
    }
}
