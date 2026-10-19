import java.util.Scanner;

public class palindromi {

    public static int memo[][];

    public static int RicercaMax(char[] stringa, int i, int j){
        //Caso base
        if( i == j )
            return 1;

        // Caso base: stringa vuota o non valida
        if (i > j)
            return 0;
            
        //check in memo
        if( memo[i][j] != -1)
            return memo[i][j];

        int max = 0;
        int Dfirst = 0;
        int Dlast = 0;
        int NoOP = 0;

        if (stringa[i] == stringa [j]){
            NoOP = 2 + RicercaMax(stringa, i + 1, j - 1);
            memo[i][j] = NoOP;
        } else {
            Dfirst = RicercaMax(stringa, i + 1, j);
            Dlast = RicercaMax(stringa, i, j - 1);
            max = Math.max(Dlast, Dfirst);
            memo[i][j] = max;
        }

        return memo[i][j]; 

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Legge una stringa dall'utente
        System.out.print("Inserisci una stringa: ");
        String input = scanner.nextLine();

        // Converte la stringa in un vettore di caratteri
        char[] stringa = input.toCharArray();

        memo = new int[input.length()][input.length()];

        // Inizializza con -1 per indicare che non è stato calcolato
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                memo[i][j] = -1;
            }
        }       

        System.out.println("lunghezza palindromo massimo: " + RicercaMax(stringa, 0, input.length()-1));

        // Chiude lo scanner
        scanner.close();
    }
}
