

//Questa soluzione è un alternativa al noto Algoritmo di Kadane.
//L'algoritmo seguente usa un approccio bottom up. Consiste in due cicli for: 
//il primo itera l'intero vettore di cui si vuole trovare la sottosomma ottima,
//il secondo itera su una struttura di risultati parziali.
//Ad ogni passo l'elemento i-esimo viene sommato con tutti i risultati parziali
//trovati al passo precedente e aggiorna la somma massima trovata fino a quel punto.
//Essendo composto da 2 cicli for l'algoritmo ha una complessità di O(n^2).


import java.util.Scanner;

public class MaxSubsetSum {

    public static int RicercaMax(int[] vettore, int N) {

        int Max = Integer.MIN_VALUE;
        //array per memorizzare risultati somme parziali
        int[] VettSum = new int[N]; 

        //itera su tutto il vettore
        for (int i = 0; i < N; i++) {  
            //per ogni elemento esegue j iterazioni sull'array Memo: j parte
            //dall'indice di posizione i e si decrementa fino a 0.
            for (int j = i; j >= 0; j--) {  
                //caso base salva l'elemento i-esimo del vettore nella prima posizione di Memo
                if (j == 0)
                    VettSum[j] = vettore[i];
                //salva in posizione j di Memo la somma dell'elemento i-esimo del vettore
                //con il valore salvato in posizione j-1 di Memo.
                else
                    VettSum[j] = vettore[i] + VettSum[j - 1];

                Max = Math.max(Max, VettSum[j]);
            }
        }

        return Max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci numero ed elementi array:");

        int N = scanner.nextInt();
        int[] vettore = new int[N];

        for (int i = 0; i < N; i++) 
            vettore[i] = scanner.nextInt();

        int massimo = RicercaMax(vettore, N);

        System.out.println("Il massimo sottoarray e' " + massimo);

        scanner.close();
    }
}
