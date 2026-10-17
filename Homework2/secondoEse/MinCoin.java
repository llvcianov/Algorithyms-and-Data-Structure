


//Il problema è trovare il minor numero di monete per arrivare ad una data somma, il sottoproblema è trovare il minor numero
//di monete per arrivare alla nuova somma data da quella vecchia meno il valore della prima moneta scelta. Nel caso peggiore
//abbiamo quindi O(sommaTarget) sottoproblemi. Ad ogni passo le possibili scelte sono le differenti monete che abbiamo
//quindi abbiamo N scelte date dai diversi tipi di monete. 
//La ricorrenza è DP(S) = min {DP(S - coin[i]) + 1 | i = 0,..., N-1}, dove il minimo è calcolato sulla variabile di costo.
//La variabile di costo è formata dal numero di monete necessarie per arrivare alla somma target e la differenza dalla stessa
//nel caso si sfori. L'algoritmo preferisce arrivare quanto più vicino possibile alla somma target. A parità di vicinanza prende
//la scelta con il minor numero di monete.
//La complessità è data dal numero di sottoproblemi O(sommaTarget) (con tempo costante per sottoprpoblema) per il numero di scelte, 
//in definitiva O(sommaTarget*N)
//Il grafo delle dipendenze è un DAG poichè la sommaTarget diminuisce sempre. Sarebbe stato ciclico se avessimo avuto
//monete a costo negativo. Non c'è extra-time.s


import java.util.HashMap;
import java.util.Scanner;

public class MinCoin {

    // Struttura dati: HashMap con Somma rimanente come chiave e numero minimo di monete come valore
    public static HashMap<Integer, Costo> MemoMappa = new HashMap<>();

    //variabile composta di costo
    public record Costo(int counterMonete, int DifferenzaMinima) {}

    //Approccio Top-Down
    public static Costo RicercaMin(int sommaTarget, int[] coin, int N) {

        // Controlla se la sommaTarget è già presente nella mappa
        if(MemoMappa.containsKey(sommaTarget))
            return MemoMappa.get(sommaTarget); 

        //Caso base, se sforo o raggiungo la sommaTarget non richiamo la funzione
        //ritorno invece solo di quanto ho superato la sommaTarget
        if(sommaTarget <= 0)
            return new Costo(0,Math.abs(sommaTarget));

        Costo min = null;

        //Ricerca il minimo numero di monete tra tutte le scelte, richiamando la funzione con una diversa somma
        for(int j = 0; j < N; j++){

                Costo value = RicercaMin(sommaTarget - coin[j], coin, N);

                //Calcolo il costo con la differenza minima, a parità di differenza scelgo il costo di monete minore
                if (min == null || 
                        value.DifferenzaMinima < min.DifferenzaMinima || 
                            (value.DifferenzaMinima == min.DifferenzaMinima && value.counterMonete < min.counterMonete)) {
                min = value;
            }
        }

        //Aggiungo una moneta
        if (min != null) 
            min = new Costo(min.counterMonete + 1, min.DifferenzaMinima);
        
        //trovata la scelta migliore per una data sommaTarget la aggiungo alla mappa
        MemoMappa.put(sommaTarget, min);

        return min;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci la somma, il numero di monete ed il loro valore:");

        int sommaTarget = scanner.nextInt();

        int N = scanner.nextInt();
        int[] vettoreMonete = new int[N];

        for (int i = 0; i < N; i++) 
            vettoreMonete[i] = scanner.nextInt();

        Costo Nmin = RicercaMin(sommaTarget, vettoreMonete, N);

        System.out.println("Il numero minimo di monete e' " + Nmin.counterMonete);

        scanner.close();
    }
}

