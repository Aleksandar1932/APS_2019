package mk.ukim.finki.aps.av4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CheapestTripTest {
    static int cost[][];
    static int tax[], best[];
    static int n;
    static int INFINITY = 1000000;

    public static void main(String[] args) throws Exception {
        int i, j;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Vnesi broj na gradovi:");
        n = Integer.parseInt(br.readLine());

        tax = new int[n];
        best = new int[n];
        cost = new int[n][n];

        for (i = 0; i < n; i++) {
            System.out.println("Vnesi aerodromska taksa za gradot " + (i + 1) + " : ");
            tax[i] = Integer.parseInt(br.readLine());

            for (j = i + 1; j < n; j++) {
                System.out.println("Cena na bilet od " + (i + 1) + " do " + (j + 1) + " : ");
                cost[i][j] = Integer.parseInt(br.readLine());
            }
        }

        best[0] = tax[0]; // za prviot grad se plakja samo taksa

        for (i = 1; i < n; i++) {
            best[i] = INFINITY; // inicijalizacija
            // go barame optimalniot grad j, od koj bi patuvale do gradot i
            for (j = 0; j < i; j++)
                best[i] = Math.min(best[i], best[j] + cost[j][i] + tax[i]);
        }

        System.out.println("Najmala cena e " + best[n - 1]);
    }
}
