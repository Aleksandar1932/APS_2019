package mk.ukim.finki.aps.vezbanjekol1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DLLVojska {
    public static void main(String[] args) throws IOException {
        DLL<Integer> lista = new DLL<>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] ids = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(ids[i]));
        }

        s = stdin.readLine();
        String[] interval = s.split(" ");
        int a = Integer.parseInt(interval[0]);
        int b = Integer.parseInt(interval[1]);

        s = stdin.readLine();
        interval = s.split(" ");
        int c = Integer.parseInt(interval[0]);
        int d = Integer.parseInt(interval[1]);


        DLL<Integer> result = vojska(lista, a, b, c, d);


        DLLNode<Integer> node = result.getFirst();

        node = node.succ;
        while(node.succ != null) {
            System.out.print(node.element + " ");

            node = node.succ;
        }

    }

    private static DLL<Integer> vojska(DLL<Integer> lista, int a, int b, int c, int d) {

        lista.insertFirst(-999);
        lista.insertLast(-999);

        DLL<Integer> iA = new DLL<>();
        DLL<Integer> iB = new DLL<>();

        DLLNode<Integer> temp = lista.getFirst();

        while (temp != null) {

            if (temp.element == a) {
                while (true) {
                    iA.insertLast(temp.element);
                    if (temp.element == b) {
                        break;
                    }
                    temp = temp.succ;
                }
            }


            if (temp.element == c) {
                while (true) {
                    iB.insertLast(temp.element);
                    if (temp.element == d) {
                        break;
                    }
                    temp = temp.succ;
                }
            }
            temp = temp.succ;
        }

        temp = lista.getFirst();
        while (temp.succ != null) {

            swap(a, b, iB, temp);

            swap(c, d, iA, temp);

            temp = temp.succ;
        }

        return lista;
    }

    private static void swap(int a, int b, DLL<Integer> iB, DLLNode<Integer> temp) {
        if (temp.element == a) {

            DLLNode<Integer> temp2 = temp;
            while (temp2.element != b) {
                temp2 = temp2.succ;
            }

            temp.pred.succ = iB.getFirst();
            temp2.succ.pred = iB.getLast();

            iB.getFirst().pred = temp.pred;
            iB.getLast().succ = temp2.succ;

        }
    }
}