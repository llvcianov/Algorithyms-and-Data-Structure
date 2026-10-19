
import java.util.ArrayList;
import java.util.Scanner;

public class TwoElementSum{ 

    public static int target;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] inputArray = new int[size];
        System.out.println("Enter " + size + " elements for the array:");
        for (int i = 0; i < size; i++) {
            inputArray[i] = scanner.nextInt();
        }

        ArrayList<Integer> vettore = createArrayList(inputArray);

        System.out.print("Enter the sum: ");
        int somma = scanner.nextInt();
        Ricerca(vettore, somma);
    }

    public static ArrayList<Integer> createArrayList(int[] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int element : array) {
            arrayList.add(element);
        }
        return arrayList;
    }

    public static void Ricerca(ArrayList<Integer> vettore, int somma) {
        int inizio = 0;
        int fine = vettore.size() - 1;
        QuickSort(vettore, inizio, fine);
        int s1 = 0;
        int s2 = Integer.MAX_VALUE;
        for( int i = 0; i < vettore.size() - 1; i++) {
            int complemento = somma - vettore.get(i);
            binarySearch(vettore, complemento, i + 1, fine);
            if (target!= -1) {
                if(Math.abs(target - vettore.get(i)) < Math.abs(s2 - s1)){
                    s1 = target;
                    s2 = vettore.get(i);
                }
            }
        }
        System.out.println("Elementi con somma minima: " + s1 + " e " + s2);
    }

    public static void QuickSort(ArrayList<Integer> vettore, int inizio, int fine) {
        if (inizio < fine) {
            int pivotIndex = partition(vettore, inizio, fine);
            QuickSort(vettore, inizio, pivotIndex - 1);
            QuickSort(vettore, pivotIndex + 1, fine);
        }
    }

    public static int partition(ArrayList<Integer> vettore, int inizio, int fine) {
        int pivot = vettore.get(fine);
        int i = inizio - 1;
        for (int j = inizio; j < fine; j++) {
            if (vettore.get(j) <= pivot) {
                i++;
                swap(vettore, i, j);
            }
        }
        swap(vettore, i + 1, fine);
        return i + 1;
    }

    public static void swap(ArrayList<Integer> vettore, int i, int j) {
        int temp = vettore.get(i);
        vettore.set(i, vettore.get(j));
        vettore.set(j, temp);
    }
    
    public static void binarySearch(ArrayList<Integer> list, int complemento, int start, int end) {
        int m = (start + end) / 2;
        if(start == end){
            if(list.get(start) == complemento){
                target = list.get(start);
            } else {
                target = -1;
            }
        } else {
            if (complemento <= list.get(m)) {
                binarySearch(list, complemento, start, m);
            } else {
                binarySearch(list, complemento, m + 1, end);
            }
        }
    }


}
