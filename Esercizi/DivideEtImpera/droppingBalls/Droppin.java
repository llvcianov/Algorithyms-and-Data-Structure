import java.util.Scanner;

public class Droppin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Richiesta dei valori di input all'utente
        System.out.print("Inserisci il valore di profondità: ");
        int D = scanner.nextInt();
        System.out.print("Inserisci il valore di lancio: ");
        int I = scanner.nextInt();
        
        // Chiamata della funzione calcolaP con i parametri D, I e 1
        int risultato = calcolaP(D, I, 1);
        
        // Stampa del risultato
        System.out.println("La pallina cadrà in: " + risultato);
        
        scanner.close();
    }

    // Implementazione della funzione calcolaP
    public static int calcolaP(int D, int value, int padre) {
        int res = padre;
        D--;
        if (D != 0) {
            if (value % 2 == 0) {
                value = value / 2;
                padre = (padre * 2) + 1;
            } else {
                value = (value + 1) / 2;
                padre = padre * 2;
            }
            res = calcolaP(D, value, padre);
        }
        return res;
    }
}