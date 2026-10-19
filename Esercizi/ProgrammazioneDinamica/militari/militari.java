import java.util.Scanner;
import java.util.Arrays;

public class militari {

    public static int memo[][];

    //approccio TopDown
    public static int RicercaMax(int i, int Basi2[], int yBound){
        //caso base
        if(i == Basi2.length)
            return 0;

        //check in memo
        if(memo[i][yBound] != -1)
            return memo[i][yBound];

        int link = 0;
        int notlink = 0;
        int min = 0;

        //collega
        if(Basi2[i] > yBound)
            link = 1 + RicercaMax(i + 1, Basi2, Basi2[i]);

        //non collega
        notlink = RicercaMax(i + 1, Basi2, yBound);

        min = Math.max(link, notlink);
        memo[i][yBound] = min;
        
        return min;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leggi la lunghezza del vettore
        System.out.print("Inserisci il numero di basi: ");
        int n = scanner.nextInt();

        // Inizializza i due vettori
        int[] vettore1 = new int[n];
        int[] vettore2 = new int[n];

        // Leggi i valori del primo vettore
        System.out.println("Inserisci le basi del 1 confine:");
        for (int i = 0; i < n; i++) {
            System.out.print("Base " + (i + 1) + ": ");
            vettore1[i] = scanner.nextInt();
        }

        // Leggi i valori del secondo vettore
        System.out.println("Inserisci le basi del 2 confine:");
        for (int i = 0; i < n; i++) {
            System.out.print("Base " + (i + 1) + ": ");
            vettore2[i] = scanner.nextInt();
        }

        // Ordina il secondo vettore basandosi sull'ordine crescente del primo vettore
        Integer[] indici = new Integer[n];
        for (int i = 0; i < n; i++) {
            indici[i] = i;
        }

        // Ordina gli indici in base ai valori del primo vettore
        Arrays.sort(indici, (a, b) -> Integer.compare(vettore1[a], vettore1[b]));

        // Crea il vettore M con il secondo vettore ordinato
        int[] vettoreM = new int[n];
        for (int i = 0; i < n; i++) {
            vettoreM[i] = vettore2[indici[i]];
        }

        // Inizializzazione della struttura memo
        memo = new int[vettoreM.length][50];

        // Inizializza con -1 per indicare che non è stato calcolato
        for (int i = 0; i < vettoreM.length; i++) {
            for (int j = 0; j < 50; j++) {
                memo[i][j] = -1;
            }
        }

        System.out.println("I massimi collegamenti possibili tra le basi sono: " + RicercaMax(0,vettoreM,0));

        scanner.close();
    }
}
