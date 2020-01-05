package mk.ukim.finki.aps.lab10;

import mk.ukim.finki.aps.lab10.weightedGraph.Edge;
import mk.ukim.finki.aps.lab10.weightedGraph.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Hierarchy {
    public static void main(String[] args) throws IOException {
        //Read the info
        Map<String, Integer> namesAndIDs = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //Broj na lugje

        String[] nodeInfo = new String[N];
        for(int i = 0 ; i < N ; i ++){
            nodeInfo[i] = "";
        }

        int M = Integer.parseInt(br.readLine()); //Broj na vrski
        Graph<String> graph = new Graph<>(N,nodeInfo);


        for (int i = 0; i < M; i++) {
            String connection = br.readLine();
            String[] tokens = connection.split("\\s+");


            int id1 = Integer.parseInt(tokens[0]);
            String name1 = tokens[1];
            int id2 = Integer.parseInt(tokens[2]);
            String name2 = tokens[3];
            int weight = Integer.parseInt(tokens[4]); //Proveri logika so obratno 10 - tezina;


            graph.getNode(id1).setInfo(name1);
            graph.getNode(id2).setInfo(name2);
            graph.addEdge(id1,id2,weight);
            graph.addEdge(id2,id1,weight);

            namesAndIDs.put(name1,id1);
            namesAndIDs.put(name2,id2);

        }
        //End of reading graph

        //Read president
        String president = br.readLine();
        int presidentID = namesAndIDs.get(president);
        //End of reading president

        //Calculate MST
        int totalSum = 0;
        List<Edge> mst = graph.prim(presidentID);
        for (Edge e : mst) {
            totalSum += e.getWeight();
        }
        System.out.println(totalSum);
        //END
    }
}
