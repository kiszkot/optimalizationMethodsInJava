package tests;

import student.*;
import java.time.*;
import edu.princeton.cs.algs4.In;

/**
 * Sample usage
 * 
 * java tests.DiaGraphTest tinyDAG.txt
 * 
 * @author kiszkot
 *
 */

public class DiaGraphTest {
	
	/**
	 * Takes filename as argument to run test on Topology.
	 * Suggested the use of tinyDAG.txt
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] ver = {5, 10, 20, 50, 100, 1000, 5000};
		DiaGraph D;
		Instant start, stop;
		
		for(int i : ver) {
			start = Instant.now();
			D = new DiaGraph(i, i*2);
			stop = Instant.now();
			System.out.printf("Time for %d : %s\n", i, Duration.between(start, stop).toString());
		}
		
		D = new DiaGraph(new In(args[0]));
		int[] perm = {8,7,2,3,0,6,9,10,11,12,1,5,4};
		String permS = "";
		for (int i : perm)
			permS += String.valueOf(i) + " ";
		System.out.println("Is " + permS + "topology: " + D.validatePerm(perm));
		
		perm[0] = 7;
		perm[1] = 8;
		permS = "";
		for (int i : perm)
			permS += String.valueOf(i) + " ";
		System.out.println("Is " + permS + "topology: " + D.validatePerm(perm));
		
		int[] perm1 = {4,12,10,11,9,6,1,5,0,3,2,7,8};
		permS = "";
		for (int i : perm1)
			permS += String.valueOf(i) + " ";
		System.out.println("Is " + permS + "topology: " + D.validatePerm(perm1));
		
		int[] perm2 = {8,7,2,3,0,5,1,6,9,11,10,12,4};
		permS = "";
		for (int i : perm2)
			permS += String.valueOf(i) + " ";
		System.out.println("Is " + permS + "topology: " + D.validatePerm(perm2));

	}

}
