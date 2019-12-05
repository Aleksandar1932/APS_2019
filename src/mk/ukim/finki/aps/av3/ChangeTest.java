package mk.ukim.finki.aps.av3;

/*
Дадени се парички со одредена вредност (постојат парички од 50, 10, 5, 2 и 1 денар), и
притоа бројот на парички од секоја вредност е неограничен. За дадена сума да се определи
најмалиот број на парички кои се потребни да се формира таа сума.
 */
public class ChangeTest {
    public void sortCoins() {
        //TODO implement sortCoins - bubble sort;
    }

    public static int returnCoins(int coins[], int n, int sum, int coinsNum[]) {
        //Se vrakaat brojot na paricki;
        int i = 0;
        int numCoins = 0;
        while (sum > 0) {
            coinsNum[i] = sum / coins[i];
            sum -= coinsNum[i] * coins[i];
            numCoins += coinsNum[i];
            i++;
        }

        /*
        Ostatokot od nizata vo koja se brojat parickite, se popolnuva so 0, se smeta deka tie paricki
        nikogas ne se iskoristile, t.e. nemalo potreba od niv;
         */

        while (i < n) {
            coinsNum[i] = 0;
            i++;
        }

        return numCoins;
    }

    public static void main(String[] args) {
        int[] coins = {50, 10, 5, 2, 1};
        int[] coinsNum = {0, 0 , 0, 0, 0};
        int change = 53;
        System.out.println(returnCoins(coins,5,change,coinsNum));
    }
}
