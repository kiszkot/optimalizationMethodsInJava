/**
 * Graph that does not have double edges or self edges
 */

package student;

import edu.princeton.cs.algs4.*;

public class BetterGraph extends Graph{
	
	public BetterGraph(int V) { super(V); }
	
	public BetterGraph(In in) { super(in); }
	
	/**
	 * Function to check if the given vertices have an edge
	 * between them
	 * @param v - first vertex
	 * @param w - second vertex
	 * @return boolean value
	 */
	boolean hasEdge(int v, int w) {
		for(Integer i : super.adj(v)) if (i == w) return true;
		return false;
	}
	
	@Override
	public void addEdge(int v, int w) {
		if (v == w || hasEdge(v, w)) {
			return;
		} else {
			super.addEdge(v, w);
		}
	}
	
	/**
	 * Function to find the highest degree vertex
	 * @return
	 */
	public int highestDegree() {
		int m = 0;
		for (int i=0; i < super.V(); i++) {
			if ( super.degree(i) > super.degree(m) ) m = i;
		}
		return m;
	}
	
	public static void main(String args[]) {
		String path = "tinyG.txt";
		In in = new In(path);
		BetterGraph G = new BetterGraph(in);
		in = new In(path);
		Graph B = new Graph(in);
		
		System.out.println(G.toString());
		System.out.println(B.toString());
		
	}
	
}
