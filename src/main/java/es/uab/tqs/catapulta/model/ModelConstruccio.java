package main.java.es.uab.tqs.catapulta.model;

public class ModelConstruccio {

    private int midaVertical;       // Altura de la construcció
    private int midaHoritzontal;    // Amplada de la construcció
    private int copsRebuts;         // Nombre de cops rebuts
    private boolean estaDemolida;   // Estat de la construcció

    public ModelConstruccio() {
        this.midaVertical = 1;
        this.midaHoritzontal = 1;
        this.copsRebuts = 0;
        this.estaDemolida = false;
    }
}
