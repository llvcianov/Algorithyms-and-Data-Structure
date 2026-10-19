import java.util.Scanner;

public class ValoriMonete {

    // Struttura dati
    public static int memo[];
    
    //Approccio Top-Down
    public static int RicercaMin(int valori[], int i){
        //caso base
        if(i == valori.length)
            return 0;

        //prende il risultato dal memo
        if(memo[i] != -1)
            return memo[i];


        int value1 = Math.abs(valori[i] + RicercaMin(valori, i + 1));
        int value2 = Math.abs(-valori[i] + RicercaMin(valori, i + 1));

        memo[i] = Math.min(value1, value2);
        return memo[i];  

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Legge il numero di monete
        System.out.print("Inserisci il numero di monete (N): ");
        int N = scanner.nextInt();

        // Crea un vettore per i valori delle monete
        int[] valori = new int[N];

        // Legge i valori delle monete
        System.out.println("Inserisci i valori delle " + N + " monete:");
        for (int i = 0; i < N; i++) {
            System.out.print("Valore della moneta " + (i + 1) + ": ");
            valori[i] = scanner.nextInt();
        }

        // Inizializzazione della struttura memo
        memo = new int[valori.length];

        // Inizializza con -1 per indicare che non è stato calcolato
        for (int i = 0; i < valori.length; i++) {
            memo[i] = -1;
        }
        
        // Stampa il risultato
        System.out.println("La differenza minima del bottino è: " + RicercaMin(valori, 0));


        // Chiude lo scanner
        scanner.close();
    }
}
