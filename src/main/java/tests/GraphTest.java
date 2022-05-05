package tests;

import edu.princeton.cs.algs4.Graph;
import student.BetterGraph;

public class GraphTest {

	public static void main(String[] args) {
		
		Graph G = new Graph(4);
		BetterGraph B = new BetterGraph(4);
		
		System.out.printf("%d\n", G.V());
		System.out.printf("%d\n", G.E());
		System.out.printf("%s\n", G.toString());
		
		G.addEdge(0, 1);
		G.addEdge(0, 3);
		G.addEdge(1, 2);
		G.addEdge(1, 3);
		System.out.printf("%s\n", G.toString());
		
		System.out.printf("%d\n", B.V());
		System.out.printf("%d\n", B.E());
		System.out.printf("%s\n", B.toString());
		
		B.addEdge(0, 1);
		B.addEdge(0, 3);
		B.addEdge(1, 2);
		B.addEdge(1, 3);
		B.addEdge(3, 1);
		System.out.printf("%s\n", B.toString());

	}

}
