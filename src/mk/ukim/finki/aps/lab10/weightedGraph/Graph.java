package mk.ukim.finki.aps.lab10.weightedGraph;

import java.util.*;

public class Graph<E> {

    int num_nodes;
    GraphNode<E>[] adjList;

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode< >(i, list[i]);
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

    public void addEdge(int x, int y, float tezina) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y so tezina
        if (adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].updateNeighborWeight(adjList[y], tezina);
        } else
            adjList[x].addNeighbor(adjList[y], tezina);
    }

    public void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    public GraphNode<E> getNode(int ID){
    	return adjList[ID];
	}

    public void dfsSearch(int node) {
        boolean[] visited = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        dfsRecursive(node, visited);
    }

    public void dfsRecursive(int node, boolean[] visited) {
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());

        for (int i = 0; i < adjList[node].getNeighbors().size(); i++) {
            GraphNode<E> pom = adjList[node].getNeighbors().get(i).node;
            if (!visited[pom.getIndex()])
                dfsRecursive(pom.getIndex(), visited);
        }
    }

    public void dfsNonrecursive(int node) {
        boolean[] visited = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());
        Stack<Integer> s = new Stack<>();
        s.push(node);

        GraphNode<E> pom;

        while (!s.isEmpty()) {
            pom = adjList[s.peek()];
            GraphNode<E> tmp = null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i).node;
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

    private Edge getMinimalEdge(boolean[] included){
        int index1 = Integer.MAX_VALUE, index2 = Integer.MAX_VALUE;
        float minweight = Float.MAX_VALUE;

        for(int i=0;i<this.num_nodes;i++){
            if(included[i]){
                //ako e vkluceno temeto i
                //izmini gi negovite nevkluceni sosedi
                Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors().iterator();
                while(it.hasNext()){
                    GraphNodeNeighbor<E> pom = it.next();
                    //ako sosedot ne e poseten i ima do sega najmala tezina
                    if(!included[pom.node.getIndex()]&&pom.weight<minweight){
                        index1 = i;
                        index2 = pom.node.getIndex();
                        minweight = pom.weight;
                    }
                }
            }
        }

        if(minweight<Float.MAX_VALUE){
            Edge ret = new Edge(index1, index2, minweight);
            return ret;
        }
        return null;
    }


    public List<Edge> prim(int start_index) {
        // lista koja kje gi sodrzi MST rebra
        List<Edge> mstEdges = new ArrayList<Edge>();

        boolean included[] = new boolean[this.num_nodes];
        for(int i=0;i<this.num_nodes;i++)
            included[i]=false;

        included[start_index] = true;

        for(int i=0;i<this.num_nodes-1;i++){
            Edge e = this.getMinimalEdge(included);
            if(e==null){
                System.out.println("Ne mozat da se povrzat site jazli");
                break;
            }
            included[e.getFrom()] = included[e.getTo()] = true;
            mstEdges.add(e);
        }

        return mstEdges;
    }

    public void bfs(int node) {
        boolean[] visited = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());
        Queue<Integer> q = new LinkedQueue<>();
        q.enqueue(node);

        GraphNode<E> pom;

        while (!q.isEmpty()) {
            pom = adjList[q.dequeue()];
            GraphNode<E> tmp = null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i).node;
                if (!visited[tmp.getIndex()]) {
                    visited[tmp.getIndex()] = true;
                    System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
                    q.enqueue(tmp.getIndex());
                }
            }


        }

    }

    /***************** DIJKSTRA
     * @return******************************************************************************/
   public int[] dijkstraINT(int from, int to) {

        /* Minimalna cena do sekoj od teminjata */
        int odamOd[]=new int[num_nodes];
        float distance[] = new float[this.num_nodes];
        /* dali za temeto e najdena konecnata (minimalna) cena */
        boolean finalno[] = new boolean[this.num_nodes];
        for (int i = 0; i < this.num_nodes; i++) {
            odamOd[i]=-1;
            distance[i] = -1;
            finalno[i] = false;
        }

        finalno[from] = true;
        distance[from] = 0;
        odamOd[from]=-2;

        /*
         * vo sekoj cekor za edno teme se dobiva konecna minimalna cena
         */
        for (int i = 1; i < this.num_nodes; i++) {
            /* za site sledbenici na from presmetaj ja cenata */
            Iterator<GraphNodeNeighbor<E>> it = adjList[from].getNeighbors()
                    .iterator();
            while (it.hasNext()) {
                GraphNodeNeighbor<E> pom = it.next();
                /* ako grankata kon sosedot nema konecna cena */
                if (!finalno[pom.node.getIndex()]) {
                    /* ako ne e presmetana cena za temeto */
                    if (distance[pom.node.getIndex()] <= 0) {
                        distance[pom.node.getIndex()] = distance[from]+ pom.weight;
                        odamOd[pom.node.getIndex()]=from;
                    }
                    /* inaku, ako e pronajdena poniska */
                    else if (distance[from] + pom.weight < distance[pom.node.getIndex()]) {
                        distance[pom.node.getIndex()] = distance[from]+ pom.weight;
                        odamOd[pom.node.getIndex()] =from;
                    }
                }
            }

            /* najdi teme so min. cena koja ne e konecna */
            boolean minit = false; /* min. ne e inicijaliziran */
            int k = -1;
            float minc = -1;
            /* proveri gi site teminja */
            for (int j = 0; j < this.num_nodes; j++) {
                if (!finalno[j] && distance[j] != -1) { /*ako cenata ne e  konecna*/
                    if (!minit) { /* ako ne e inicijaliziran minimumot */
                        minc = distance[k = j]; /* proglasi go ova e minimum */
                        minit = true; /* oznaci deka min e inicijaliziran */
                    }
                    /* proveri dali e pronajdeno teme so pomala cena */
                    else if (minc > distance[j] && distance[j] > 0)
                        minc = distance[k = j];
                }
            }
            finalno[from = k] = true;
        }
        int nizaNizKoja[]=new int[500];
        for(int i=0;i<500;i++)
            nizaNizKoja[i]=-1;
        //int nizaNizKoja[]=new int[this.num_nodes];
        int b=0;
        int a=to;
        while(a!=-2)
        {
            //System.out
            nizaNizKoja[b++]=a;
            a=odamOd[a];
        }
        int res[]=new int[b];
        for (int i=0;i<b;i++)
            res[i]=nizaNizKoja[i];
        return res;

    }
    public float[] dijkstra(int from, int to) {

        /* Minimalna cena do sekoj od teminjata */
        int odamOd[]=new int[num_nodes];
        float distance[] = new float[this.num_nodes];
        /* dali za temeto e najdena konecnata (minimalna) cena */
        boolean finalno[] = new boolean[this.num_nodes];
        for (int i = 0; i < this.num_nodes; i++) {
            odamOd[i]=-1;
            distance[i] = -1;
            finalno[i] = false;
        }

        finalno[from] = true;
        distance[from] = 0;
        odamOd[from]=-2;

        /*
         * vo sekoj cekor za edno teme se dobiva konecna minimalna cena
         */
        for (int i = 1; i < this.num_nodes; i++) {
            /* za site sledbenici na from presmetaj ja cenata */
            Iterator<GraphNodeNeighbor<E>> it = adjList[from].getNeighbors()
                    .iterator();
            while (it.hasNext()) {
                GraphNodeNeighbor<E> pom = it.next();
                /* ako grankata kon sosedot nema konecna cena */
                if (!finalno[pom.node.getIndex()]) {
                    /* ako ne e presmetana cena za temeto */
                    if (distance[pom.node.getIndex()] <= 0) {
                        distance[pom.node.getIndex()] = distance[from]+ pom.weight;
                        odamOd[pom.node.getIndex()]=from;
                    }
                    /* inaku, ako e pronajdena poniska */
                    else if (distance[from] + pom.weight < distance[pom.node.getIndex()]) {
                        distance[pom.node.getIndex()] = distance[from]+ pom.weight;
                        odamOd[pom.node.getIndex()] =from;
                    }
                }
            }

            /* najdi teme so min. cena koja ne e konecna */
            boolean minit = false; /* min. ne e inicijaliziran */
            int k = -1;
            float minc = -1;
            /* proveri gi site teminja */
            for (int j = 0; j < this.num_nodes; j++) {
                if (!finalno[j] && distance[j] != -1) { /*ako cenata ne e  konecna*/
                    if (!minit) { /* ako ne e inicijaliziran minimumot */
                        minc = distance[k = j]; /* proglasi go ova e minimum */
                        minit = true; /* oznaci deka min e inicijaliziran */
                    }
                    /* proveri dali e pronajdeno teme so pomala cena */
                    else if (minc > distance[j] && distance[j] > 0)
                        minc = distance[k = j];
                }
            }
            finalno[from = k] = true;
        }
        int nizaNizKoja[]=new int[this.num_nodes];
        int b=0;
        int a=to;
        ArrayList<Float> nodesList = new ArrayList<>();
        while(a!=-2)
        {
            nodesList.add((float) a);
            //System.out
            nizaNizKoja[b++]=a;
            a=odamOd[a];
        }

//        Collections.reverse(nodesList);
//
//        float calculatedDistance = calculateDistance(distance);
//
//        nodesList.add(calculatedDistance);
//
//        return nodesList;

        return distance;
    }

    private static float calculateDistance(float[] distance){
       int n = distance.length -1;
       for(int i = n; i>=0;i--){
           if (distance[i]!=0){
               return distance[i];
           }
       }
       return -1;


    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < this.num_nodes; i++)
            ret.append(adjList[i]).append("\n");
        return ret.toString();
    }
}
