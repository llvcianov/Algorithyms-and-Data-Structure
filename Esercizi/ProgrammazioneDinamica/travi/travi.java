import java.util.Scanner;

public class travi {

    // Struttura dati
    public static int memo[][];
    
    //Approccio Top-Down
    public static int RicercaMin(int i, int j, int tagli[]){
        //caso base
        if(j == i + 1)
            return 0;

        //prende il risultato dal memo
        if(memo[i][j] != -1)
            return memo[i][j];

        int min = Integer.MAX_VALUE;

        for(int k = i + 1; k < j; k++){

            int value = RicercaMin(i, k, tagli) + RicercaMin(k, j, tagli) + tagli[j] - tagli[i];
            min = Math.min(value, min);

        }

        memo[i][j]= min;
        return min;  

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci la lunghezza della trave: ");
        int L = scanner.nextInt();

        System.out.print("Inserisci il numero di tagli: ");
        int N = scanner.nextInt();

        // Crea un vettore per i tagli
        int[] tagli = new int[N + 2];

        // Legge i valori dei tagli
        System.out.println("Inserisci i valori dei " + N + " tagli:");
        for (int i = 1; i <= N; i++) {
            System.out.print("Valore del taglio " + i + ": ");
            tagli[i] = scanner.nextInt();
        }
        tagli[0] = 0;
        tagli[N + 1] = L;

        // Inizializzazione della struttura memo
        memo = new int[N + 2][N + 2];

        // Inizializza con -1 per indicare che non è stato calcolato
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < N + 2; j++)
                memo[i][j] = -1;
        }
        
        // Stampa il risultato
        System.out.println("Il costo minimo dei tagli è: " + RicercaMin(0, N + 1, tagli));

        // Chiude lo scanner
        scanner.close();
    }
}
