package mk.ukim.finki.aps.av3;

public class FractionalKnapsack {
    private static void sort(int[] profit, int[] weight, int n) {
        //TODO implement
        int i, j, tempP, tempW;
        for (i = 0; i < n; i++) {
            for (j = i; j < n; j++) {
                if ((profit[i] / (float) weight[i]) < (profit[j] / (float) weight[j])) {
                    tempP = profit[i];
                    tempW = weight[i];
                    profit[i] = profit[j];
                    weight[i] = weight[j];
                    profit[j] = tempP;
                    weight[j] = tempW;
                }
            }
        }
    }

    private static float greatestFractionalKnapsackProfit(int[] profit, int[] weight, float C, int n, float[] x) {
        sort(profit, weight, n); //Objects are sorted by following condition :=> profit[i]/weight[i] >= profit[i+1]/weight[i+1]

        int i;
        float profitRet = 0;

        for (i = 0; i < n; i++) {
            x[i] = 0;
        }

        for (i = 0; i < n; i++) {
            if (C > weight[i]) {
                x[i] = 1;
                C = C - weight[i];
                profitRet = profitRet + profit[i];
            } else {
                x[i] = (C / (float) weight[i]);
                profitRet += x[i] * (float) profit[i];
                C = 0;
                break;
            }
        }

        return profitRet;
    }

    public static void main(String[] args) {

        int[] profit = {25,24,15}; //Object profits array
        int[] weight={18,15,10}; //Object weights array
        float[] x={0,0,0}; //Fractions array
        float C = 20; //Capacity of backpack
        int n = 3; //Number of objects


        System.out.println(greatestFractionalKnapsackProfit(profit,weight,C,n,x));
    }
}
