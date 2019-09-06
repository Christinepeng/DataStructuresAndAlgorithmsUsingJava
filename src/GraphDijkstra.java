import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * File Name: GraphDijkstra.java 
 * Implements Dijkstra's algorithms
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * To compile you require: IntUtil.java RandomInt.java Graph.java GraphTest.javs GraphDijkstra.java
 */

class GraphDijkstra{
	private class NodeData {
		private int name;
		private double cost;
		private int from;
		private boolean visited;

		NodeData(int name, double cost, boolean v) {
			this.name = name;
			this.cost = cost;
			this.from = name; //the best way to go to city i is i
			this.visited = v;
		}
	}

	private class NodeDataComparator implements Comparator<NodeData> {
		@Override
		public int compare(NodeData x, NodeData y) {
			return (int)(x.cost - y.cost);
		}
	}

	//You can have any number of private variables and private functions
	//DATA GIVEN
	private String t ; //Title
	private String dotFile; //Write tree as a dot file
	private Graph g ;
	String start;
	String end;
	int[] work ;
	double[] cost ;
	//DATA REQUIRED FOR WORK
	//You can have any number of private variables
	//Data used to solve the problem
	private int startingcity;
	private int endingcity;
	private PriorityQueue<NodeData> minheap; //minheap of NodeData
	private NodeData[] a; //array of Nodedata. This cannot be bigger than numV
	private int numberOfNodeAddedToHeap;

	GraphDijkstra(String t, String dotFile, Graph g, String start,String end, int [] work, double [] cost) {
		this.t = t ;
		this.dotFile = dotFile ;
		this.g = g ;
		this.start = start;
		this.end = end ;
		this.work = work ;
		this.cost = cost ;
		//WRITE YOUR CODE
		g.u.openDotFile(dotFile);
		Dijkstra();
		g.u.closeDotFile(dotFile);
	}

	private void print(int v) {
		if (!t.isEmpty()) {
			if (v == -1) {
				System.out.println("============ " + t + " ================");
			} else  {
				System.out.println("WWorking on Vertex: " + g.getNodeRealName(v));
			}
			//Write ROW header
			int numv = g.getnumV();
			for (int i = 0; i < numv; i++) {
				System.out.format("%5s", g.getNodeRealName(a[i].name));
			}
			System.out.println();
			//Vistied or not
			for (int i = 0; i < numv; i++) {
				char ch = 'F';
				if (a[i].visited) {
					ch = 'T';
				}
				System.out.format("%5s", ch);
			}
			System.out.println();
			//Minimum cost known at this point
			for (int i = 0; i < numv; i++) {
				String ch = "L";
				double c = a[i].cost;
				if (c != g.INFINITY) {
					ch = Double.toString(c);
				}
				System.out.format("%5s", ch);
			}
			System.out.println();
			//How did we visit this city
			for (int i = 0; i < numv; i++) {
				System.out.format("%5s", g.getNodeRealName(a[i].from));
			}
			System.out.println();
		}
	}

	private void computePaths() {
		int numv = g.getnumV();
		for (int i = 0; i < numv; i++) {
			ArrayList<Integer> apath = new ArrayList<>();
			double[] c = {0.0};
			computePath_r(apath, i, c);
			printPaths(i, apath, c);
		}
	}

	private void computePath_r(ArrayList<Integer> apath, int f, double[] c) {
		int v = a[f].from;
		if (v != startingcity) {
			computePath_r(apath, v, c);
		}
		apath.add(f);
		if (v != f) {
			c[0] += g.getNodeFanoutEdgeWeightE(v, f);
		}
	}

