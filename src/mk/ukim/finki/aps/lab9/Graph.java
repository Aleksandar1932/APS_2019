package mk.ukim.finki.aps.lab9;

//import java.util.Iterator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Graph<E> {

    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
    }

    public int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    public GraphNode<E> getNodeByID(int id) {
        return adjList[id];
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void addEdge(int x, int y) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y
        if (!adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].addNeighbor(adjList[y]);
        }
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    void dfsSearch(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        dfsRecursive(node, visited);
    }

    void dfsRecursive(int node, boolean visited[]) {
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());

        for (int i = 0; i < adjList[node].getNeighbors().size(); i++) {
            GraphNode<E> pom = adjList[node].getNeighbors().get(i);
            if (!visited[pom.getIndex()])
                dfsRecursive(pom.getIndex(), visited);
        }
    }

    void dfsNonrecursive(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());
        Stack<Integer> s = new Stack<Integer>();
        s.push(node);

        GraphNode<E> pom;

        while (!s.isEmpty()) {
            pom = adjList[s.peek()];
            GraphNode<E> tmp = null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i);
                if (!visited[tmp.getIndex()])
                    break;
            }
            if (tmp != null && !visited[tmp.getIndex()]) {
                visited[tmp.getIndex()] = true;
                System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
                s.push(tmp.getIndex());
            } else
                s.pop();
        }

    }

    void bfs(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());
        Queue<Integer> q = new LinkedQueue<Integer>();
        q.enqueue(node);

        GraphNode<E> pom;

        while (!q.isEmpty()) {
            pom = adjList[q.dequeue()];
            GraphNode<E> tmp = null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i);
                if (!visited[tmp.getIndex()]) {
                    visited[tmp.getIndex()] = true;
                    System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
                    q.enqueue(tmp.getIndex());
                }
            }


        }

    }


    public int[] shortestReach(int startId) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(startId);
        int[] distances = new int[num_nodes];
        Arrays.fill(distances, -1); //Inicijalno ja polnam nizataa so -1;
        distances[startId] = 0; //Rastojanieto od mene do mene e 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adjList[node].getNeighborsArray()) {
                if (distances[neighbor] == -1) {
                    distances[neighbor] = distances[node] + 1;
                    queue.add(neighbor);
                }
            }
        }
        return distances;
    }


    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += i + ": " + adjList[i] + "\n";
        return ret;
    }


}
