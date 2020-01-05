package mk.ukim.finki.aps.lab10.weightedGraph;

public class GraphNodeNeighbor<E> {
	GraphNode<E> node;
	float weight;
	
	public GraphNodeNeighbor(GraphNode<E> node, float weight) {
		this.node = node;
		this.weight = weight;
	}
	
	public GraphNodeNeighbor(GraphNode<E> node) {
		this.node = node;
		this.weight = 0;
	}

	@Override
	public boolean equals(Object obj) {
		@SuppressWarnings("unchecked")
		GraphNodeNeighbor<E> pom = (GraphNodeNeighbor<E>)obj;
		return pom.node.equals(this.node);
	}
	
	
	
	

}
