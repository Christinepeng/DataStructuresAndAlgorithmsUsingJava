import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * File Name: GraphBfs.java 
 * Breadth First Search on a graph
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphBfs.java
 */

class GraphBfs{
	private String t ;
	private Graph g ;
	String start;
	//Output
	private int [] work ;
	private int [] size ;
	private int [] bfsorder;
	private int [] bfspath ;
	//You can have any number of private variables and private funcions
	
	GraphBfs(String t, Graph g, String start, int [] work, int[] size, int [] bfsorder, int [] bfspath) {
		this.t = t ;
		this.g = g ;
		this.start = start;
		this.work = work ;
		this.size = size ;
		this.bfsorder = bfsorder ;
		this.bfspath = bfspath ;

    //WRITE YOUR CODE
		int u = g.graphHasNode(start);
		bfs(u);
		dump();
	}

	private void dump() {
		System.out.println("------------ " + t + " --------------------");
		System.out.println("Num Vertices = " + g.getnumV());
		System.out.println("Num Edges    = " + g.getnumE());
		System.out.println("Work done    = " + work[0]);
		System.out.print("BFS order    = ");
		for (int i = 0; i < bfsorder.length; i++) {
			System.out.print(bfsorder[i]);
		}
		System.out.print("\nBFS path     = ");
		for (int i = 0; i < bfspath.length; i++) {
			if (i == bfspath.length - 1) {
				System.out.println(bfspath[i]);
			} else {
				System.out.print(bfspath[i]);
			}
		}
	}

	private void bfs(int u) {
		ArrayList<Integer> visited = new ArrayList<>();

		Queue<ArrayList<Integer>> queue = new LinkedList<>();
		ArrayList<Integer> entry = new ArrayList<>();
		entry.add(u);
		entry.add(u);
		queue.offer(entry);
		visited.add(u);

		while (!queue.isEmpty()) {
			ArrayList<Integer> pair = queue.poll();
			int cv = pair.get(0);
			int pv = pair.get(1);
			work[0]++;

			bfsorder[size[0]] = cv;
			bfspath[size[0]++] = pv;

			for (int i = 0; i < g.numFanout(cv); i++) {
				work[0]++;
				int nv = g.getNodeFanout(cv, i);
				if (!visited.contains(nv)) {
					entry = new ArrayList<>();
					entry.add(nv);
					entry.add(cv);
					queue.offer(entry);
					visited.add(nv);
				}

			}
		}
	}

	public static void main(String[] args) {
		System.out.println("GraphBfs.java starts");
		System.out.println("Use GraphTest.java to test");
		System.out.println("GraphBfs.java Ends");
	}
}
