package student;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.*;

public class WeGraph {
	
	private final int V;           // number of vertices in this wegraph
    private int E;                 // number of edges in this digraph
    private ArrayList<ArrayList<Edge>> adj;    // adj[v] = adjacency list for vertex v
    
    public WeGraph(int V) {
    	if (V <= 0)
    		throw new IllegalArgumentException("Negative sizes are not allowed");
    	this.V = V;
    	this.E = 0;
    	this.adj = new ArrayList<ArrayList<Edge>>(V);
    	for(int i=0; i<V; i++)
    		adj.add(i, new ArrayList<Edge>());
    }
    
    public WeGraph(In in) {
    	if (in == null)
    		throw new IllegalArgumentException("Input stream is empty");
    	try {
    		this.V = in.readInt();
    		if (V <= 0)
        		throw new IllegalArgumentException("Negative sizes are not allowed");
        	this.adj = new ArrayList<ArrayList<Edge>>(V);
        	for(int i=0; i<V; i++)
        		adj.add(i, new ArrayList<Edge>());
        	int E = in.readInt();
        	if (E < 0)
        		throw new IllegalArgumentException("Negative sizes are not allowed");
        	if (E > V*(V-1))
        		throw new IllegalArgumentException("Not enough vertices");
        	for (int i=0; i < E; i++) {
        		int v = in.readInt();
        		int w = in.readInt();
        		double we = in.readDouble();
        		addEdge(new Edge(v, w, we));
        	}
    	} catch (NoSuchElementException e) {
    		throw new IllegalArgumentException("Stream incorrectly formatted");
    	}
    }
    
    public WeGraph(int V, int E) {
    	this(V, E, "uniform");
    }
    
    public WeGraph(int V, int E, String type) {
    	this(V);
    	if (E < 0)
    		throw new IllegalArgumentException("Negative sizes are not allowed");
    	if (E > V*(V-1))
    		throw new IllegalArgumentException("Not enough vertices");
    	int from, to;
    	double weight;
    	if (type.compareTo("uniform") == 0) {
	    	while(this.E < E) {
	    		from = (int) (Math.random() * (V));
	    		to = (int) (Math.random() * (V));
	    		weight = StdRandom.uniform();
	    		addEdge(new Edge(from, to, weight));
	    	}
    	} else if(type.compareTo("gaussian") == 0) {
    		while(this.E < E) {
	    		from = (int) (Math.random() * (V));
	    		to = (int) (Math.random() * (V));
	    		weight = StdRandom.gaussian();
	    		addEdge(new Edge(from, to, weight));
	    	}
    	} else
    		throw new IllegalArgumentException("Distribution not available");
    }
    
    public int V() { return this.V; }
    
    public int E() { return this.E; }
    
    public ArrayList<Edge> adj(int v) {
    	validateVertex(v);
    	return adj.get(v);
    }
    
    public void addEdge(Edge edge) {
    	int from = edge.either();
    	int to = edge.other(from);
    	validateVertex(from);
    	validateVertex(to);
    	if(from == to)
    		return;
    	for (Edge edg : adj.get(from)) {
    		if (edge.isEqualNoWeight(edg))
    			return;
    	}
    	adj.get(from).add(edge);
    	adj.get(to).add(edge);
    	E++;
    }
    
    private void validateVertex(int v) { // throws IllegalArgumentException {
    	if(v < 0 || v >= this.V)
    		throw new IllegalArgumentException("vertex " + v + " is not in <" + 0 +", " + (V-1) + ">");
    }
    
    public String toString() {
    	String ret = String.format("Vertex: %d\nEdges: %d\nAdjacency List:\n", this.V, this.E);
    	for (int i=0; i < V; i++) {
    		ret += (i + ": ");
    		for (int j=0; j < adj.get(i).size(); j++)
    			ret += (adj.get(i).get(j) + " ");
    		ret += "\n";
    	}	
    	return ret;
    }
    
    public String toFileFormat() {
    	ArrayList<Edge> list = new ArrayList<Edge>();
    	String ret = String.format("%d\n%d\n", this.V, this.E);
    	for (int i=0; i < V; i++) {
    		for (Edge edg : adj.get(i))
    			if (list.contains(edg))
    				continue;
    			else {
    				ret += (edg.toString() + "\n");
    				list.add(edg);
    			}
    	}
    	return ret;
    }
    
    public static void main(String args[]) {
    	WeGraph D;
    	if (args.length != 0)
    		D = new WeGraph(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    	else
    		D = new WeGraph(6, 30);
    	System.out.print(D.toFileFormat());
    }
	
}
