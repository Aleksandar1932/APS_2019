package mk.ukim.finki.aps.lab10.weightedGraph;

public class GraphTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Character niza[] = new Character[5];
		niza[0]='A';
		niza[1]='B';
		niza[2]='C';
		niza[3]='D';
		niza[4]='E';
		Graph<Character> g = new Graph<Character>(5,niza);
		g.addEdge(0, 1, 5); //dodavam rebro od A do B tezina 5
		g.addEdge(1, 2, 7); //dodavam rebro od B do C tezina 7
		System.out.print(g);
		System.out.println("Dali se sosedi 0 i 1: "+g.adjacent(0, 1));
		System.out.println("Dali se sosedi 0 i 2: "+g.adjacent(0, 2));
		g.addEdge(0, 1, 6); //dodavam rebro od A do B tezina 6
		g.deleteEdge(1, 2); //brisham rebro od B do C
		System.out.print(g);
		g.addEdge(1, 2, 8); //dodavam rebro od B do C tezina 7
		g.addEdge(1, 3, 1); //dodavam rebro od B do D tezina 1
		g.addEdge(2, 4, 3); //dodavam rebro od C do E tezina 3
		
		System.out.println("Depth First Search Recursive:");
		g.dfsSearch(0);
		System.out.println("Depth First Search Nonrecursive:");
		g.dfsNonrecursive(0);
		System.out.println("Breath First Search:");
		g.bfs(0);
		
	}

}
