package mk.ukim.finki.aps.vezbanjekol1;

import java.util.Scanner;

public class StackBukvi {
    private static int checkValidity(String seq) {
        String[] splitted = seq.split("S");

        int retValue = 1;
        int compare = countT(splitted[1]);
        for (int i = 2; i < splitted.length; i++) {
            if (countT(splitted[i]) != compare) {
                retValue = 0;
                break;
            }
        }
        return retValue;
    }

    private static int countT(String s) {
        int retValue = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'T') {
                retValue++;
            }
        }
        return retValue;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String seq = in.nextLine();
        System.out.println(checkValidity(seq));
    }
}
