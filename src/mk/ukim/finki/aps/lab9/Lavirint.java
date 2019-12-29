//package mk.ukim.finki.aps.lab9;
//
///*
//KOPIRANA TI E OD PASTE BIN RESI SI JA SAM!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// */
//import java.util.Hashtable;
//import java.util.LinkedList;
//import java.util.Scanner;
//import java.util.Stack;
//
//
//public class Lavirint {
//
//
//    Graph graph;
//    int start;
//    int end;
//    Hashtable<Integer, String> hash;
//    Hashtable<String, Integer> rehash;
//
//    Lavirint() {
//        hash = new Hashtable<>();
//        rehash = new Hashtable<>();
//    }
//
//    void createGraph(int rows, int columns, String [] input) {
//
//        int numNodes = 0;
//        String key;
//        GraphNode nodes[] = new GraphNode[rows * columns];
//
//        for (int i=0; i < rows; ++i) {
//            for (int j=0; j < columns; ++j) {
//                if (input[i].charAt(j) != '#') {
//                    key = i + "," + j;
//                    hash.put(numNodes,key);
//                    rehash.put(key, numNodes);
//                    nodes[numNodes] = new GraphNode(numNodes, key);
//                    if (input[i].charAt(j) == 'S')
//                        start = numNodes;
//                    if (input[i].charAt(j) == 'E')
//                        end = numNodes;
//                    ++numNodes;
//                }
//            }
//        }
//
//        graph = new Graph(numNodes,nodes);
//        int x;
//        int y;
//        for (int i=0; i < rows; ++i) {
//            for (int j=0; j < columns; ++j) {
//
//                if (input[i].charAt(j) != '#') {
//                    if (input[i].charAt(j-1) != '#') {
//                        x = rehash.get(i + "," + j);
//                        y = rehash.get(i + "," + (j-1));
//                        graph.addEdge(x, y);
//                    }
//
//                    if (input[i].charAt(j+1) != '#') {
//                        x = rehash.get(i + "," + j);
//                        y = rehash.get(i + "," + (j+1));
//                        graph.addEdge(x, y);
//                    }
//
//                    if (input[i-1].charAt(j) != '#') {
//                        x = rehash.get(i + "," + j);
//                        y = rehash.get((i-1) + "," + j);
//                        graph.addEdge(x, y);
//                    }
//
//                    if (input[i+1].charAt(j) != '#') {
//                        x = rehash.get(i + "," + j);
//                        y = rehash.get((i+1) + "," + j);
//                        graph.addEdge(x, y);
//                    }
//
//                }
//
//            }
//        }
//
//    }
//
//    void findPath() {
//        boolean visited[] = new boolean[graph.num_nodes];
//        for (int i = 0; i < graph.num_nodes; i++)
//            visited[i] = false;
//        visited[start] = true;
//        Stack<Integer> s = new Stack<Integer>();
//        s.push(start);
//
//        GraphNode pom;
//
//        while (!s.isEmpty()&&s.peek() != end) {
//            pom = graph.adjList[s.peek()];
//            GraphNode tmp=null;
//            for (int i = 0; i < pom.getNeighbors().size(); i++) {
//                tmp = pom.getNeighbors().get(i);
//                if (!visited[tmp.getIndex()])
//                    break;
//            }
//            if(tmp!=null&&!visited[tmp.getIndex()]){
//                visited[tmp.getIndex()] = true;
//                s.push(tmp.getIndex());
//            }
//            else
//                s.pop();
//        }
//
//        if(s.isEmpty())
//            System.out.println("Nema reshenie");
//        else{
//            Stack<Integer> os = new Stack<Integer>();
//            while(!s.isEmpty())
//                os.push(s.pop());
//            while(!os.isEmpty())
//                System.out.println(hash.get(os.pop()));
//        }
//
//    }
//
//    @Override
//    public String toString() {
//        return graph.toString();
//    }
//
//    public static void main(String[] args) {
//
//        Scanner scan = new Scanner(System.in);
//        Lavirint lavirint = new Lavirint();
//        String line = scan.nextLine();
//        String parts[] = line.split(",");
//
//        int rows = Integer.parseInt(parts[0]);
//        int columns = Integer.parseInt(parts[1]);
//
//        String input[] = new String[rows];
//
//        for (int i=0; i < rows; ++i) {
//            input[i] = scan.nextLine();
//        }
//
//        lavirint.createGraph(rows, columns, input);
//        lavirint.findPath();
//    }
//
//
//
//}
//
//class GraphNode {
//    private int index;//index (reden broj) na temeto vo grafot
//    private String info;
//    private LinkedList<GraphNode> neighbors;
//
//    public GraphNode(int index, String info) {
//        this.index = index;
//        this.info = info;
//        neighbors = new LinkedList<GraphNode>();
//    }
//
//    boolean containsNeighbor(GraphNode o){
//        return neighbors.contains(o);
//    }
//
//    void addNeighbor(GraphNode o){
//        neighbors.add(o);
//    }
//
//    void removeNeighbor(GraphNode o){
//        if(neighbors.contains(o))
//            neighbors.remove(o);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        @SuppressWarnings("unchecked")
//        GraphNode pom = (GraphNode)obj;
//        return (pom.info.equals(this.info));
//    }
//
//    @Override
//    public String toString() {
//        return index + " " + info;
//    }
//
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }
//
//    public String getInfo() {
//        return info;
//    }
//
//    public void setInfo(String info) {
//        this.info = info;
//    }
//
//    public LinkedList<GraphNode> getNeighbors() {
//        return neighbors;
//    }
//
//    public void setNeighbors(LinkedList<GraphNode> neighbors) {
//        this.neighbors = neighbors;
//    }
//
//}
//
//class Graph {
//
//    int num_nodes;
//    GraphNode adjList[];
//
//    @SuppressWarnings("unchecked")
//    public Graph(int num_nodes, GraphNode[] list) {
//        this.num_nodes = num_nodes;
//        adjList = new GraphNode[num_nodes];
//        for (int i=0; i < num_nodes; ++i)
//            adjList[i] = list[i];
//    }
//
//    int adjacent(int x, int y) {
//        // proveruva dali ima vrska od jazelot so
//        // indeks x do jazelot so indeks y
//        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
//    }
//
//    void addEdge(int x, int y) {
//        // dodava vrska od jazelot so indeks x do jazelot so indeks y
//        if (!adjList[x].containsNeighbor(adjList[y])) {
//            adjList[x].addNeighbor(adjList[y]);
//        }
//        if (!adjList[y].containsNeighbor(adjList[x])) {
//            adjList[y].addNeighbor(adjList[x]);
//        }
//    }
//
//    void deleteEdge(int x, int y) {
//        adjList[x].removeNeighbor(adjList[y]);
//        adjList[y].removeNeighbor(adjList[x]);
//    }
//
//    @Override
//    public String toString() {
//        String ret = new String();
//        for(int i=0;i<this.num_nodes;i++)
//            ret+=adjList[i]+"\n";
//        return ret;
//    }
//
//}