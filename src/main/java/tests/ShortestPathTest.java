package tests;

import edu.princeton.cs.algs4.*;

public class ShortestPathTest {

	public static void main(String[] args) {
		
		In in = new In(args[0]);
		int from = Integer.parseInt(args[1]);
		int to = Integer.parseInt(args[2]);
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
		DijkstraSP path = new DijkstraSP(G, from);
		
		if (!path.hasPathTo(to))
			System.out.println("No path");
		else
			System.out.println(path.pathTo(to) + "\nDistance: " + path.distTo(to));

	}

}
