package mk.ukim.finki.aps.vezbanjekol2;

import mk.ukim.finki.aps.lab10.weightedGraph.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Paytolls {
    /*
        Pravam -1 na ID-ata bidejki numeracijata na gradovite zapocnuva od 1, a ne od 0;
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Graph<String> graph = new Graph<>(N, generateEmptyStringArray(N));

        for (int i = 0; i < N; i++) {
            graph.getNode(i).setInfo("" + i);
        }

        String[] cities = br.readLine().split("\\s+");
        int fromID = Integer.parseInt(cities[0]) - 1;
        int toID = Integer.parseInt(cities[1]) - 1;

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");

            int x = Integer.parseInt(tokens[0]) - 1;
            int y = Integer.parseInt(tokens[1]) - 1;
            float w = Float.parseFloat(tokens[2]);

            graph.addEdge(x, y, w);
            graph.addEdge(y, x, w);

        }
        System.out.println((int) graph.dijkstra(fromID, toID)[toID]);

    }

    public static String[] generateEmptyStringArray(int n) {
        //Pomosna metoda koja generira niza od n prazni stringovi koi se postavuvaat kako inicijalni vrednosti
        //na teminjata na grafot
        String[] retArray = new String[n];
        for (int i = 0; i < n; i++) {
            retArray[i] = "";
        }
        return retArray;
    }
}
