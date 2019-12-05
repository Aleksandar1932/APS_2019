package mk.ukim.finki.aps.lab2.zad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Homework {

    private static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i] > a[j]) {
                    swapValues(a, i, j);
                }
            }
        }
    }

    private static void swapValues(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static int minBrojKazneni(int[] a) {
        sort(a);
        int totalSum = 0;
        int ithSum;
        for (int i = 0; i < a.length; i++) {
            ithSum = 0;
            for (int j = 0; j <= i; j++) {
                ithSum += a[j];
            }
            totalSum += ithSum;
        }
        return totalSum;
    }


    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N];
        for (i = 0; i < N; i++)
            a[i] = Integer.parseInt(br.readLine());
        int rez = minBrojKazneni(a);
        System.out.println(rez);
        br.close();
    }
}