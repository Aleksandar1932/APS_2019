package mk.ukim.finki.aps.lab5;

import java.util.ArrayList;
import java.util.Scanner;

public class ShakerSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(in.nextInt());
        }
        shakerSort(list);
    }

    public static void shakerSort(ArrayList<Integer> array) {
        int min = array.get(0), max = array.get(0);
        int minIndex = 0, maxIndex = 0;
        int start = 0, end = array.size();
        boolean sorted = true;
        int counter = 0;
        while (counter < array.size() / 2 -1) {
            minIndex = 0;
            maxIndex = 0;
            sorted = false;
            min = 1000;
            max = 0;
            for (int i = start; i < end; i++) {
                if (array.get(i) <= min) {
                    min = array.get(i);
                    minIndex = i;
                }
            }
            array.remove(minIndex);
            array.add(start, min);
            printArr(array);


            for (int i = start; i < end; i++) {
                if (array.get(i) > max) {
                    max = array.get(i);
                    maxIndex = i;
                }
            }


            array.remove(maxIndex);
            array.add(end - 1, max);
            printArr(array);
            start++;
            end--;

            counter++;
        }
    }

    private static void printArr(ArrayList<Integer> arr) {
        System.out.println();
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
    }
}
