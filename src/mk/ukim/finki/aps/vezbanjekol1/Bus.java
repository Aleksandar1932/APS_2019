package mk.ukim.finki.aps.vezbanjekol1;

import java.io.*;

public class Bus {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int min = 0;
        if (n < m) {
            min = (m / n) * n * 100 + (m - (m / n) * n) * 100;
        }
        if (n > m) {
            min = n * 100;
        }
        int max;
        if (m == 0) {
            max = n * 100;
        } else {
            max = 100 * m + 100 * n - 100;
        }
        System.out.println(min + "\n" + max);
        br.close();

    }
}
