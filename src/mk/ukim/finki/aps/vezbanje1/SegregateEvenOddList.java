package mk.ukim.finki.aps.vezbanje1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SegregateEvenOddList {

    private static <E> void swapValues(SLLNode<E> node1, SLLNode<E> node2) {
        E o = node1.element;
        node1.element = node2.element;
        node2.element = o;
    }

    private static <E> void segregateList(SLL<E> list1) {
        SLLNode<E> temp1 = list1.getFirst();
        SLLNode<E> temp2 = list1.getFirst();

        while (temp1 != null) {
            if ((int) temp1.element % 2 == 0) {
                swapValues(temp1, temp2);
                temp2 = temp2.succ;
            }
            temp1 = temp1.succ;
        }
    }

    public static void main(String[] args) throws IOException {

        SLL<Integer> list1 = new SLL<>();

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] tmpArray = s.split(" ");
        for (int i = 0; i < N; i++) {
            list1.insertLast(Integer.parseInt(tmpArray[i]));
        }

        System.out.println("Inputed list: " + list1.toString());
        segregateList(list1);
        System.out.println("List after segregation: " + list1.toString());
    }
}
