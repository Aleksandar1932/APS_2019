package mk.ukim.finki.aps.geeksforgeeks.dynamicprogramming;

import java.util.Scanner;

public class nthFibonacciNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int testCases = 0; testCases < T; testCases++) {
            int N = in.nextInt();

            long prev = 1;
            long curr = 1;
            long next = prev + curr;

            for (int i = 1; i < N; i++) {
                next = (prev + curr) % 1000000007;
                prev = curr;
                curr = next;

            }

            System.out.println(prev);
        }
    }
}
