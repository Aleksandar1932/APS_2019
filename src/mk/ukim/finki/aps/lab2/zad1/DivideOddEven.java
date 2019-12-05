/*
Koristi:
    DLLNode.java
    DLL.java
 */
package mk.ukim.finki.aps.lab2.zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DivideOddEven {
    public static void divideOddEven(DLL<Integer> list) {
        DLLNode<Integer> iterator = list.getFirst();
        DLL<Integer> evenList = new DLL<Integer>();
        /*
            Zaradi memoriska zasteda, vo postoeckata DLL list, kje gi ostavime samo neparnite elementi,
            a vo novokreiranata DLL evenList, kje gi stavime site parni elementi.
         */
        while (iterator != null) {
            if ((iterator.element % 2) == 0) {
                list.deleteNode(iterator);
                evenList.insertLast(iterator.element);
            }
            iterator = iterator.succ;
        }
        System.out.println("Podlista so parni elementi: " + evenList.toString());
        System.out.println("Podlista so neparni elementi: " + list.toString());
    }

    public static void main(String[] args) throws IOException {
        DLL<Integer> lista = new DLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(pomniza[i]));
        }

        System.out.println("Vnesena lista: " + lista.toString());
        divideOddEven(lista);
    }
}