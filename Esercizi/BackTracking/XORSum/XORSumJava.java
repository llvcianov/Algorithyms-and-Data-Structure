import java.util.ArrayList;
import java.util.Scanner;


class Solution {

    public static int risultatoTOT = 0;

    public static void costruisci(ArrayList <Boolean> candidati){
        candidati.add(true);
        candidati.add(false);
    }

    public static int subsetXORSum(ArrayList<Boolean> nums, ArrayList<Integer> Input) {
        ArrayList<Integer> Sub = new ArrayList<>();
        for(int i = 0; i < nums.size(); i++){
            if(nums.get(i) == true){
                Sub.add(Input.get(i));
            }
        }

        int XORSum = 0;
        if(Sub.size() == 1){
            return Sub.get(0);
        } else {
            for (int i : Sub) {
                XORSum ^= i; 
            }
        } 
        return XORSum;  
    }

    public static Boolean is_a_solution(ArrayList<Boolean> S, int k, ArrayList<Integer> Input){
        return (Input.size() == S.size());
    }

    public static void backtrack(ArrayList<Boolean> S, int k, ArrayList<Integer> Input){
        ArrayList<Boolean> candidati = new ArrayList<>(2);

        if(is_a_solution(S, k, Input)){
            risultatoTOT += subsetXORSum(S, Input);
        } else {
            k = k + 1;
            costruisci(candidati);
            for (int i = 0; i < 2; i++){
                S.add(candidati.get(i));
                backtrack(S, k, Input);
                S.remove(S.size()-1);
                k--;
            }
        }
    }
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci numero e valori da analizzare");
        int N = scanner.nextInt(); // input: numero di stringhe
        if (N <= 12 && N >= 1){
            scanner.nextLine(); // Consuma la linea successiva dopo il numero
            ArrayList<Integer> Input = new ArrayList<>(N); // Lista per contenere le stringhe
            for (int i = 0; i < N; i++) {
                Input.add(scanner.nextInt()); // legge ogni numero
            }
            int k = 0;
            ArrayList<Boolean> S = new ArrayList<>(N);
            backtrack(S,k,Input);
  
        }
        System.out.print("il risultato è: " + risultatoTOT);
        scanner.close();
    }
}