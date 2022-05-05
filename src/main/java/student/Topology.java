package student;

import edu.princeton.cs.algs4.In;

public class Topology {
	
	private Iterable<Integer> order;  // Topology order
    private int[] rank;               // rank[v] = rank of vertex v in order
    
    public Topology(DiaGraph G) {
        DiaCycle finder = new DiaCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
            rank = new int[G.V()];
            int i = 0;
            for (int v : order)
                rank[v] = i++;
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean hasOrder() {
        return order != null;
    }

    public int rank(int v) {
        validateVertex(v);
        if (hasOrder()) return rank[v];
        else            return -1;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = rank.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args) {
        DiaGraph G = new DiaGraph(new In(args[0]));
        Topology Topology = new Topology(G);
        for (int v : Topology.order()) {
            System.out.println(v);
        }
    }

}
