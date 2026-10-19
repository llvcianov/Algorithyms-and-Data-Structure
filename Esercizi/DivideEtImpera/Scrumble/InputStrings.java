import java.util.Scanner;

public class InputStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Chiedi all'utente di inserire la prima stringa
        System.out.println("Inserisci la stringa originale:");
        String s1 = scanner.nextLine();

        // Chiedi all'utente di inserire la seconda stringa
        System.out.println("Inserisci la possibile stringa scrumble:");
        String s2 = scanner.nextLine();

        boolean Res = isScrumble(s1, s2, s1.length());
        System.out.println("La stringa è scramble: " + Res);

        scanner.close();
    }

    // Funzione per ottenere una sottostringa
    public static String getSubstring(String str, int start, int end) {
        if (start < 0 || end > str.length() || start > end) {
            throw new IllegalArgumentException("Indici non validi");
        }
        return str.substring(start, end);
    }

    public static String scrumble(String str, int point) {
        if (point < 0 || point > str.length()) {
            throw new IllegalArgumentException("Punto di scrumble non valido");
        }

        String firstPart = str.substring(0, point);
        String secondPart = str.substring(point);

        return secondPart + firstPart;
    }

    static boolean isScrumble(String s1, String s2, int end1) {
        boolean res1 = false;
        boolean res2 = false;

        int medio = end1 / 2;

        String substring = getSubstring(s1, 0, medio);

        if (substring.length() > 1){
            String ScrumbleSubstring = scrumble(substring, substring.length()/2);
            if (s2.contains(substring) || s2.contains(ScrumbleSubstring)){
                res1 = true;
            } else {
                res1 = isScrumble(substring, s2, medio);
            }
        } else {
            res1 = true;
        }

        substring = getSubstring(s1, medio, end1);
        String ScrumbleSubstring = scrumble(substring, substring.length()/2);
        if (s2.contains(substring) || s2.contains(ScrumbleSubstring)){
            res2 = true;
        } else {
            if (substring.length() > 2)
                res2 = isScrumble(substring, s2, (end1 - medio));
        }
        return res1 && res2;
    }
}