import java.util.ArrayList;
import java.util.Scanner;


class Solution {

    public static int risultato = 0;

    public static void Ricerca(ArrayList<Integer> V, int i, int f){
        int m = (i + f) / 2;
        int pre = (V.get(m) - V.get(m - 1));
        int post = (V.get(m + 1) - V.get(m));
        int min = Math.min(pre, post);
        if(pre != post){
            if(pre > post)
                risultato = min + V.get(m - 1);
            else 
                risultato = min + V.get(m);
            return;
        } else {
            if((V.get(i) + ((V.size() / 2) * min)) != V.get(m - 1))
                Ricerca(V, i, m - 1);
            else 
                Ricerca(V, m + 1, f);
        }
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci numero e valori da analizzare");
        int N = scanner.nextInt(); // input: numero di stringhe
        if (N <= 12 && N >= 1){
           // scanner.nextLine(); // Consuma la linea successiva dopo il numero
            ArrayList<Integer> Input = new ArrayList<>(N); // Lista per contenere le stringhe
            for (int i = 0; i < N; i++) {
                Input.add(scanner.nextInt()); // legge ogni numero
            }
            int inizio = 0;
            int fine = N - 1;
            Ricerca(Input, inizio, fine);
  
        }
        System.out.print("il risultato è: " + risultato);
        scanner.close();
    }
}