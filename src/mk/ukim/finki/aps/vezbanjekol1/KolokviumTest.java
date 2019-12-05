package mk.ukim.finki.aps.vezbanjekol1;

import java.util.Scanner;

class ArrayQueue<E> {
    E[] elems;
    int length, front, rear;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty() {
        return (length == 0);
    }

    public int size() {
        return length;
    }

    public E peek() {
        if (length > 0)
            return elems[front];
        else {
            System.out.println("PEEK failed");
            return null;
        }
    }

    public void clear() {
        length = 0;
        front = rear = 0;
    }

    public void enqueue(E x) {
        elems[rear++] = x;
        if (rear == elems.length)
            rear = 0;
        length++;
    }

    public E dequeue() {
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length)
                front = 0;
            length--;
            return frontmost;
        } else {
            System.out.println("Redicata e prazna");
            return null;
        }
    }
}

public class KolokviumTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int perSlot = in.nextInt(); //Kapacitet po termin;
        int m = in.nextInt(); //Se prijavile matematika;
        ArrayQueue<String> math = new ArrayQueue<>(100);
        for (int i = 0; i < m; i++) {
            String student = in.next();
            math.enqueue(student);
        }

        int n = in.nextInt(); //Se prijavile samo APS:
        ArrayQueue<String> aps = new ArrayQueue<>(100);
        for (int i = 0; i < n; i++) {
            String student = in.next();
            aps.enqueue(student);
        }

        int k = in.nextInt(); //Vistinski polagaat APS;
        ArrayQueue<String> realMath = new ArrayQueue<>(100);
        for (int i = 0; i < k; i++) {
            String student = in.next();
            realMath.enqueue(student);
        }

        ArrayQueue<String> schedule = new ArrayQueue<>(200); //100 e redundantno no za sigurnost
        ArrayQueue<String> liars = new ArrayQueue<>(100);


        int counterLiars = 0;
        for (int i = 0; i < m; i++) {
            if (math.isEmpty()) {
                break;
            }
            // System.out.println(math.peek());
            //Raspredeluvanje na onie koi polagaat matematika prvi;
            if (realMath.isEmpty()) {
                break;
            }
            if (math.peek().equals(realMath.peek())) {
                //OK e studentot ne laze, stvarno polaga matematika, stavi go na lisata;
                schedule.enqueue(math.peek());
                math.dequeue();
                realMath.dequeue();
            } else if (math.peek() != realMath.peek()) {
                if (math.isEmpty()) {
                    break;
                }
                liars.enqueue(math.peek());
                math.dequeue();
            }
        }

        for (int i = 0; i < n; i++) {
            schedule.enqueue(aps.dequeue());
        }


        //I na kraj gi stavame lazacite;
        int limit = liars.length;
        for (int i = 0; i < limit; i++) {
            System.out.println(liars.peek());
            schedule.enqueue(liars.peek());
            liars.dequeue();
        }

        System.out.println("===============");


        int toI = schedule.size()/perSlot;
        for (int i = 0; i<=100; i++) {
            System.out.println("Termin: "+ (i + 1));
            for (int j = 0; j < perSlot; j++) {
                if(schedule.isEmpty()){
                    break;
                }
                System.out.println("\t" + schedule.dequeue());
            }
        }

    }

}
