package mk.ukim.finki.aps.vezbanjekol1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class card_trick {
    private static int countCard(int mainCard){
        Queue<Integer> cards = new LinkedList<>();

        for (int i = 1; i <= 51; i++) {
            cards.add(i);
        }

        boolean flag = true;
        int counter = 0;
        while (flag) {
            int[] tempArr = new int[7];
            for (int i = 6; i >= 0; i--) {
                tempArr[i] = cards.remove();
            }
            for(int i = 0 ; i <= 6 ; i++){
                cards.add(tempArr[i]);
                cards.add(cards.remove());
            }
            counter++;
            if(!cards.isEmpty() && cards.peek() == mainCard){
                flag = false;
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int mainCard = in.nextInt();
        System.out.println(countCard(mainCard));
    }


}