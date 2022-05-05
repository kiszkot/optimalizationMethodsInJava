/**
 * GraphProperties calculates some basic properties of a Graph
 * like its centre, its radius and its diameter. These values are calculated in the constructor
 * to not waste time when accessing the variable multiple times.
 * 
 * Usage
 * java GraphProperties test.txt
 */

package student;

import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.time.Instant;
import java.time.Duration;

public class GraphProperties {
	
	/**
	 * Graph
	 */
	private Graph G;
	
	/**
	 * Graph diameter
	 */
	private int rad;
	
	/**
	 * Graph radius
	 */
	private int diam;
	
	/**
	 * Graph center
	 */
	private int cen;
	
	/**
	 * Graph girth
	 */
	private float Girth;
	
	/**
	 * Constructor for GraphProperties
	 * @param G - Graph
	 * @throws IllegalArgumentException when 
	 */
	public GraphProperties(Graph G) throws IllegalArgumentException {
		DepthFirstPaths path = new DepthFirstPaths(G, 0);
		for(int i = 0; i < G.V(); i++) {
			path = new DepthFirstPaths(G, i);
			for (int j = 0; j < G.V(); j++) {
				if (!path.hasPathTo(j)) {
					throw new IllegalArgumentException("Graph is not connected");
				}
			}
		}
		this.G = G;
		this.diam = diameterC();
		this.rad = radiusC();
		this.cen = centreC();
		girthC();
	}
	
	/**
	 * Eccentricity is the length of the distance from a given vertex
	 * to the vertex further away from it. Uses BreathFirstPath.
	 * @param v - Vertex
	 * @return Eccentricity of v
	 */
	public int eccentricity(int v) {
		BreadthFirstPaths search = new BreadthFirstPaths(G, v);
		int length = 0;
		for (int i = 0; i < G.V(); i++) {
			int l = search.distTo(i);
			if (l > length) length = l;
		}
		return length;
	}
	
	/**
	 * The diameter of a graph is the highest eccentricity
	 * of all the vertices. 
	 * @return Diameter of the Graph
	 */
	public int diameter() {
		return this.diam;
	}
	
	private int diameterC() {
		int max = 0;
		for (int i = 0; i < G.V(); i++) {
			int l = eccentricity(i);
			if (l > max) max = l;
		}
		return max;
	}
	
	/**
	 * The radius of the graph is the smallest eccentricity
	 * of all the vertices. 
	 * @return Radius of the Graph
	 */
	public int radius() {
		return this.rad;
	}
	
	private int radiusC() {
		int min = eccentricity(0);
		for (int i = 0; i < G.V(); i++) {
			int l = eccentricity(i);
			if (l < min) min = l;
		}
		return min;
	}
	
	/**
	 * The centre of a graph is a vertex, whose eccentricity
	 * is equal to the radius. When more than one satisfy this condition,
	 * the first one is returned.
	 * @return The centre of the graph
	 */
	public int centre() { return this.cen; }
	
	private int centreC() {
		for (int i = 0; i < G.V(); i++) {
			int e = eccentricity(i);
			if (e == rad) return i;
		}
		return -1;
	}
	
	private void girthC() {
		BreadthFirstPaths search;
		float length = Float.POSITIVE_INFINITY;
		for (int i = 0; i < G.V(); i++) {
			search = new BreadthFirstPaths(G, i);
			Iterable<Integer> adj = G.adj(i);
			float l = Float.POSITIVE_INFINITY;
			for (int j = 0; j < G.V(); j++) {
				if (j == i) continue; // skip if vertex is same
				BreadthFirstPaths back = new BreadthFirstPaths(G, j);
				Iterator<Integer> path = back.pathTo(i).iterator();
				path.next(); if (!path.hasNext()) continue; // skip if path id of length 1
				int prev = path.next();
				for (int e : adj) {
					path = back.pathTo(e).iterator();
					path.next(); if (!path.hasNext()) continue;
					if (path.next() != prev) {
						l = search.distTo(j) + back.distTo(e) + 1;
						if (l < length) length = l;
						// minimal cycle length is 3, there is no need to search anymore
						if (l == 3) {
							this.Girth = length;
							return;
						}
						continue;
					}
				}
			}
		}
		this.Girth = length;
	}
	
	/**
	 * Calculates the girth of a graph.
	 * Girth is the length of the smallest cycle in a graph.
	 * If the graph has no cycle, then it's infinity.
	 * @return The girth of the graph
	 */
	public float girth() { return this.Girth; }
	
	public static void main(String args[]) {
		Graph g = new Graph(new In("test2.txt"));
		Instant start = Instant.now();
		GraphProperties P = new GraphProperties(g);
		Instant stop = Instant.now();
		System.out.printf("Elapsed time: %s\n", Duration.between(start, stop).toString());
		for (int i = 0; i < g.V(); i++) {
			System.out.printf("Eccentricity of %d: %d\n", i, P.eccentricity(i));
		}
		System.out.printf("Diameter: %d\n", P.diameter());
		System.out.printf("Radius: %d\n", P.radius());
		System.out.printf("Center: %d\n", P.centre());
		System.out.printf("Girth: %f\n", P.girth());
		
		/*
		BetterGraph R = new BetterGraph(50);
		int v;
		int w;
		while (R.E() < 20) {
			v = (int) (Math.random() * 20);
			w = (int) (Math.random() * 20);
			R.addEdge(v,  w);
		}
		
		start = Instant.now();
		P = new GraphProperties(R);
		stop = Instant.now();
		System.out.printf("Elapsed time: %s\n", Duration.between(start, stop).toString());
		*/
		
		/*
		BreadthFirstPaths search = new BreadthFirstPaths(g, 0);
		BreadthFirstPaths search1 = new BreadthFirstPaths(g, 3);
		Iterator<Integer> path = search.pathTo(3).iterator();
		while(path.hasNext()) System.out.print(path.next() + " ");
		System.out.print("\n");
		System.out.println(search.pathTo(3));
		System.out.println(search.pathTo(1));
		System.out.println(search.pathTo(2));
		System.out.println(search1.pathTo(0));
		*/
	}

}
