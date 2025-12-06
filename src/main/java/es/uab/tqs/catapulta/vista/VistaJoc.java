package es.uab.tqs.catapulta.vista;

import java.util.Scanner;

public class VistaJoc {
    private Scanner scanner;

    public VistaJoc() {
        this.scanner = new Scanner(System.in);
    }

    public void mostraTauler(char[][] tauler) {
        // Lògica per desplegar el tauler a la terminal
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
        // Lògica per mostrar missatges a l'usuari
        if (missatge != null && !missatge.isEmpty()) {
            System.out.println(missatge);
        }
    }

    public void jugadaUsuari() {
        // Lògica per demanar al jugador les coordenades per atacar
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
