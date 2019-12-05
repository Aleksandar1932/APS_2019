package mk.ukim.finki.aps.av4;

import java.util.Scanner;

public class BinomialCoefficients {
    public static void printMatrix(int[][] mat, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter n: ");
        int n = in.nextInt();
        n++;

        int[][] pascalMatrix = new int[n][n];

        pascalMatrix[0][0] = -1;
        pascalMatrix[1][0] = 1;
        pascalMatrix[2][1] = 2;
        for (int i = 2; i < n; i++) {
            pascalMatrix[i][0] = 1;
            pascalMatrix[i][i] = 1;
        }

        for (int i = 3; i < n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                pascalMatrix[i][j] = pascalMatrix[i-1][j-1] + pascalMatrix[i-1][j];
            }
        }
        printMatrix(pascalMatrix, n);
    }
}
