import java.util.ArrayList;
import java.util.Scanner;


class hamming {

    public static void costruisci(ArrayList <Integer> candidati, ArrayList<Integer> S, int N, int H){
        boolean candidatoMinoreUgualeH = false;  // Flag per l'insieme minore o uguale a H
        boolean candidatoMaggioreH = false;      // Flag per l'insieme maggiore di H
        ArrayList<Boolean> Aux = new ArrayList<>();
        for (int i = 0; i < N; i++){
            Aux.add(false);
        }
        for (int i = 0; i < S.size(); i++){
            int appoggio = S.get(i);
            Aux.set(appoggio-1, true);
        }
        for ( int i = 0; i < Aux.size(); i++){
            if(!Aux.get(i)){
                if((i + 1 <= H) && !candidatoMinoreUgualeH){
                    candidati.add(i+1);
                    candidatoMinoreUgualeH = true;
                } else if ((i + 1 > H) && !candidatoMaggioreH){
                    candidati.add(i + 1);
                    candidatoMaggioreH = true;
                } 
            }
        }
    }
    
    public static void process_solution(ArrayList<Integer> S, int H){
        ArrayList <Integer> ZeroOneVectors = new ArrayList<>();
        for (int i = 0; i < S.size(); i++){
            if(S.get(i) <= H){ 
                ZeroOneVectors.add(1);
            } else {
                ZeroOneVectors.add(0);
            }
        }
        for (int i = 0; i < ZeroOneVectors.size(); i++){
            System.out.print(ZeroOneVectors.get(i) + "");
            //System.out.print(S.get(i) + "");
        }
        System.out.println();
    }

    public static void backtrack(ArrayList<Integer> S, int H, int N){
        ArrayList<Integer> candidati = new ArrayList<>();

        if(S.size() == N){
            process_solution(S, H);
        } else {
            costruisci(candidati, S, N, H);
            for (int i = 0; i < candidati.size(); i++){
                S.add(candidati.get(i));
                backtrack(S, H, N);
                S.remove(S.size()-1);
            }
        }
    }
    
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci N e H");
        int N = scanner.nextInt(); 
        int H = scanner.nextInt();
        ArrayList<Integer> S = new ArrayList<>(N);
        backtrack(S, H, N);

        scanner.close();
    }
}