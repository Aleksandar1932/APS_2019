package mk.ukim.finki.aps.lab9;


import java.util.LinkedList;

public class GraphNode<E> {
    private int index;//index (reden broj) na temeto vo grafot
    private E info;
    private LinkedList<GraphNode<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNode<E>>();
    }

    boolean containsNeighbor(GraphNode<E> o) {
        return neighbors.contains(o);
    }

    void addNeighbor(GraphNode<E> o) {
        neighbors.add(o);
    }

    void removeNeighbor(GraphNode<E> o) {
        if (neighbors.contains(o))
            neighbors.remove(o);
    }

    @Override
    public String toString() {
        String ret = "INFO:" + info + " SOSEDI:";
        for (int i = 0; i < neighbors.size(); i++)
            ret += neighbors.get(i).info + " ";
        return ret;

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>) obj;
        return (pom.info.equals(this.info));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<GraphNode<E>> getNeighbors() {
        return neighbors;
    }

    public int[] getNeighborsArray() {
        int[] retArray = new int[neighbors.size()];
        for (int i = 0; i < neighbors.size(); i++) {
            retArray[i] = neighbors.get(i).getIndex();
        }
        return retArray;
    }

    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
        this.neighbors = neighbors;
    }


}
