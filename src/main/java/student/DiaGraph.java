package student;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.In;


//**
//	Class for directed Graphs that generates a random directed graph with given parameters
//
//	Example usage from bin directory:
//	java -cp . student.DiaGraph 6 30
//	*//

public class DiaGraph {
	
    private final int V;           // number of vertices in this digraph
    private int E;                 // number of edges in this digraph
    private ArrayList<ArrayList<Integer>> adj;    // adj[v] = adjacency list for vertex v
    private int[] indegree;        // indegree[v] = indegree of vertex v
    
    public DiaGraph(int V) {
    	if (V <= 0)
    		throw new IllegalArgumentException("Negative sizes are not allowed");
    	this.V = V;
    	this.E = 0;
    	indegree = new int[V];
    	this.adj = new ArrayList<ArrayList<Integer>>(V);
    	for(int i=0; i<V; i++)
    		adj.add(i, new ArrayList<Integer>());
    }
    
    public DiaGraph(In in) {
    	if (in == null)
    		throw new IllegalArgumentException("Input stream is empty");
    	try {
    		this.V = in.readInt();
    		if (V <= 0)
        		throw new IllegalArgumentException("Negative sizes are not allowed");
        	indegree = new int[V];
        	this.adj = new ArrayList<ArrayList<Integer>>(V);
        	for(int i=0; i<V; i++)
        		adj.add(i, new ArrayList<Integer>());
        	int E = in.readInt();
        	if (E < 0)
        		throw new IllegalArgumentException("Negative sizes are not allowed");
        	if (E > V*(V-1))
        		throw new IllegalArgumentException("Not enough vertices");
        	for (int i=0; i < E; i++) {
        		int v = in.readInt();
        		int w = in.readInt();
        		addEdge(v, w);
        	}
    	} catch (NoSuchElementException e) {
    		throw new IllegalArgumentException("Stream incorrectly formatted");
    	}
    }
    
    public DiaGraph(int V, int E) {
    	this(V);
    	if (E < 0)
    		throw new IllegalArgumentException("Negative sizes are not allowed");
    	if (E > V*(V-1))
    		throw new IllegalArgumentException("Not enough vertices");
    	int from, to;
    	while(this.E < E) {
    		from = (int) (Math.random() * (V));
    		to = (int) (Math.random() * (V));
    		addEdge(from, to);
    		adj.get(from).sort(Comparator.naturalOrder());
    	}
    }
    
    public int V() { return this.V; }
    
    public int E() { return this.E; }
    
    public ArrayList<Integer> adj(int v) {
    	validateVertex(v);
    	return adj.get(v);
    }
    
    public void addEdge(int from, int to) {
    	validateVertex(from);
    	validateVertex(to);
    	if(from == to || adj.get(from).contains(to))
    		return;
    	adj.get(from).add(to);
    	indegree[to]++;
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
    
    public boolean validatePerm(int[] perm) {
    	if (perm.length != (this.V))
    		throw new IllegalArgumentException("Permutation needs " + (this.V) + " elements but has " + perm.length);
    	DiaCycle find = new DiaCycle(this);
    	boolean check = false;
    	if (!find.hasCycle()) {
    		for (int i=0; i < perm.length-1; i++) {
    			check = false;
    			if(adj.get(perm[i]).contains(perm[i+1]) || adj.get(perm[i]).size() == 0)
    				continue;
    			for (int j = i; j < perm.length; j++) {
    				check = true;
    				if(adj.get(perm[i]).contains(perm[j])) {
    					check = false;
    					break;
    				}
    			}
    			if(check)
    				return false;
    		}
    	}
    	return true;
    }
    
    public static void main(String args[]) {
    	DiaGraph D;
    	if (args.length != 0)
    		D = new DiaGraph(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    	else
    		D = new DiaGraph(6, 30);
    	System.out.print(D.toString());
    }

}
