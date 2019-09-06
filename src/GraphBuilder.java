import java.io.*;
import java.util.ArrayList;

/**
 * File Name: GraphBuilder.java 
 * All routines that builds Graph
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphBuilder.java
 */

class GraphBuilder{
	private Graph g ;
	//You can have any number of private variables and private functions
	
	GraphBuilder(Graph g, String f) {
		this.g = g ;

		File file = new File(f);
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				st = st.trim();
				String[] input = st.split("\\s+");
				if (input.length < 2) { // this might happen because of corrupted input files
					continue;
				}

				g.insertOrFind(input[0], false);
				g.insertOrFind(input[1], false);
				int u_idx = g.graphHasNode(input[0]);
				int v_idx = g.graphHasNode(input[1]);
				if (g.getType() == GraphTest.GraphType.UNDIRECTED) {
					g.createEdge(u_idx, v_idx, 0, true);
					g.createEdge(u_idx, v_idx, 0, false);
					g.createEdge(v_idx, u_idx, 0, true);
					g.createEdge(v_idx, u_idx, 0, false);
				} else if (g.getType() == GraphTest.GraphType.DIRECTED) {
					g.createEdge(u_idx, v_idx, 0, true);
					g.createEdge(v_idx, u_idx, 0, false);
				} else if (g.getType() == GraphTest.GraphType.WEIGHTED_UNDIRECTED) {
					double w = Double.parseDouble(input[2]);
					g.createEdge(u_idx, v_idx, w, true);
					g.createEdge(u_idx, v_idx, w, false);
					g.createEdge(v_idx, u_idx, w, true);
					g.createEdge(v_idx, u_idx, w, false);
				} else if (g.getType() == GraphTest.GraphType.WEIGHTED_DIRECTED) {
					double w = Double.parseDouble(input[2]);
					g.createEdge(u_idx, v_idx, w, true);
					g.createEdge(v_idx, u_idx, w, false);
				}
			}
		} catch (Exception e) {
			System.out.println("File Reading Exception: " + e);
		}
	}

	public static void main(String[] args) {
		System.out.println("GraphBuilder.java starts");
		System.out.println("Use GraphTester.java to test");
		System.out.println("GraphBuilder.java Ends");
	}
}