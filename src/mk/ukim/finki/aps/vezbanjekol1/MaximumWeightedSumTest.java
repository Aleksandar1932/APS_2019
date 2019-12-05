package mk.ukim.finki.aps.vezbanjekol1;

import java.util.Collections;
import java.util.Scanner;

public class MaximumWeightedSumTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        int[] auxArray = new int[k];

        for(int i = 0 ; i < k ; i++){
            auxArray[i] = -666;
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0; j<i;j++){
                if(array[i] > array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        for(int i = 0 ; i < k ; i++){
            auxArray[i] = array[i];
        }

        int p = k;
        int total = 0;
        for(int i = 0; i<k; i++){
            total += p * auxArray[i];
            p--;
        }

        System.out.println(total);



    }

    public static void printArr(int[] array){
        int n = array.length;
        for(int i = 0 ; i < n ;i ++){
            System.out.print(array[i] + " ");
        }
    }
}
