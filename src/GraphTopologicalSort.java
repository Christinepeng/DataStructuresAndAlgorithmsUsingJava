import java.util.*;

import static java.util.Collections.swap;

/**
 * File Name: GraphTopologicalSort.java 
 * Topological Sort on a Graph
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphTopologicalSort.java
 */

class GraphTopologicalSort{
	private String t ;
	private Graph g ;
	//Output
	boolean [] cycle;
	int [] work ;
	int [] size ;
	int [] topoorder ;
	boolean[] onStack;
	boolean[] visited;
	
	GraphTopologicalSort(String t, Graph g, boolean [] cycle, int [] work, int [] size, int [] topoorder) {
		this.t = t ;
		this.g = g ;
		this.cycle = cycle ;
		this.work = work ;
		this.size = size ;
		this.topoorder = topoorder ;
		this.onStack = new boolean[g.getnumV()];
		this.visited = new boolean[g.getnumV()];

		int u = 0;
		if (g.getType() == GraphTest.GraphType.DIRECTED || g.getType() == GraphTest.GraphType.WEIGHTED_DIRECTED) {
			work[0] = g.getnumV() + g.getnumE();
			directed_dfs(u);
			reverse(topoorder);
		} else {
			work[0] = g.getnumV() + g.getnumE()/2;
			undirected_dfs(u, u);
		}

		dump();
	}

	private void reverse(int[] arr) {
		for (int i = 0; i < arr.length/2; i++) {
			int t = arr[i];
			arr[i] = arr[arr.length-1-i];
			arr[arr.length-1-i] = t;
		}
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
		if ((g.getType() == GraphTest.GraphType.DIRECTED || g.getType() == GraphTest.GraphType.WEIGHTED_DIRECTED) && !cycle[0]) {
			System.out.print("DFS topological order =");
			for (int i = 0; i < size[0]; i++) {
				System.out.format(" %s", g.getNodeRealName(topoorder[i]));
			}
			System.out.println("");
			verify();
		} else {
			System.out.println("This order has no meaning");
		}
	}

	private void verify() {
		Set<Integer> node_set = new HashSet<>();
		boolean suc = true;
		for (int i = 0; i < size[0]; i++) {
			int u = topoorder[i];
			for (int j = 0; j < g.numFanin(u); j++) {
				int v = g.getNodeFanin(u, j);
				if (!node_set.contains(v)) {
					suc = false;
				}
			}
			node_set.add(u);
		}
		if (suc) {
			System.out.println("dfs assert passed");
		} else {
			System.out.println("dfs assert failed");
		}
	}

	private void directed_dfs(int u) {
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
		topoorder[size[0]++] = u;
	}

	private void undirected_dfs(int p, int u) {
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

		topoorder[size[0]++] = u;
	}

	public static void main(String[] args) {
		System.out.println("GraphTopologicalSort.java starts");
		System.out.println("Use GraphTester.java to test");
		System.out.println("GraphTopologicalSort.java Ends");
	}
}
