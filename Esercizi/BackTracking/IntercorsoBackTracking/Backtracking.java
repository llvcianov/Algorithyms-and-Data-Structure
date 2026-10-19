import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Backtracking {

    //tiene traccia delle righe del percorso ottimo
    public static ArrayList<Integer> percorso = new ArrayList<>();

    //tiene traccia della somma delle celle del percorso ottimo
    public static int minpercorso = Integer.MAX_VALUE;

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        
        matrix.add(new ArrayList<>(List.of(3, 4, 1, 2, 8, 6)));
        matrix.add(new ArrayList<>(List.of(6, 1, 8, 2, 7, 4)));
        matrix.add(new ArrayList<>(List.of(5, 9, 3, 9, 9, 5)));
        matrix.add(new ArrayList<>(List.of(8, 4, 1, 3, 2, 6)));
        matrix.add(new ArrayList<>(List.of(3, 7, 2, 8, 6, 4)));
        
        ArrayList<ArrayList<Integer>> soluzione = new ArrayList<>();

        for( int i = 0; i < matrix.size(); i++ ){
            soluzione.clear();
            soluzione.add(new ArrayList<>(Arrays.asList(i, 0)));
            RicercaMin(matrix,soluzione,i,0);
        }

        for(int elem : percorso){
            System.out.print(elem + " ");
        }
        System.out.println("\nPercorso più corto: " + minpercorso);   
    }

    public static void RicercaMin(ArrayList<ArrayList<Integer>> matrice,ArrayList<ArrayList<Integer>> soluzione,int x, int y) {
                                       
        ArrayList<ArrayList<Integer>> candidati = new ArrayList<>();

        if (is_a_solution(matrice,soluzione)){
            process_solution(soluzione,matrice);

        } else{
            if (costruisci_candidati(matrice,soluzione,candidati,x,y)){
                for (int i = 0; i < candidati.size(); i++) {

                    soluzione.add(candidati.get(i));
                    RicercaMin(matrice, soluzione, candidati.get(i).get(0), candidati.get(i).get(1));
                    soluzione.remove(soluzione.size()-1);
                }
            } 
        }          
    }

    public static boolean is_a_solution(ArrayList<ArrayList<Integer>> matrice, ArrayList<ArrayList<Integer>> soluzione) {
        return (soluzione.size() == matrice.get(0).size());
    }

    public static boolean costruisci_candidati(ArrayList<ArrayList<Integer>> matrice, ArrayList<ArrayList<Integer>> soluzione, ArrayList<ArrayList<Integer>> candidati, int x, int y) {
                                    
        int counter = 0;
        for (int offsetX = -1; offsetX < 2; offsetX++) {
               
            int Newx = x + offsetX;
            int Newy = y + 1;

            if (Newy < matrice.get(0).size()){  
             
                if(Newx < 0) {
                    Newx = matrice.size() - 1;
                } else if (Newx > matrice.size() - 1) {
                    Newx = 0;
                }
           
                if(compareSum(soluzione,Newx,Newy,matrice)){
                    candidati.add(new ArrayList<>(Arrays.asList(Newx, Newy)));
                    counter++;
                }
            }
        }
        return (counter > 0);
    }

    //verifica se l'aggiunta della cella di cordinate x,y supera min percorso
    public static boolean compareSum(ArrayList<ArrayList<Integer>> soluzione,int x, int y,
                                ArrayList<ArrayList<Integer>> matrice) {

        int sum = sumByCoordinates(soluzione, matrice);

        // Aggiungi il valore dell'ultima coordinata newx, newy
        sum += matrice.get(x).get(y); 
        
        // Confronta con minpercorso
        return sum < minpercorso;
    }

    //calcola la somma delle celle nella matrice le cui coordinate sono presenti in soluzione
    public static int sumByCoordinates(ArrayList<ArrayList<Integer>> coordinates, ArrayList<ArrayList<Integer>> matrice) {
        int sum = 0;

        for (ArrayList<Integer> coordinate : coordinates) {
            int row = coordinate.get(0); // Ottieni la riga
            int col = coordinate.get(1); // Ottieni la colonna
            // Aggiungi il valore corrispondente dalla matrice 
            sum += matrice.get(row).get(col);
        }

        return sum;
    }

    //se la soluzione trovata è minore di minpercorso, aggiorna min percorso e percorso
    public static void process_solution(ArrayList<ArrayList<Integer>> soluzione, ArrayList<ArrayList<Integer>> matrice) {
        int m = sumByCoordinates(soluzione, matrice);
        if (m < minpercorso) {
            minpercorso = m;
            percorso.clear();
            for (ArrayList<Integer> coordinate : soluzione) {
                int rowIndex = coordinate.get(0); // Ottieni l'indice di riga
                percorso.add(rowIndex); // Aggiungi al percorso
            }
        }
    }
}