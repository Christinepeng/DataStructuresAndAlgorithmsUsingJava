import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * File Name: GraphDfs.java 
 * Depth First Search on a graph
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphDfs.java
 */

class GraphDfs{
	//You can have any number of private variables and private functions
	private String t ;
	private Graph g ;
	String start;
	//Output
	boolean[] cycle;
	int[] work ;
	int[] size ;
	int[] dfsorder;
	boolean[] onStack;
	boolean[] visited;
	
	GraphDfs(String t, Graph g, String start, boolean[] cycle, int[] work, int[] size, int[] dfsorder) {
		this.t = t ;
		this.g = g ;
		this.start = start;
		this.cycle = cycle ;
		this.work = work ;
		this.size = size ;
		this.dfsorder = dfsorder ;
		this.onStack = new boolean[g.getnumV()];
		this.visited = new boolean[g.getnumV()];

		int u = g.graphHasNode(start);
		if (g.getType() == GraphTest.GraphType.DIRECTED || g.getType() == GraphTest.GraphType.WEIGHTED_DIRECTED) {
			work[0] = g.getnumV() + g.getnumE();
			directed_dfs(u);
		} else {
			work[0] = g.getnumV() + g.getnumE()/2;
			undirected_dfs(u, u);
		}

		dump();
	}

	private void dump() {
		System.out.println("------------ " + t + " --------------------");
		System.out.println("Num Vertices = " + g.getnumV());
		System.out.println("Num Edges    = " + g.getnumE());
		System.out.println("Work done    = " + work[0]);
		if (cycle[0]) {
			System.out.println("Has Cycle    = YES");
		} else {
			System.out.println("Has Cycle    = NO");
		}
		if (g.getType() == GraphTest.GraphType.DIRECTED || g.getType() == GraphTest.GraphType.WEIGHTED_DIRECTED) {
			System.out.print("DFS order =");
			for (int i = 0; i < size[0]; i++) {
				System.out.format(" %s", g.getNodeRealName(dfsorder[i]));
			}
			System.out.println("");
			verify();
		} else {
			System.out.println("This order has no meaning");
		}
	}

	private void verify() {
		System.out.println("There is no need to verify DFS order");
	}

	private void directed_dfs(int u) {
		dfsorder[size[0]++] = u;
		onStack[u] = true;
		visited[u] = true;

		int num_edge = g.numFanout(u);
		for (int i = 0; i < num_edge; i++) {
			int v = g.getNodeFanout(u, i);
			if (visited[v]) {
				if (onStack[v]) {
					cycle[0] = true;
				}
			} else {
				directed_dfs(v);
			}
		}

		onStack[u] = false;
	}

	private void undirected_dfs(int p, int u) {
		dfsorder[size[0]++] = u;
		visited[u] = true;

		int num_edge = g.numFanout(u);
		for (int i = 0; i < num_edge; i++) {
			int v = g.getNodeFanout(u, i);
			if (v == p) { // one should never walk back to parent node on undirected graph (assume no duplicate edges)
				continue;
			}
			if (visited[v]) {
				cycle[0] = true;
			} else {
				undirected_dfs(u, v);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("GraphDfs.java starts");
		System.out.println("Use GraphTester.java to test");
		System.out.println("GraphDfs.java Ends");
	}
}