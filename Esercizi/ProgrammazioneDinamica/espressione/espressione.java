import java.util.ArrayList;
import java.util.Scanner;

public class espressione {

    // Struttura dati
    public static int memo[][];
    
    //Approccio Top-Down
    public static int RicercaMax(int input[], int i, int j){

        //Caso base
        if(i == j)
            return input[i];

        // Controlla se è già presente in memo
        if(memo[i][j] != -1)
            return memo[i][j]; 

        int max = 0;

        for (int k = i; k < j; k = k + 2) {
            int value = 0;
            if (input[k + 1] == -7) { 
                value = RicercaMax(input, i, k) + RicercaMax(input, k + 2, j);
            } else {
                value = RicercaMax(input, i, k) * RicercaMax(input, k + 2, j);
            }
            max = Math.max(max, value);
        }

        memo[i][j] = max;

        return max;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci un'espressione (ad esempio: 3+5*2+8):");
        String expression = scanner.nextLine();

        // Rimuove eventuali spazi
        expression = expression.replaceAll("\\s", "");

        // Convalida l'espressione
        if (!expression.matches("\\d+([+*]\\d+)*")) {
            System.out.println("Espressione non valida. Usa solo numeri positivi e operatori + o *.");
            return;
        }

        // Converte l'espressione in un array
        ArrayList<Integer> inputList = new ArrayList<>();
        StringBuilder numberBuilder = new StringBuilder();
        
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                numberBuilder.append(c);
            } else {
                // Aggiunge il numero precedente all'input
                inputList.add(Integer.parseInt(numberBuilder.toString()));
                numberBuilder.setLength(0);
                // Aggiunge l'operatore corrispondente
                if (c == '+') {
                    inputList.add(-7);
                } else if (c == '*') {
                    inputList.add(-8);
                }
            }
        }
        
        // Aggiunge l'ultimo numero
        if (numberBuilder.length() > 0) {
            inputList.add(Integer.parseInt(numberBuilder.toString()));
        }

        // Converte in array
        int[] input = new int[inputList.size()];
        for (int i = 0; i < inputList.size(); i++) {
            input[i] = inputList.get(i);
        }

        // Inizializzazione della struttura memo
        memo = new int[input.length][input.length];

        // Inizializza con -1 per indicare che non è stato calcolato
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                memo[i][j] = -1;
            }
        }

        // Stampa il risultato
        System.out.println("Il risultato massimo ottenibile dall'espressione \"" + expression + "\" è: " + RicercaMax(input, 0, input.length - 1));
    }
}