	private void printPaths(int to, ArrayList<Integer> apath, double[] c) {
		//c must be equal to the stored weight
		g.u.myassert(c[0] == a[to].cost);
		if (!t.isEmpty()) {
			int start = startingcity;
			if (start != to) {
				String cs;
				int ws = startingcity;
				System.out.println("The best way to go from " + g.getNodeRealName(a[start].name)
				 + " to city " + g.getNodeRealName(a[start].name) + " is follows"); ///////////////////
				int l = apath.size();
				System.out.print(g.getNodeRealName(a[start].name));
				for (int j = 0; j < l; j++) {
					System.out.print(" - > ");
					int cj = apath.get(j);
					System.out.print(g.getNodeRealName(a[cj].name));
				}
				System.out.print("  Cost = ");
				double prevCost = 0;
				for (int i = 0; i < l; i++) {
					int costj = apath.get(i);
					if (i != l - 1) {
						System.out.print((a[costj].cost - prevCost) + " + ");
					} else {
						System.out.print(a[costj].cost - prevCost);
					}
					prevCost = a[costj].cost;
				}
				System.out.print(" = " + a[to].cost);
				System.out.println();
			}
		}
	}

	private void Statistics() {
		System.out.println(g.getGraphType());
		System.out.println("Num Vertices = " + g.getnumV());
		System.out.println("Num Edges    = " + g.getnumE());
		System.out.println("Word done    = " + work[0]);
		System.out.println("numberOfNodeAddedToHeap = " + numberOfNodeAddedToHeap);
		System.out.println("Shortest path from city " + g.getNodeRealName(startingcity) +
				" to city " + g.getNodeRealName(endingcity) + " = " + cost[0]);
	}

	private void Dijkstra() {
		numberOfNodeAddedToHeap = 0;
		startingcity = -1;
		endingcity = -1;
		startingcity = g.graphHasNode(start);
		if (startingcity != -1) {
			endingcity = g.graphHasNode(end);
			if (endingcity != -1) {
				setup(); //O(V)
				alg(); //O((E+V) *log V)
				cost[0] = a[endingcity].cost;
				computePaths();
				Statistics();
			} else {
				System.out.println("Ending City is not correct");
			}
		} else {
			System.out.println("Starting City is not correct");
		}
	}

	/**
	 * Time complexity: O(V)
	 * Space complexity: O(V)
	 */
	private void setup() {
		Comparator<NodeData> comparator = new NodeDataComparator();
		minheap = new PriorityQueue<>(10, comparator);
		int numv = g.getnumV();
		a = new NodeData[numv]; //This cannot be bigger than numv
		for (int i = 0; i < numv; i++) {
			double c = g.INFINITY;
			if (i == startingcity) {
				c = 0;
			}
			NodeData n = new NodeData(i, c, false);
			a[i] = n;
			minheap.add(n);
			numberOfNodeAddedToHeap++;
		}
	}

	private void alg() {
		print(-1);
		int itr = 0;
		do {
			if (minheap.isEmpty()) {
				break;
			}
			NodeData n = minheap.peek();
			minheap.remove();
			if (!n.visited) {
				int s = n.name; //Working node
				int k = g.numFanout(s); //Number of fanout
				itr++;
				for (int i = 0; i < k; i++) {
					//Visiting edge
					work[0] += 1;
					relax(s, i, itr);
					//Put s on heap again
					minheap.add(a[s]);
					numberOfNodeAddedToHeap++;
				}
				n.visited = true;
				print(s);
			}
		} while (true);
	}

	private void relax(int s, int i, int itr) {
		int f = g.getNodeFanout(s, i); //fanot of s
		double cost_s_to_f = g.getNodeFanoutEdgeWeight(s, i);
		//We know the best way to go to s
		double cost_of_visiting_s = a[s].cost;
		g.u.myassert(cost_of_visiting_s != g.INFINITY);
		//What is the cost to go from s to f
		//This is given by user
		g.u.myassert(cost_s_to_f != g.INFINITY);
		double cost = cost_of_visiting_s + cost_s_to_f;
		if (cost < a[f].cost) {
			//That means if a better path
			a[f].cost = cost;
			a[f].from = s; //We visited this city from s
			String d = g.getNodeRealName(s) + " -> " + g.getNodeRealName(f) +
					"[label = " + cost + "]" + "[xlabel = " + itr + "]";
			g.u.appendDotFile(dotFile, d);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("GraphDijkstra.java starts");
		System.out.println("Use GraphTest.java to test");
		System.out.println("GraphDijkstra.java Ends");
	}
}