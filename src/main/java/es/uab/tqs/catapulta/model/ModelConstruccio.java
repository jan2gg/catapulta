package es.uab.tqs.catapulta.model;

// Representa una construcció al tauler amb mida i estat de demolició.
public class ModelConstruccio {

    // Dimensions i localització de la peça al tauler.
    private int midaVertical;       // Altura de la construcció
    private int midaHoritzontal;    // Amplada de la construcció
    private int filaInicial;        // Fila inicial de la construcció
    private int columnaInicial;     // Columna inicial de la construcció

    // Estat de danys i demolició.
    private int copsRebuts;         // Nombre de cops rebuts
    private boolean estaDemolida;   // Estat de la construcció

    // Constructor principal: defineix mida i posició de la construcció.
    public ModelConstruccio(int midaVertical, int midaHoritzontal, int filaInicial, int columnaInicial) {
        this.midaVertical = midaVertical;
        this.midaHoritzontal = midaHoritzontal;
        this.filaInicial = filaInicial;
        this.columnaInicial = columnaInicial;
        this.copsRebuts = 0;
        this.estaDemolida = false;
    }

    // Constructor per defecte: peça mínima 1x1 a la cantonada superior esquerra.
    public ModelConstruccio() {
        this(1, 1, 0, 0);
    }

    public int getMidaVertical() {
        return midaVertical;
    }

    public int getMidaHoritzontal() {
        return midaHoritzontal;
    }

    public int getCopsRebuts() {
        return copsRebuts;
    }

    public boolean estaDemolida() {
        return estaDemolida;
    }

    public int getFilaInicial() {
        return filaInicial;
    }

    public int getColumnaInicial() {
        return columnaInicial;
    }

    public void rebeCop() {
        // Incrementa el comptador i comprova cas límit: suficients impactes per demolir.
        if (!estaDemolida) {
            copsRebuts++;
            int copsNecessaris = midaVertical * midaHoritzontal;
            if (copsRebuts >= copsNecessaris) {
                estaDemolida = true;
            }
        }
    }
}
