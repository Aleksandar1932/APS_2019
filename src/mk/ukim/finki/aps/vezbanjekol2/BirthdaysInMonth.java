package mk.ukim.finki.aps.vezbanjekol2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/*
    Za daden mesec da se ispecatat iminjata na rodenite lugje, no ako ima isto ime dvapati vo toj mesec
    togas toa ime da ne se pecati, na krajot da nema duplikati;
 */
public class BirthdaysInMonth {
    public static void main(String[] args) throws IOException {
        //Deklariranje pomosni raboti
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Deklariranje na podatocni strukturi
        Map<Integer, HashSet<String>> monthBirthdays = new HashMap<>(); //Glavna mapa;

        //Inicijalizacija na mapa;
        for (int i = 1; i <= 12; i++) {
            monthBirthdays.put(i, new HashSet<>());
        }

        //Citanje na input;
        int N = Integer.parseInt(br.readLine()); //broj na input;
        for (int i = 0; i < N; i++) {
            String inputLine = br.readLine();
            String[] tokens = inputLine.split(" ");

            String name = tokens[0];
            Integer month = getBirthdayMonth(tokens[1]);

            monthBirthdays.get(month).add(name);
        }

        Integer queryMonth = Integer.parseInt(br.readLine());
        System.out.println(monthBirthdays.get(queryMonth));


    }

    public static int getBirthdayMonth(String birthdayString) {
        return Integer.parseInt(birthdayString.split("\\.")[1]);
    }
}
