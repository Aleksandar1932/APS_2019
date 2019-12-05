package mk.ukim.finki.aps.av4;

public class MarsRobotTest {
    public static void main(String[] args) {
        int a[][] = new int[100][100];
        int best[][] = new int[100][100];

    }

    public int[][] bestSum(int[][] a, int[][] best, int m, int n) {
        //Inicijalizranje na trivijalni vrednosti:
        best[0][0] = a[0][0];

        //Inicijaliziranje na prva kolona - trivijalno
        for (int i = 1; i < n; i++) {
            best[i][0] = best[i - 1][0] + a[i][0];
        }

        //Inicijaliziranje na prva redica - trivijalno
        for (int j = 1; j < m; j++) {
            best[0][j] = best[0][j - 1] + a[0][j];
        }

        //Presmetka na nepoznati optimumi;
        for (int i = 2; i < m; i++) {
            for (int j = 2; j < n; j++) {
                best[i][j] = Math.max((best[i - 1][j] + a[i][j]), best[i][j - 1] + a[i][j]);
            }
        }

        return best;
    }
}
