package mk.ukim.finki.aps.lab3.zad2;

import java.util.Scanner;

public class ZigZagSequence {
    private static int findLongestZigZagSequence(int[] a) {
        int max = 0;
        for (int i = 0; i < a.length - 1; i++) {
            int count = 1;
            for (int j = i; j < a.length - 1; j++) {
                if ((isPositive(a[j]) && isPositive(a[j + 1])) || (!isPositive(a[j]) && !isPositive(a[j + 1])) || a[j] == 0 || a[j + 1] == 0) {
                    break;
                }
                count++;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    private static boolean isPositive(int number) {
        return (number > 0);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = in.nextInt();
        }
        System.out.println(findLongestZigZagSequence(array));
    }
}
