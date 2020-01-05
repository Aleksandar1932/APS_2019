package mk.ukim.finki.aps.lab10;

import mk.ukim.finki.aps.lab10.weightedGraph.Edge;
import mk.ukim.finki.aps.lab10.weightedGraph.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Cities {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> citiesAndIDs = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //START: Reading info;
        Integer N = Integer.parseInt(br.readLine()); //Number of cities;
        Integer M = Integer.parseInt(br.readLine()); //Number of roads;
        String[] nodesInfo = generateEmptyStringArray(N);
        Graph<String> graph = new Graph<>(N, nodesInfo);
        //END: Reading info;

        //START: Reading roads;
        for (int i = 0; i < M; i++) {
            String roadLine = br.readLine();
            String[] tokens = roadLine.split("\\s+");

            int id1 = Integer.parseInt(tokens[0]);
            String city1 = tokens[1];
            int id2 = Integer.parseInt(tokens[2]);
            String city2 = tokens[3];
            float distance = Float.parseFloat(tokens[4]);

            //1.Imenuvaj
            graph.getNode(id1).setInfo(city1);
            graph.getNode(id2).setInfo(city2);
            //2. Dodadi konekcija
            graph.addEdge(id1, id2, distance);
            //3. Dodadi vo mapa
            citiesAndIDs.putIfAbsent(city1, id1);
            citiesAndIDs.putIfAbsent(city2, id2);
        }
        //END: Reading roads; <-- Ovde veke ja imame celata RoadMap na makedonija ili sto i da e.

        String startCity = br.readLine();
        String endCity = br.readLine();
        int startCityID = citiesAndIDs.get(startCity);
        int endCityID = citiesAndIDs.get(endCity);

        List<Edge> startToEnd = graph.prim(startCityID);
        List<Edge> endToStart = graph.prim(endCityID);


//        ArrayList<Float> way1 = graph.dijkstra(startCityID,endCityID);
//        ArrayList<Float> way2 = graph.dijkstra(endCityID,startCityID);

        int[] way1 = graph.dijkstraINT(startCityID,endCityID);
        ArrayList<Integer> w1 = new ArrayList<>();
        for(int a : way1){
            w1.add(a);
        }
        Collections.reverse(w1);
        int[] way2 = graph.dijkstraINT(endCityID,startCityID);
        ArrayList<Integer> w2 = new ArrayList<>();
        for(int a : way2){
            w2.add(a);
        }
        Collections.reverse(w2);

        float[] distance1 = graph.dijkstra(startCityID,endCityID);
        float[] distance2 = graph.dijkstra(endCityID,startCityID);

        float totalDistance = distance1[endCityID] + distance2[startCityID];

        printRoute(graph,w1);
        System.out.println();
        printRoute(graph,w2);
        System.out.println();
        System.out.println(totalDistance);



    }

    public static void printRoute(Graph<String> graph, ArrayList<Integer> route){
        route.stream().forEach(id->{
            System.out.print(graph.getNode(id).getInfo() + " ");
        });
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
