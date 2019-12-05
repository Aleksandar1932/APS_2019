package mk.ukim.finki.aps.vezbanje1;

/*
Da se stavat parni na pocetok, neparni na kraj;
 */
public class SegregateEvenOddArray {
    private static void printArray(int[] a){
        for (int value : a) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    private static void segregateArray(int[] a) {
        int k = 0;

        for (int i = 0; i < a.length; i++) {
           if(a[i]%2==0){
               swapArrayValues(a, k, i);
               k++;
           }
        }
    }

    private static void swapArrayValues(int[] a, int k, int i) {
        int temp = a[k];
        a[k]=a[i];
        a[i]=temp;
    }

    public static void main(String[] args) {
        int[] a = {15, 17, 5, 6, 19, 8, 20};
        printArray(a);
        segregateArray(a);
        printArray(a);
    }

}
