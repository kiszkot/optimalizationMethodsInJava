package student;

public class Edge {
	
	private final int from;
	private final int to;
	private final double weight;
	
	public Edge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public int either() {
		return from;
	}

	public int other(int v) {
		if (v == from)
			return to;
		else if (v == to)
			return from;
		else
			throw new IllegalArgumentException("Not an endpoint");
	}

	public double weight() {
		return weight;
	}

	@Override
	public String toString() {
		return String.format("%d %d %.3f", from, to, weight);
	}
	
	public boolean isEqual(Edge edge) {
		if (this.from == edge.from && this.to == edge.to && this.weight == edge.weight)
			return true;
		return false;
	}
	
	public boolean isEqualNoWeight(Edge edge) {
		if (this.from == edge.from && this.to == edge.to)
			return true;
		else if (this.from == edge.to && this.to == edge.from)
			return true;
		else
			return false;
	}
	
	public boolean contains(int v) {
		if (v == from || v == to)
			return true;
		return false;
	}

}
