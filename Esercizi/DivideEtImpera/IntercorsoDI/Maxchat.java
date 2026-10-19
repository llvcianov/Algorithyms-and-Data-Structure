import java.util.ArrayList;
import java.util.Scanner;

class Maxchat {

    // Funzione di ricerca ricorsiva per il massimo locale
    public static int Ricerca(ArrayList<Integer> V, int i, int f) {
        // Trova il punto medio
        int m = (i + f) / 2;

        // Caso base: controllo massimo locale
        if ((m == 0 || V.get(m) > V.get(m - 1)) && (m == V.size() - 1 || V.get(m) > V.get(m + 1))) {
            return V.get(m);  // Se è un massimo locale, lo restituisco
        }

        // Se il valore a sinistra è più grande, cerca nella metà sinistra
        if (m > 0 && V.get(m - 1) > V.get(m)) {
            return Ricerca(V, i, m - 1);
        }

        // Altrimenti, cerca nella metà destra
        return Ricerca(V, m + 1, f);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci il numero di valori da analizzare:");
        int N = scanner.nextInt(); 
        
        // Crea l'array di input
        ArrayList<Integer> Input = new ArrayList<>(N); 
        System.out.println("Inserisci i valori:");
        for (int i = 0; i < N; i++) {
            Input.add(scanner.nextInt()); 
        }

        int inizio = 0;
        int fine = N - 1;
            
        // Chiamata alla funzione di ricerca per trovare un massimo locale
        int maxLocale = Ricerca(Input, inizio, fine);
        System.out.println("Il massimo locale è: " + maxLocale);
        scanner.close();
    }
}
