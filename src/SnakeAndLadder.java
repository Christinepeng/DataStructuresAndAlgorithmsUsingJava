import java.util.*;

/**
 * File Name: SnakeAndLadder.java
 * SnakeAndLadder concrete class
 *
 *
 * To Compile: IntUtil.java RandomInt.java SnakeAndLadder.java SnakeAndLadderBase.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class SnakeAndLadder extends SnakeAndLadderBase{
	//You can have any number of private data or private functions here
	SnakeAndLadder() {
		//NOTHING CAN BE CHANGED HERE
		testBench();
	}

	@Override
  	void computeShortestPath() {
		//WRITE YOUE CODE
		//YOU CAN HAVE ANY NUMBER OF PRIVATE functions and variables
		shortestPath = 0;

		Set<Integer> setNum = new HashSet<>();
		for (int i = 2; i <= numSquare; i++) {
			setNum.add(i);
		}

		Map<Integer, Integer> snakeAndLadder = new HashMap<>();
		for (int[] l : ladders) {
			snakeAndLadder.put(l[0], l[1]);
		}
		for (int[] s : snakes) {
			snakeAndLadder.put(s[0], s[1]);
		}

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		alg(setNum, snakeAndLadder, queue) ;
	}

	private void alg(Set<Integer> setNum, Map<Integer, Integer> snakeAndLadder, Queue<Integer> queue) {
		//WRITE CODE NOW
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				if (!queue.isEmpty()) {
					int cn = queue.poll();

					for (int j = 1; j <= 6; j++) {
						work++;
						int nn = cn + j;
						//snake or ladder
						if (snakeAndLadder.containsKey(nn)) {
							nn = snakeAndLadder.get(nn);
						}

						//check haven't visited
						if (setNum.contains(nn)) {
							queue.offer(nn);

							//stop condition
							if (nn == numSquare) {
								queue.clear();
								i = size;
								j = 7;
							}
							setNum.remove(nn);
						}
					}
				}
			}
			shortestPath++;
		}
	}

	public static void main(String[] args) {
		//NOTHING CAN BE CHANGED HERE
		System.out.println("SnakeAndLadder problem STARTS");
		SnakeAndLadder m = new SnakeAndLadder() ;
		System.out.println("All SnakeAndLadder tests passed. You now understand why BFS is required");
		System.out.println("SnakeAndLadder problem ENDS");
	}
}




//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.Queue;
//
///**
// * File Name: SnakeAndLadder.java
// * SnakeAndLadder concrete class
// *
// *
// * To Compile: IntUtil.java RandomInt.java SnakeAndLadder.java SnakeAndLadderBase.java
// *
// * @author Jagadeesh Vasudevamurthy
// * @year 2019
// */
//
//class SnakeAndLadder extends SnakeAndLadderBase{
//	//You can have any number of private data or private functions here
//	SnakeAndLadder() {
//		//NOTHING CAN BE CHANGED HERE
//		testBench();
//	}
//
//	@Override
//	void computeShortestPath() {
//		//WRITE YOUE CODE
//		//YOU CAN HAVE ANY NUMBER OF PRIVATE functions and variables
//		alg() ;
//	}
//
//
//	class SL {
//		private HashMap<Integer, Integer> hm;
//
//		SL() {
//			hm = new HashMap<Integer, Integer>();
//		}
//
//		int getOther(int x) {
//			int o = -1;
//			boolean f = hm.containsKey(x);
//			if (f) {
//				o = hm.get(x);
//			}
//			return o;
//		}
//
//		void insert(int x, int y) {
//			boolean f = hm.containsKey(x);
//			u.myassert(f == false);
//			f = hm.containsKey(y);
//			u.myassert(f == false);
//			hm.put(x, y);
//		}
//	}
//
//	private class data {
//		private int name; // 0 to N+1
//		private int level; // level of the node from 1
//		private int from; // How did you visit this node
//		private int through; // if from !=through, use snake or ladder
//
//		data(int i) {
//			this.name = i;
//			this.level = -1;
//			this.from = -1;
//			this.through = -1;
//		}
//	}
//
//	private SL L;
//	private SL S;
//	private data[] dataarray;
//	private Queue<data> q;
//
//	private void store(boolean t, int[][] a, SL sl) {
//		for (int [] b: a) {
//			int n = b.length;
//			u.myassert(n==2);
//			int x = b[0];
//			int y = b[1];
//			if (t == true) {
//				// ladder case
//				// x > y
//				if (x > y) {
//					int p = x;
//					x = y;
//					y = p;
//				}
//				u.myassert(x<y);
//				sl.insert(x, y);
//			}else {
//				// snake case
//				// y > x
//				if (y < x) {
//					int p = x;
//					x = y;
//					y = p;
//				}
//				u.myassert(y>x);
//				sl.insert(y, x);
//			}
//		}
//	}
//
//	private void storeLaddersAndSnakes() {
//		store(true, ladders, L);
//		store(false, snakes, S);
//	}
//
//	private int goOnSnake(int p) {
//		int l = S.getOther(p);
//		return l;
//	}
//
//	private int goOnLadder(int p) {
//		int l = L.getOther(p);
//		return l;
//	}
//
//	private void init() {
//		L = new SL();
//		S = new SL();
//		q = new LinkedList<data>();
//		dataarray = new data[numSquare + 1]; // 0 is unsued. 1 is 36
//		for (int i = 0; i <= numSquare; ++i) {
//			data d = new data(i);
//			dataarray[i] = d;
//		}
//	}
//
//	private void bfs() {
//		data ns = dataarray[1]; // first square
//		ns.level = 0;
//		ns.level = 1;
//		ns.through = 1;
//		q.add(ns);
//		while (q.isEmpty() == false) {
//			data n = q.remove();
//			// visiting node n
//			++work;
//			//Explore all places you can go from this space
//			for(int i = 1; i <=6; ++i) {
//				int z = n.name + i ;
//				if (z <= numSquare) {
//					data nz = dataarray[z];
//					if (nz.level != -1) {
//						// already visited;
//						continue;
//					}
//					int nladder = goOnLadder(z);
//					int nsnake = goOnSnake(z);
//					if (nladder != -1) {
//						u.myassert(nsnake == -1);
//					}
//					if (nsnake != -1) {
//						u.myassert(nladder == -1); // if snake is there, ladder cannot be.
//
//					}
//					if ((nladder != -1) && (dataarray[nladder].level != -1)) {
//						// already visited;
//						continue;
//					}
//					if ((nsnake != -1) && (dataarray[nsnake].level != -1)) {
//						// already visited
//						continue;
//					}
//					// now we are working
//					++work;
//					int nzname = nz.name;
//					if (nladder == -1 && nsnake == -1) {
//						// neither ladder is there nor snake
//					} else if (nladder != -1) {
//						// you got ladder
//						nz = dataarray[nladder];
//					}else if (nsnake != -1) {
//						// you got snake
//						nz = dataarray[nsnake];
//					}
//					nz.level = 1 + n.level;
//					nz.from = n.name;
//					nz.through = nzname;
//					q.add(nz); // add to queue
//				}
//			}
//
//		}
//	}
//
//	private void printPath() {
//		ArrayList<data> dataa = new ArrayList<data>();
//		printPath_r(dataa, numSquare);
//		int s = dataa.size();
//		u.myassert(s == shortestPath);
//		int current = 1;
//		for (int i = s-1; i >=0; --i) {
//			if (current == 1) {
//				System.out.print(current);
//			}
//			System.out.print("-->");
//			data d = dataa.get(i);
//			if(d.through != d.name) {
//				System.out.print("(" + d.through + ")");
//			}
//			System.out.print(d.name + " ");
//			current = d.name;
//		}
//		System.out.println();
//
//	}
//
//	private void printPath_r(ArrayList<data> data, int numSquare) {
//		// TODO Auto-generated method stub
//
//	}
//
//	private void alg() {
//		//WRITE CODE NOW
//		init();
//		storeLaddersAndSnakes();
//		bfs();
//		shortestPath = dataarray[numSquare].level;
//		if(shortestPath != -1) {
//			printPath();
//		}
//	}
//
//	public static void main(String[] args) {
//		//NOTHING CAN BE CHANGED HERE
//		System.out.println("SnakeAndLadder problem STARTS");
//		SnakeAndLadder m = new SnakeAndLadder() ;
//		System.out.println("All SnakeAndLadder tests passed. You now understand why BFS is required");
//		System.out.println("SnakeAndLadder problem ENDS");
//	}
//}