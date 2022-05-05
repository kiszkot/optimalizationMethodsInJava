package student;

// import edu.princeton.cs.algs4.*;
import tests.*;

public class Main {
	
	private static final String[] tests = {
			"GraphTest",
			"DiaGraphTest"
	};
	
	private static void printTests() {
		for (int i=0; i < tests.length; i++)
			System.out.printf("%d : %s\n", i, tests[i]);
	}

	public static void main(String[] args) {
		
		int c;
		String[] argz = new String[1];
		if(args.length == 0) {
			System.out.printf("Please specify argument from 0 to %d\n", tests.length);
			printTests();
		} else if(Integer.parseInt(args[0]) >= tests.length) {
			System.out.println("No such test");
			printTests();
		} else {
			c = Integer.parseInt(args[0]);
			switch (c) {
			case 0:
				GraphTest.main(args);
				break;
			case 1:
				argz[0] = args[1];
				DiaGraphTest.main(argz);
			}
		}
		
		/*
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
		*/
	}

}

/*
 * Graph G = new Graph(4);
		System.out.printf("%d\n", G.V());
		System.out.printf("%d\n", G.E());
		System.out.printf("%s\n", G.toString());
		
		G.addEdge(0, 1);
		G.addEdge(0, 3);
		G.addEdge(1, 2);
		G.addEdge(1, 3);
		System.out.printf("%s\n", G.toString());
*/
