package es.uab.tqs.catapulta.vista;

import java.util.Scanner;

// Vista de consola: mostra el tauler i recull interaccions amb l'usuari.
public class VistaJoc {
    // Canal d'entrada per llegir coordenades (substituïble en tests).
    private Scanner scanner;

    public VistaJoc() {
        this.scanner = new Scanner(System.in);
    }

    // Constructor per testing amb Scanner mockejat.
    public VistaJoc(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostraTauler(char[][] tauler) {
        // Dibuixa el tauler en text: capçaleres de columnes, separadors i files.
        System.out.println("\n  " + getNumeColumnes(tauler[0].length));
        System.out.println("  " + getLiniaSeparadora(tauler[0].length));
        
        for (int i = 0; i < tauler.length; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < tauler[i].length; j++) {
                System.out.print(tauler[i][j] + "|");
            }
            System.out.println();
            System.out.println("  " + getLiniaSeparadora(tauler[0].length));
        }
    }

    public void mostraMissatges(String missatge) {
        // Presenta feedback a l'usuari si el text no és buit.
        if (missatge != null && !missatge.isEmpty()) {
            System.out.println(missatge);
        }
    }

    public int[] obtenirCoordenades() {
        // Demana coordenades d'atac; bucle fins a entrada vàlida (gestiona InputMismatch).
        int[] coordenades = new int[2];
        boolean coordenadesValides = false;
        
        while (!coordenadesValides) {
            try {
                System.out.println("\nIntrodueix les coordenades per atacar:");
                System.out.print("Fila (x): ");
                coordenades[0] = scanner.nextInt();
                System.out.print("Columna (y): ");
                coordenades[1] = scanner.nextInt();
                coordenadesValides = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Introdueix números vàlids!");
                scanner.nextLine(); // Neteja buffer per tornar a llegir.
            }
        }
        
        return coordenades;
    }

    public void jugadaUsuari() {
        // Demana una jugada directa (no retorna valors; útil per compatibilitat).
        System.out.println("\nIntrodueix les coordenades per atacar:");
        System.out.print("Fila (x): ");
        int x = scanner.nextInt();
        System.out.print("Columna (y): ");
        int y = scanner.nextInt();
    }

    private String getNumeColumnes(int numColumnes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numColumnes; i++) {
            sb.append(i).append(" ");
        }
        return sb.toString();
    }

    private String getLiniaSeparadora(int numColumnes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numColumnes * 2 + 1; i++) {
            sb.append("-");
        }
        return sb.toString();
    }
}
