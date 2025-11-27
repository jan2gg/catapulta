package main.java.es.uab.tqs.catapulta.model;

import java.util.List;

public class ModelJoc {
    
    private int[][] tauler; // Matriu que representa el tauler del joc
    private List<ModelConstruccio> construccions; // Llista de construccions

    public ModelJoc(int width, int height) {

    }

    private void inicialitzaTauler() {
        // Inicialitzar el tauler amb espais buits
    }

    public void addConstruccio(ModelConstruccio construccio) {
        // Lògica per afegir una construcció al tauler
    }

    public boolean atac(int x, int y) {
        // Lògica per atacar una posició i verificar si hi ha una construcció
        return false;
    }

    public int[][] getTauler() {
        return this.tauler; // Retorna l'estat del tauler
    }

    public List<ModelConstruccio> getConstruccions() {
        return this.construccions; // Retorna la llista de construccions
    }
}
