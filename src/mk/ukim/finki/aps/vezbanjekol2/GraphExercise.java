package mk.ukim.finki.aps.vezbanjekol2;

import mk.ukim.finki.aps.vezbanjekol2.listWeightedDirected.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    Vo ovaa zadaca ke gi probam grafovite i metodite vo gotvite klasi;
    Nasocen Tezinski graf realiziran so lista na sosedstvo;
 */
public class GraphExercise {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //number of nodes;

        Graph<Integer> graph = new Graph<>(N);
        for (int i = 0; i < N; i++)
            graph.adjList[i].setInfo(i);


        int M = Integer.parseInt(br.readLine()); //number of edges;

        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            graph.addEdge(
                    Integer.parseInt(tokens[0]), //x
                    Integer.parseInt(tokens[1]), //y
                    Float.parseFloat(tokens[2]) //tezina;
            );
        }

        System.out.println(graph);
        System.out.println("=== DIJKSTRA TEST ===\n" + Arrays.toString(graph.dijkstra(0)));
        System.out.println("=== MST PRIM TEST ===\n");
        graph.prim(0).forEach(edge -> System.out.println(edge.getFrom() + " -> " + edge.getTo()));

        System.out.println("=== MST KRUSKAL TEST ===\n");
        graph.kruskal().forEach(edge -> System.out.println(edge.getFrom() + " -> " + edge.getTo()));
//
//        int start_index = 0;
//
//        float[] distance = graph.dijkstra(start_index);
//
//        System.out.println("Minimalni ceni so pocetno teme " + start_index + " se:");
//        for (int i = 0; i < distance.length; i++) {
//            System.out.println("Cena od teme" + start_index + " do teme" + i + " e " + distance[i]);
//
//        }




        /*
        6
        8
        0 1 5
        0 4 6
        1 3 4
        2 1 6
        3 0 7
        4 2 5
        4 3 9
        5 4 12
         */


    }
}
