package student;

import edu.princeton.cs.algs4.*;

/*
 * Uwaga, aby program zadziałał należy imię pliku podać jako argument.
 * W Eclipse jest to pod Properties -> Run / Debug Settings
 * W edytowaniu programu który chcemy uruchomić pod zakładką "Arguments"
 */

public class BetterGraphMain {

	public static void main(String[] args) {
		
		// Tutaj utowrzono normalny graph, który pozwala
		// na krawędzie równoległe. Jest on wyświetlany
		In in = new In(args[0]);
		BetterGraph G = new BetterGraph(in);	
		System.out.println(G.toString());
		
		/*
		 * Tutaj utworzono graph który nie pozwala
		 * na krawędzie równoległe. Także jest wyświetlany
		 * w celach porównania
		 */
		in = new In(args[0]);
		Graph G1 = new Graph(in);
		System.out.println(G1.toString());
		
		// Random graph generator
		// 20 vertex, 100 edges
		BetterGraph R = new BetterGraph(20);
		int v;
		int w;
		while (R.E() < 100) {
			v = (int) (Math.random() * 20);
			w = (int) (Math.random() * 20);
			R.addEdge(v,  w);
		}
		
		System.out.println(R.toString());
		
		// Prints paths from highest degree vertex
		int m = R.highestDegree();
		DepthFirstPaths paths = new DepthFirstPaths(R, m);
		for (int i = 0; i < G.V(); i++) {
			if (paths.hasPathTo(i)) System.out.println(paths.pathTo(i).toString());
        }
	}

}
