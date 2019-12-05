package mk.ukim.finki.aps.lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OddEvenSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        ArrayList<Integer> arrayEven = new ArrayList<>();
        ArrayList<Integer> arrayOdd = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int element = in.nextInt();
            if (element % 2 == 0) {
                arrayEven.add(element);
            } else if (element % 2 == 1) {
                arrayOdd.add(element);
            }
        }
        printOddEvenSorted(arrayEven, arrayOdd);
    }

    private static void printOddEvenSorted(ArrayList<Integer> arrayEven, ArrayList<Integer> arrayOdd) {
        Collections.sort(arrayOdd);
        arrayEven.sort(Collections.reverseOrder());

        //Printing the two separate arrays
        for (Integer value : arrayOdd) {
            System.out.print(value + " ");
        }

        for (Integer integer : arrayEven) {
            System.out.print(integer + " ");
        }
    }
}
