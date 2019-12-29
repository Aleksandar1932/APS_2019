package mk.ukim.finki.aps.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/*
    GraphNode::
        getNeighborsArray():int[] - Funkcija koja vraka niza od int, so id-ata na site sosedi na dadeniot node;
    Graph::
        shortestReach(start)::int[] - Funkcija koja zapocnuvajki od daden jazel so id `start` generira niza so minimalni rastojanija
        od nego do site drugi jazli vo grafot. Koristi modifikacija na BFS i Bellman-Ford ravenka, pri sto inicijalno do sekoj patot e -1
        Potoa gi izminuva site sosedi, pa znaeme deka rastojanieto za bilo koj jazel e za eden pogolemo od ona na negoviot sosed, a rastojanieto
        do sosedot sme go presmetale povtorno preku negoviot sosed, int... pocnuvajki od nasite sosedi koi ni se oddaleceni za 1, i od samite sebe si
        koi sme oddaleceni za 0;
 */

public class CountFacebookFriends {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int N = Integer.parseInt(br.readLine());

        Integer[] nodeInfo = new Integer[N];

        for (int i = 0; i < N; i++) {
            nodeInfo[i] = i;
        }

        Graph<Integer> graph = new Graph<>(N, nodeInfo);

        for (int i = 0; i < N; i++) {
            int numberOfFriends = Integer.parseInt(br.readLine());
            for (int j = 0; j < numberOfFriends; j++) {
                //Za sekoj covek gi citam prijatelite;
                String line = br.readLine();
                int friendId = Integer.parseInt(line.split("\\s+")[0]);
                graph.addEdge(i, friendId);
            }
        }
        int from = Integer.parseInt(br.readLine());
        int to = Integer.parseInt(br.readLine());

        System.out.println(calculateSeparationLevel(graph, from, to));
    }

    public static int calculateSeparationLevel(Graph<Integer> graph, int from, int to) {
        int[] array = graph.shortestReach(from);
        return array[to];
    }

}
