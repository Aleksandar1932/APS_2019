package mk.ukim.finki.aps.vezbanjekol2;

import mk.ukim.finki.aps.lab9.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DedoMrazIRibite {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); //Broj na ezera;
        int M = Integer.parseInt(br.readLine()); //Broj na reki;

        Graph<String> graph = new Graph<>(N, generateEmptyStringArray(N));
        populateGraphNodesInfo(graph, N);

        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");
            Integer x = Integer.parseInt(tokens[0]);
            Integer y = Integer.parseInt(tokens[1]);

            graph.addEdge(x, y);
        }

        int X = Integer.parseInt(br.readLine());
        ArrayList<String> bfsList = graph.bfsToList(X);
        System.out.println(bfsList.size() - 1);
    }

    public static void populateGraphNodesInfo(Graph<String> graph, int N) {
        for (int i = 0; i < N; i++) {
            graph.getNodeByID(i).setInfo("" + i);
        }
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


