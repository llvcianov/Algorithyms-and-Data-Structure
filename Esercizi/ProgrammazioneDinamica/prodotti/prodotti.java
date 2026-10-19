

import java.util.ArrayList;
import java.util.Scanner;

public class prodotti {

    // Struttura dati
    public static int memo[][];
    
    //Approccio Top-Down
    public static int DPprodotti(ArrayList<ArrayList<Integer>> prodotti, int classe, int Budget ) {

        //Caso base
        if((classe >= prodotti.size()) || (Budget <= 0))
            return 0;

        // Controlla se è già presente in memo
        if(memo[classe][Budget] != -1)
            return memo[classe][Budget]; 

        int maxSpesa = 0;

        // Prova ogni prodotto della classe corrente
        for (int costo : prodotti.get(classe)) {
            if (costo <= Budget) { // Solo se il costo rientra nel budget
                maxSpesa = Math.max(maxSpesa, costo + DPprodotti(prodotti, classe + 1, Budget - costo));
            }
        }

        memo[classe][Budget] = maxSpesa;

        return memo[classe][Budget];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il budget e il numero di classi:");
        int Budget = scanner.nextInt();
        int Nclassi = scanner.nextInt();

        // Inizializzazione della struttura memo
        memo = new int[Nclassi][Budget + 1];
 
        for (int i = 0; i < Nclassi; i++) {
            for (int j = 0; j <= Budget; j++) {
                memo[i][j] = -1; // inizializzo con -1 per indicare "non calcolato"
            }
        }

        ArrayList<ArrayList<Integer>> prodotti = new ArrayList<>();

        for (int i = 0; i < Nclassi; i++) { 
            System.out.println("Inserisci numero prodotti della classe " + (i + 1) + " e il loro costo:");
            int Nprodotti = scanner.nextInt();

            prodotti.add(new ArrayList<>()); 

            for (int j = 0; j < Nprodotti; j++) { 
                prodotti.get(i).add(scanner.nextInt());
            }
        }

        System.out.println("La massima spesa possibile comprando un prodotto per classe è: " + DPprodotti(prodotti, 0, Budget));

        scanner.close();
    }
}

