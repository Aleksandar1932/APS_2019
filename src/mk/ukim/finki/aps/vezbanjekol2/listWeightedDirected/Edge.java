//package lista_tezinski_nasocen;
package mk.ukim.finki.aps.vezbanjekol2.listWeightedDirected;
public class Edge{
	private int fromVertex, toVertex;
	private float weight;
	public Edge(int from, int to, float weight){
		this.fromVertex = from;
		this.toVertex = to;
		this.weight = weight;
	}
	
	public int getFrom(){
		return this.fromVertex;
	}
	public int getTo(){
		return this.toVertex;
	}
	public float getWeight(){
		return this.weight;
	}
}
