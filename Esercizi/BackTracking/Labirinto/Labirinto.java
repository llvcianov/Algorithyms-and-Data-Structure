import java.util.ArrayList;

public class Labirinto {

    static final int N = 5;

    // N.B Nel codice x rappresenta indice di riga e y indice di colonna
    public static boolean costruisciCandidati(ArrayList<ArrayList<Integer>> L, int x, int y, ArrayList<ArrayList<Integer>> scelte) {
        int counter = 0;
        
        if (y + 1 < L.size() && L.get(x).get(y + 1) == 1) { // Verifica se y+1 è dentro i limiti
            ArrayList<Integer> coppia = new ArrayList<>();
            coppia.add(x);
            coppia.add(y + 1);
            scelte.add(coppia); // Aggiungi la coppia (x, y+1) a scelte
            counter++;
        }
        
        if (x + 1 < L.size() && L.get(x + 1).get(y) == 1) { // Verifica se x+1 è dentro i limiti
            ArrayList<Integer> coppia = new ArrayList<>();
            coppia.add(x + 1);
            coppia.add(y);
            scelte.add(coppia); // Aggiungi la coppia (x+1, y) a scelte
            counter++;
        }
        
        return counter != 0;
    }

    public static boolean isSolution(int x, int y) {
        return x == N - 1 && y == N - 1;
    }

    public static void stampaPercorso(ArrayList<ArrayList<Integer>> S) {
        ArrayList<ArrayList<Integer>> matrice = new ArrayList<>();
        
        // Crea una matrice di zeri di dimensione N
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                row.add(0);
            }
            matrice.add(row);
        }

        // Imposta il punto di partenza
        matrice.get(0).set(0, 1);

        // Imposta a 1 le posizioni specificate in S
        for (ArrayList<Integer> pos : S) {
            int x = pos.get(0);
            int y = pos.get(1);
            matrice.get(x).set(y, 1);
        }

        // Stampa la matrice risultante
        for (ArrayList<Integer> row : matrice) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void cercaPercorso(ArrayList<ArrayList<Integer>> S, ArrayList<ArrayList<Integer>> L, int x, int y) {
        ArrayList<ArrayList<Integer>> scelte = new ArrayList<>();

        if (isSolution(x, y)) { // Verifica se la posizione corrente è la fine del labirinto
            stampaPercorso(S);
        } else {
            if (costruisciCandidati(L, x, y, scelte)) {

                // Itero sul vettore delle possibili scelte
                for (ArrayList<Integer> riga : scelte) {
                    // Salvo la posizione corrente per ripristinarla in caso di scelta sbagliata
                    int xOrig = x;
                    int yOrig = y;

                    // Aggiungo la scelta fatta al vettore delle soluzioni
                    S.add(riga);
                    // Eseguo la mossa
                    x = riga.get(0);
                    y = riga.get(1);
                    cercaPercorso(S, L, x, y);

                    // Se qui significa che ho fatto una scelta sbagliata, tolgo la mossa effettuata
                    S.remove(S.size() - 1);
                    // Ripristino la posizione
                    x = xOrig;
                    y = yOrig;
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> labirinto = new ArrayList<>();

        // Creazione della matrice rappresentante il labirinto
        int[][] valoriLabirinto = {
            {1, 1, 1, 0, 1},
            {1, 0, 0, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
        };

        // Trasferisco i valori dall'array alla struttura ArrayList<ArrayList<Integer>>
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                row.add(valoriLabirinto[i][j]);
            }
            labirinto.add(row);
        }

        // Stampa iniziale della matrice del labirinto
        for (ArrayList<Integer> row : labirinto) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println("\n");

        ArrayList<ArrayList<Integer>> S = new ArrayList<>();
        
        int x = 0; // posizione di partenza x
        int y = 0; // posizione di partenza y

        cercaPercorso(S, labirinto, x, y);
    }
}
