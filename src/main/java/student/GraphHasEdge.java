package student;

import edu.princeton.cs.algs4.*;

public class GraphHasEdge extends Graph{
	
	public GraphHasEdge(int V) {
		super(V);
	}
	
	// throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= super.V())
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (super.V()-1));
    }
	
	boolean hasEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		Iterable<Integer> search = super.adj(v);
		for(Integer i : search) if (i == w) return true;
		return false;
	}
	
	public static void main(String[] args) {
		GraphHasEdge G = new GraphHasEdge(4);
		
		G.addEdge(0, 1);
		G.addEdge(0, 3);
		G.addEdge(1, 2);
		G.addEdge(1, 3);
		System.out.println(G.hasEdge(0, 2));
		System.out.println(G.hasEdge(0, 1));
		System.out.println(G.hasEdge(2, 1));
	}

}
