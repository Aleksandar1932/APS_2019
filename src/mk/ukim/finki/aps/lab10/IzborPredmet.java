package mk.ukim.finki.aps.lab10;

import mk.ukim.finki.aps.lab9.Graph;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class IzborPredmet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //Broj na jazli;

        String[] nodesInfo = new String[N];
        Map<String, Integer> nodesIDs = new HashMap<>(); //So pomos na ovaa mapa, mozam da gi zimam ID-ata koga ke go polnam grafot;
        for (int i = 0; i < N; i++) {
            String key = br.readLine();
            nodesInfo[i] = key;
            nodesIDs.put(key, i);
        }

        Graph<String> graph = new Graph<>(N, nodesInfo);

        int M = Integer.parseInt(br.readLine());//Broj na zavisnosti;
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");
            String mainNodeKey = tokens[0];
            int mainNodeID = nodesIDs.get(mainNodeKey);

            Arrays.stream(tokens).skip(1).forEach(s -> {
                graph.addEdge(nodesIDs.get(s), mainNodeID);
            });
        }


        String node = br.readLine();
        int nodeId = nodesIDs.get(node);
        Arrays.stream(graph.getNodeByID(nodeId).getNeighborsArray()).forEach(number->{
            System.out.println(number +": " + graph.getNodeByID(number).getInfo());
        });
    }
}
