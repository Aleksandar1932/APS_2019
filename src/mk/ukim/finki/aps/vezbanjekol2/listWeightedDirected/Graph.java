//package lista_tezinski_nasocen;
package mk.ukim.finki.aps.vezbanjekol2.listWeightedDirected;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph<E> {

    int num_nodes;
    public GraphNode<E> adjList[];

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
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, null);
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

    /*************************** KRUSKAL ***********************************************************************/
    // Metoda koja generira niza od site rebra vo grafot
    public Edge[] getAllEdges() {
        int totalEdges = 0;
        for (int i = 0; i < this.num_nodes; i++) {
            // za sekoe teme go dodavame brojot na sosedi koi gi ima
            totalEdges += this.adjList[i].getNeighbors().size();
        }

        // System.out.println("METODA: Imame vk "+totalEdges+" rebra");

        Edge[] edges = new Edge[totalEdges];
        int index = 0;
        for (int i = 0; i < this.num_nodes; i++) {
            for (int j = 0; j < this.adjList[i].getNeighbors().size(); j++) {
                int index1 = this.adjList[i].getIndex();
                // se zemaat indeksot i tezinata na j-ot sosed na temeto i
                int index2 = this.adjList[i].getNeighbors().get(j).node
                        .getIndex();
                float weight = this.adjList[i].getNeighbors().get(j).weight;
                edges[index++] = new Edge(index1, index2, weight);
            }
        }

        return edges;
    }

    // Metoda koja gi sortira site rebra
    private Edge[] sortEdges(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                if (edges[i].getWeight() > edges[j].getWeight()) {
                    Edge tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }

        return edges;
    }

    // Metoda koja pravi unija na dve drva
    private int[] union(int u, int v, int[] vrski) {
        int findWhat, replaceWith;

        if (u < v) {
            findWhat = vrski[v];
            replaceWith = vrski[u];
        } else {
            findWhat = vrski[u];
            replaceWith = vrski[v];
        }

        // za dvete poddrva stava ist index
        // t.e. gi spojuva
        for (int i = 0; i < vrski.length; i++) {
            if (vrski[i] == findWhat) {
                vrski[i] = replaceWith;
            }
        }
        return vrski;
    }

    public List<Edge> kruskal() {
        /*
         * Pomoshna niza za sledenje na kreiranite drva Ako dve teminja se del
         * od isto poddrvo togash imaat ista vrednost vo nizata
         */
        int vrski[] = new int[this.num_nodes];

        // niza koja gi sodrzi site rebra
        Edge[] edges = this.getAllEdges();
        // se sortiraat rebrata spored tezinite vo neopagjacki redosled
        edges = this.sortEdges(edges);

        // inicijalizacija na pomosnata niza za evidencija,
        // sekoj jazel si e posebno drvo
        for (int i = 0; i < this.num_nodes; i++)
            vrski[i] = i;

        // lista koja kje gi sodrzi MST rebra
        List<Edge> mstEdges = new ArrayList<Edge>();

        for (int i = 0; i < edges.length; i++) {
            // za sekoe rebro vo sortiran redosled
            Edge e = edges[i];

            if (vrski[e.getFrom()] != vrski[e.getTo()]) {
                // ako teminjata na ova rebro ne se vo isto poddrvo
                // ova rebro e MST rebro
                mstEdges.add(e);
                // sega dvete teminja treba da se vo isto poddrvo
                // t.e se pravi merge (unija) t.s. vo nizata vrski
                // za site elementi od dvete poddrva
                // go setira istiot (najmal) element od dvete poddrva
                vrski = this.union(e.getFrom(), e.getTo(), vrski);
            }

            // ako sme dodale num_nodes-1 rebra moze da prestaneme
            if (mstEdges.size() == (this.num_nodes - 1))
                break;
        }

        return mstEdges;
    }

    /*******************************************************************************************************/

    /************************ PRIM **************************************************************************/

    // Metoda koja go naogja najmaloto rebro do
    // teme na neposeten sosed
    private Edge getMinimalEdge(boolean[] included) {
        int index1 = Integer.MAX_VALUE, index2 = Integer.MAX_VALUE;
        float minweight = Float.MAX_VALUE;

        for (int i = 0; i < this.num_nodes; i++) {
            if (included[i]) {
                // ako e vkluceno temeto i
                // izmini gi negovite nevkluceni sosedi
                Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors()
                        .iterator();
                while (it.hasNext()) {
                    GraphNodeNeighbor<E> pom = it.next();
                    // ako sosedot ne e poseten i ima do sega najmala tezina
                    if (!included[pom.node.getIndex()]
                            && pom.weight < minweight) {
                        index1 = i;
                        index2 = pom.node.getIndex();
                        minweight = pom.weight;
                    }
                }
            }
        }

        if (minweight < Float.MAX_VALUE) {
            Edge ret = new Edge(index1, index2, minweight);
            return ret;
        }
        return null;
    }

    public List<Edge> prim(int start_index) {
        // lista koja kje gi sodrzi MST rebra
        List<Edge> mstEdges = new ArrayList<Edge>();

        boolean included[] = new boolean[this.num_nodes];
        for (int i = 0; i < this.num_nodes; i++)
            included[i] = false;

        included[start_index] = true;

        for (int i = 0; i < this.num_nodes - 1; i++) {
            Edge e = this.getMinimalEdge(included);
            if (e == null) {
                System.out.println("Ne mozat da se povrzat site jazli");
                break;
            }
            included[e.getFrom()] = included[e.getTo()] = true;
            mstEdges.add(e);
        }

        return mstEdges;
    }

    /*******************************************************************************************************/
    /***************** DIJKSTRA ******************************************************************************/

    public float[] dijkstra(int from) {

        /* Minimalna cena do sekoj od teminjata */
        float distance[] = new float[this.num_nodes];
        /* dali za temeto e najdena konecnata (minimalna) cena */
        boolean finalno[] = new boolean[this.num_nodes];
        for (int i = 0; i < this.num_nodes; i++) {
            distance[i] = -1;
            finalno[i] = false;
        }

        finalno[from] = true;
        distance[from] = 0;

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
                        distance[pom.node.getIndex()] = distance[from]
                                + pom.weight;
                    }
                    /* inaku, ako e pronajdena poniska */
                    else if (distance[from] + pom.weight < distance[pom.node
                            .getIndex()]) {
                        distance[pom.node.getIndex()] = distance[from]
                                + pom.weight;
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

        return distance;

    }

    /*******************************************************************************************************/

    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += adjList[i] + "\n";
        return ret;
    }

}
