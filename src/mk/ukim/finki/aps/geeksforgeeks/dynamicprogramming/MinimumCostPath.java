package mk.ukim.finki.aps.geeksforgeeks.dynamicprogramming;

import java.util.Scanner;

public class MinimumCostPath {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        for (int c = 0; c < T; c++) {
            int N = in.nextInt();
            int[][] matrix = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = in.nextInt();
                }
            }

            int[][] dM = new int[N][N];
            for (int i = 0; i < N; i++) {
                if (i == 0) {
                    dM[0][0] = matrix[0][0];
                } else {
                    dM[0][i] = dM[0][i - 1] + matrix[0][i];
                    dM[i][0] = dM[i - 1][0] + matrix[i][0];
                }
            }

            for (int i = 1; i < N; i++) {
                for (int j = 1; j < N; j++){
                    dM[i][j] = Math.min(dM[i-1][j],dM[i][j-1]) + matrix[i][j];
                }
            }

            printMatrix(dM);
        }
    }

    public static void printMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

}
