import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * File Name: GraphDot.java 
 * Writes graph as a dot file
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019s
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphDot.java
 */

class GraphDot{
	private Graph g ;
	private String fname;
	//You can have any number of private variables

	GraphDot(Graph g, String s) {
		this.g = g ;
		this.fname = s;

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(s));
			writer.write("digraph g {\n");
			if (g.isUndirectedGraph()) {
				writer.write("edge [dir=none, color=red]\n");
			} else {
				writer.write("edge [color=red]\n");
			}
			for (int u_idx = 0; u_idx < g.getnumV(); u_idx++) {
				int num_e = g.numFanout(u_idx);
				for (int i = 0; i < num_e; i++) {
					int v_idx = g.getNodeFanout(u_idx, i);
					if (!g.isUndirectedGraph() || u_idx <= v_idx) {
						String u_text = g.getRealName(u_idx);
						String v_text = g.getRealName(v_idx);
						if (g.getType() == GraphTest.GraphType.WEIGHTED_UNDIRECTED || g.getType() == GraphTest.GraphType.WEIGHTED_DIRECTED) {
							double w = g.getNodeFanoutEdgeWeight(u_idx, i);
							writer.write(String.format("\t%s -> %s [label = %.1f]\n", u_text, v_text, w));
						} else {
							writer.write(String.format("\t%s -> %s\n", u_text, v_text));
						}
					}
				}
			}
			writer.write("}\n");
			writer.close();
		} catch (Exception e) {
			System.out.println("File Write Exception: " + e);
		}
	}


	public static void main(String[] args) {
		System.out.println("GraphDot.java starts");
		System.out.println("Use GraphTester.java to test");
		System.out.println("GraphDot.java Ends");
	}
}