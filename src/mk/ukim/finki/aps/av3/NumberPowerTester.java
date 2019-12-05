package mk.ukim.finki.aps.av3;

import java.util.Scanner;

public class NumberPowerTester {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Vnesete broj: ");
        int x=in.nextInt();
        System.out.println("Vnesete koj stepen od toj broj go sakate: ");
        int n=in.nextInt();
        System.out.println("("+x+"^"+n+") e ednakvo na: "+NumberPower.nthPower(x,n));
    }
}
