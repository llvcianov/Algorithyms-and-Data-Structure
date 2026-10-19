import java.util.ArrayList;
import java.util.Scanner;


class soluzione {

    public static void costruisci(ArrayList <Integer> candidati, ArrayList<Integer> S, ArrayList<Integer> Input){
        ArrayList<Boolean> Aux = new ArrayList<>(Input.size());
        for (int i = 0; i < Input.size(); i++){
            Aux.add(false);
        }
        for (int i = 0; i < S.size(); i++){
            int appoggio = S.get(i);
            Aux.set(appoggio-1, true);
        }
        for ( int i = 0; i < Aux.size(); i++){
            if(!Aux.get(i)){
                candidati.add(i+1);
            }
        }
    }

    public static void process_solution(ArrayList<Integer> S){
        for (int i = 0; i < S.size(); i++){
            System.out.print(S.get(i) + "");
        }
        System.out.println();
    }

    public static void backtrack(ArrayList<Integer> S, ArrayList<Integer> Input){
        ArrayList<Integer> candidati = new ArrayList<>();

        if(Input.size() == S.size()){
            process_solution(S);
        } else {
            costruisci(candidati, S, Input);
            for (int i = 0; i < candidati.size(); i++){
                S.add(candidati.get(i));
                backtrack(S, Input);
                S.remove(S.size()-1);
            }
        }
    }
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci numero e valori");
        int N = scanner.nextInt(); 
        scanner.nextLine(); 
        ArrayList<Integer> Input = new ArrayList<>(N); 
        for (int i = 0; i < N; i++) {
            Input.add(scanner.nextInt()); 
        }
        ArrayList<Integer> S = new ArrayList<>(N);
        backtrack(S,Input);

        scanner.close();
    }
}