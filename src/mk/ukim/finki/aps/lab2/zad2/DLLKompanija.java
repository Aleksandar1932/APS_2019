package mk.ukim.finki.aps.lab2.zad2;

import java.util.Scanner;

public class DLLKompanija {

    private static void brisiVraboteni(DLL<Vraboten> lista, int minimalenIznos) {
        DLLNode<Vraboten> temp = lista.getFirst();
        while (temp != null) {
            if (temp.element.getSalary() < minimalenIznos) {
                lista.deleteNode(temp);
            }
            temp = temp.succ;
        }
    }

    private static void sort(DLL<Vraboten> lista) {
        for (int i = 0; i < lista.getSize(); i++) {
            DLLNode<Vraboten> temp = lista.getFirst();

            while (temp.succ != null) {
                if (temp.element.getID() < temp.succ.element.getID()) {
                    swapVraboteni(temp, temp.succ);
                }
                temp = temp.succ;
            }
        }
    }

    private static void swapVraboteni(DLLNode<Vraboten> v1, DLLNode<Vraboten> v2) {
        int tempID, tempSalary;
        tempID = v1.element.getID();
        v1.element.setID(v2.element.getID());
        v2.element.setID(tempID);
        tempSalary = v1.element.getSalary();
        v1.element.setSalary(v2.element.getSalary());
        v2.element.setSalary(tempSalary);
    }

    public static void main(String[] args) {
        DLL<Vraboten> lista = new DLL<>();
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        for (int i = 0; i < N; i++) {
            int id = input.nextInt();
            int plata = input.nextInt();
            Vraboten v = new Vraboten(id, plata);
            lista.insertLast(v);
        }
        int minimalenIznos = input.nextInt();

        brisiVraboteni(lista, minimalenIznos);
        if (lista.getFirst() == null) {
            System.out.println("nema");
        } else {
            sort(lista);
            System.out.println(lista.toString());
        }

    }
}
