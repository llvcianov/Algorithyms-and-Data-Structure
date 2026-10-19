import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static boolean flag = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = 6;
        int M = 5;
        ArrayList<String> cruciverba = new ArrayList<>(Arrays.asList(
            "ocasp",
            "cilpi",
            "scdae",
            "avadn",
            "boaaa",
            "ombra"
        ));
        /* 
        // Chiedi all'utente di inserire i valori di N, M e la stringa di ricerca
        System.out.print("Inserisci il valore di N (numero di stringhe): ");
        int N = scanner.nextInt();
        System.out.print("Inserisci il valore di M (lunghezza delle stringhe): ");
        int M = scanner.nextInt();
        scanner.nextLine();  // Consuma il newline rimasto

        // Chiedi all'utente di inserire le stringhe
        ArrayList<String> cruciverba = new ArrayList<>();
        System.out.println("Inserisci " + N + " stringhe di lunghezza " + M + ":");
        for (int i = 0; i < N; i++) {
            String inputString;
            do {
                System.out.print("Stringa " + (i + 1) + ": ");
                inputString = scanner.nextLine();
                if (inputString.length() != M) {
                    System.out.println("La stringa deve essere lunga " + M + " caratteri.");
                }
            } while (inputString.length() != M);
            cruciverba.add(inputString);
        }
        */
        System.out.print("Inserisci la stringa da cercare: ");
        String target = scanner.nextLine();
        // Stampa l'ArrayList generato
        System.out.println("ArrayList di stringhe inserite:");
        for (String str : cruciverba) {
            System.out.println(str);
        }

        // Crea e inizializza un ArrayList di interi N x M con 0
        ArrayList<ArrayList<Integer>> supporto = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                row.add(0);
            }
            supporto.add(row);
        }

        // Analizza ogni cella come possibile punto di partenza
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ArrayList<ArrayList<Integer>> soluzione = new ArrayList<>();
                Ricerca(soluzione, cruciverba, supporto, target, i, j, 0);
                if (flag) break;
            }
            if (flag) break;
        }
        
        if (!flag) {
            System.out.println("Target non trovato nel cruciverba.");
        }

        scanner.close();
    }

    public static void Ricerca(ArrayList<ArrayList<Integer>> soluzione, ArrayList<String> cruciverba, 
                                        ArrayList<ArrayList<Integer>> supporto, String target, int x, int y, int k) {
                                       
        ArrayList<ArrayList<Integer>> candidati = new ArrayList<>();

        if (k == target.length()) {
            process_solution(soluzione);

        } else {
            if (costruisci_candidati(cruciverba, supporto, target, x, y, k, candidati)) {
                for (ArrayList<Integer> candidato : candidati) {

                    supporto.get(candidato.get(0)).set(candidato.get(1), 1);
                    soluzione.add(candidato);

                    if (!flag) 
                        Ricerca(soluzione, cruciverba, supporto, target, candidato.get(0), candidato.get(1), k + 1);
                    else return;

                    supporto.get(candidato.get(0)).set(candidato.get(1), 0);
                    soluzione.remove(soluzione.size() - 1);
                }
                if (flag) {
                    return;
                }
            }
        }
    }

    public static boolean costruisci_candidati(ArrayList<String> cruciverba, ArrayList<ArrayList<Integer>> supporto, 
                                            String target, int x, int y, int indice, ArrayList<ArrayList<Integer>> candidati) {
                                    
        int counter = 0;
        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetY = -1; offsetY <= 1; offsetY++) {

                if (offsetX == 0 && offsetY == 0) {
                    continue;
                }
                
                int Newx = x + offsetX;
                int Newy = y + offsetY;

                if (Newx >= 0 && Newx < cruciverba.size() && Newy >= 0 &&
                            Newy < cruciverba.get(0).length() && supporto.get(Newx).get(Newy) == 0) {

                    if(cruciverba.get(Newx).charAt(Newy) == target.charAt(indice)) {
                        counter++;
                        candidati.add(new ArrayList<>(Arrays.asList(Newx, Newy)));
                    }
                }
            }
        }
        return (counter > 0);
    }

    public static void process_solution(ArrayList<ArrayList<Integer>> soluzione) {
        flag = true;
        for (int i = 0; i < soluzione.size(); i++) {
            System.out.print(soluzione.get(i));
        }
        System.out.println();
    }
}