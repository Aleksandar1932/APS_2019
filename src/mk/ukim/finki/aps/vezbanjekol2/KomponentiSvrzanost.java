package mk.ukim.finki.aps.vezbanjekol2;

import mk.ukim.finki.aps.lab10.weightedGraph.Edge;
import mk.ukim.finki.aps.lab10.weightedGraph.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class KomponentiSvrzanost {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        Graph<String> graph = new Graph<>(N, generateEmptyStringArray(N));
        populateGraphNodesInfo(graph,N);
        for (int i = 0; i < N; i++) {
            int M = in.nextInt();
            for (int j = 0; j < M; j++) {
                int y = in.nextInt();
                graph.addEdge(i, y, 1);
            }
        }

        int keyNode = in.nextInt();


        List<Edge> primList = graph.prim(keyNode);
        ArrayList<Integer> componentNodesList = new ArrayList<>();

        primList.stream().forEach(edge -> {
            componentNodesList.add(edge.getTo());
        });

        componentNodesList.add(keyNode);

        componentNodesList.stream().sorted().forEach(System.out::println);



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

    public static void populateGraphNodesInfo(Graph<String> graph, int N) {
        for (int i = 0; i < N; i++) {
            graph.getNode(i).setInfo("" + i);
        }
    }


}
