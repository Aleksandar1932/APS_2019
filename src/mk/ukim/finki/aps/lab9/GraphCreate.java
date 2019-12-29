//package mk.ukim.finki.aps.lab9;
//
//import java.util.*;
//
//class Graph {
//    List<String> nodes;
//    int[][] ajdMatrix;
//
//    public Graph() {
//        nodes = new ArrayList<>();
//        ajdMatrix = new int[0][0];
//    }
//
//    public Graph(int numNodes) {
//        nodes = new ArrayList<>();
//        for (int i = 0; i < numNodes; i++) {
//            nodes.add(i, Arrays.toString(Character.toChars(65 + i)));
//        }
//
//        ajdMatrix = new int[numNodes][numNodes];
//
//        for (int i = 0; i < numNodes; i++) {
//            for (int j = 0; j < numNodes; j++) {
//                ajdMatrix[i][j] = 0;
//            }
//        }
//
//    }
//
//    public void addEdge(int node1, int node2) {
//        ajdMatrix[node1][node2] = 1;
//        ajdMatrix[node2][node1] = 1;
//    }
//
//    public void deleteEdge(int node1, int node2) {
//        ajdMatrix[node1][node2] = 0;
//        ajdMatrix[node2][node1] = 0;
//    }
//
//    public boolean adjacent(int node1, int node2) {
//        return ajdMatrix[node1][node2] == 1 && ajdMatrix[node2][node1] == 1;
//    }
//
//    public void printMatrix() {
//        for (int[] matrix : ajdMatrix) {
//            for (int j = 0; j < ajdMatrix.length; j++) {
//                System.out.print(matrix[j] + " ");
//            }
//            System.out.println();
//        }
//    }
//
//    public void printNode(int nodeIndex) {
//        System.out.println(nodes.get(nodeIndex).substring(1, 2));
//    }
//}
//
//public class GraphCreate {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int N = in.nextInt();
//
//        Graph graph = new Graph();
//
//        for (int i = 0; i < N + 1; i++) {
//            String command = in.nextLine();
//            if (command.contains("CREATE")) {
//               graph = createGraph(command);
//            }
//            processCommand(command, graph);
//        }
//    }
//
//    public static Graph createGraph(String command){
//        String[] tokens = command.split("\\s+");
//        int numNodes = Integer.parseInt(tokens[1]);
//        return new Graph(numNodes);
//    }
//
//    public static void processCommand(String command, Graph graph) {
//        if (command.contains("ADDEDGE")) {
//            String[] tokens = command.split("\\s+");
//            int node1 = Integer.parseInt(tokens[1]);
//            int node2 = Integer.parseInt(tokens[2]);
//            graph.addEdge(node1, node2);
//        } else if (command.contains("DELETEEDGE")) {
//            String[] tokens = command.split("\\s+");
//            int node1 = Integer.parseInt(tokens[1]);
//            int node2 = Integer.parseInt(tokens[2]);
//            graph.deleteEdge(node1, node2);
//        } else if (command.contains("ADJACENT")) {
//            String[] tokens = command.split("\\s+");
//            int node1 = Integer.parseInt(tokens[1]);
//            int node2 = Integer.parseInt(tokens[2]);
//            if (graph.adjacent(node1, node2)) {
//                System.out.println("1");
//            } else {
//                System.out.println("0");
//            }
//        } else if (command.contains("PRINTMATRIX")) {
//            graph.printMatrix();
//        } else if (command.contains("PRINTNODE")) {
//            String[] tokens = command.split("\\s+");
//            int node = Integer.parseInt(tokens[1]);
//            graph.printNode(node);
//        }
//    }
//}
