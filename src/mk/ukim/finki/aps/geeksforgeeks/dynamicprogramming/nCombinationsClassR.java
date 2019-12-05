package mk.ukim.finki.aps.geeksforgeeks.dynamicprogramming;

import java.util.Scanner;

/*
Find nCr for given n and r.
 */
public class nCombinationsClassR {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int c = 0; c < T; c++) {
            long n = in.nextLong();
            long r = in.nextLong();

            long[][] matrix = new long[(int) n][(int) (n + 1)];

            matrix[0][0] = 1;

            for (int i = 1; i < n; i++) {
                matrix[i][0] = 1;
                matrix[i][i + 1] = 1;
            }
            matrix[1][1] = 2;

            for (int i = 2; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = matrix[i - 1][j - 1] + matrix[i - 1][j];
                }
            }

            // printMatrix(matrix);

            System.out.println((matrix[(int) (n - 1)][(int) r]) % 1000000007);
        }
    }

    public static void printMatrix(long[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

}
