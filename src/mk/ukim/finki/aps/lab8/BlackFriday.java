package mk.ukim.finki.aps.lab8;

import java.io.*;
import java.util.*;

public class BlackFriday {
    public static void main(String[] args) throws IOException {
        HashMap<Integer, Integer> countingMap = readEntriesInStore();
        System.out.println(findMaxInMap(countingMap));

         /*
            Logika:

            Vo HashTabela cuvam 1440 koficki, t.e. za sekoja mozna minuta, pa za sekoj interval gi nakacuvam
            brojacite na minutite, i na krajot problemot se sveduva za baranje na maksimum value vo mapa;

            Key     Value
            0       0
            1       0
            2       1
            3       1
            .
            .
            .
            1440    5

            primer za intervalot [0, 123] min, t.e. vo 00:00 chasot vlez, izlez vo 03:03 chasot;
            od 0 do 123 vo tabelata inkerementi za soodvetnite minuti;

            Aleksandar Ivanovski;
         */
    }

    private static HashMap<Integer, Integer> readEntriesInStore() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> countingMap = new HashMap<>();
        int N = Integer.parseInt(br.readLine()); //broj na lugje
        for (int i = 0; i < N; i++) {

            String line = br.readLine();
            String[] tokenizedLine = line.split("\\s+");
            String enterTime = tokenizedLine[0];
            int durationInside = Integer.parseInt(tokenizedLine[1]);

            String[] tokens = enterTime.split(":");
            /*
                tokens[0] - cas na vlez;
                tokens[1] - minuta na vlez;
                vreme na vlez = tokens[0]*60 + tokens[1];
             */

            int enterTimeMinutes = Integer.parseInt(tokens[0]) * 60 + Integer.parseInt(tokens[1]); //minuta na vlez;
            int exitTimeMinutes = enterTimeMinutes + durationInside; //minuta na izlez;

            for (int j = enterTimeMinutes; j <= exitTimeMinutes; j++) {
                countingMap.merge(j, 1, Integer::sum);
            }

        }
        return countingMap;
    }

    public static int findMaxInMap(HashMap<Integer, Integer> map) {
        return Collections.max(map.values());
    }

}
