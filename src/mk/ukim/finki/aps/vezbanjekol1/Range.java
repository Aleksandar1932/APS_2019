package mk.ukim.finki.aps.vezbanjekol1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Range {

    private static long s(long x) {
        long sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    private static long proveri(long N, long A, long B) {
        if (A == B) {
            return -1;
        }
        if (A * A + 200 * A + s(A) == N) {
            return A;
        }
        if (B * B + 200 * B + s(B) == N) {
            return B;
        }
        long mid = (A + B) / 2; //Najdi go elementot na sredinata na intervalot
        long temp = mid * mid + 200 * mid + s(mid); //Presmeti ja ravenkata za sredisniot element;

        if (temp > N) {
            return proveri(N, A, mid);
        } else if (temp < N) {
            return proveri(N, mid, B);
        } else return mid; //Znaci elementot na sredinata e baraniot x;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long res = proveri(N, A, B);
        System.out.println(res);
        br.close();
    }
}
