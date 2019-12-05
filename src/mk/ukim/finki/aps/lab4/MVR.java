package mk.ukim.finki.aps.lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Person implements Comparable<Person> {
    private String name;
    private int priorityNumber;

    Person(String name, int priorityNumber) {
        this.name = name;
        this.priorityNumber = priorityNumber;
    }

    @Override
    public String toString() {
        return "" + name;
    }

    @Override
    public int compareTo(Person person) {
        return Integer.compare(this.priorityNumber, person.priorityNumber);
    }
}


public class MVR {
    public static void main(String[] args) {
        ArrayList<Person> queue = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());

        for (int i = 0; i < N; i++) {
            String name = in.nextLine();
            String s1 = in.nextLine();
            String s2 = in.nextLine();
            String s3 = in.nextLine();
            queue.add(toPerson(name, s1, s2, s3));
        }

        Collections.sort(queue);

        for (Person person : queue) {
            System.out.println(person.toString());
        }

    }

    private static Person toPerson(String name, String s1, String s2, String s3) {
        String priorityNumberString = s3 + s2 + s1;
        int priorityNumber = Integer.parseInt(priorityNumberString, 2);
        return new Person(name, priorityNumber);
    }

}
