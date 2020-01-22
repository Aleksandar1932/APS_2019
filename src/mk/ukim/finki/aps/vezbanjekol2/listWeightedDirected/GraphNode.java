package mk.ukim.finki.aps.vezbanjekol2.listWeightedDirected;//package lista_tezinski_nasocen;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphNode<E> {
	private int index; //index (reden broj) na temeto vo grafot
	private E info;
	private LinkedList<GraphNodeNeighbor<E>> neighbors;
	
	public GraphNode(int index, E info) {
		this.index = index;
		this.info = info;
		neighbors = new LinkedList<GraphNodeNeighbor<E>>();
	}
	
	public boolean containsNeighbor(GraphNode<E> o){
		GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,0);
		return neighbors.contains(pom);
	}
	
	public void addNeighbor(GraphNode<E> o, float weight){
		GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,weight);
		neighbors.add(pom);
	}
	
	public void removeNeighbor(GraphNode<E> o){
		GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,0);
		if(neighbors.contains(pom))
			neighbors.remove(pom);
	}
	
	@Override
	public String toString() {
		String ret= "INFO:"+info+" SOSEDI:";
		for(int i=0;i<neighbors.size();i++)
		ret+="("+neighbors.get(i).node.info+","+neighbors.get(i).weight+") ";
		return ret;
		
	}

	public void updateNeighborWeight(GraphNode<E> o, float weight){
		Iterator<GraphNodeNeighbor<E>> i = neighbors.iterator();
		while(i.hasNext()){
			GraphNodeNeighbor<E> pom = i.next();
			if(pom.node.equals(o))
				pom.weight = weight;
		}
			
	}

	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		GraphNode<E> pom = (GraphNode<E>)obj;
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

	public LinkedList<GraphNodeNeighbor<E>> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(LinkedList<GraphNodeNeighbor<E>> neighbors) {
		this.neighbors = neighbors;
	}
	
	
	
}
