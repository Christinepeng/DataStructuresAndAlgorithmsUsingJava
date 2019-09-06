import java.util.ArrayList;

/**
 * File Name: Island.java 
 * Island concrete class
 * 
 * 
 * To Compile: IntUtil.java RandomInt.java Island.java IslandBase.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class Island extends IslandBase{
	//You can have any number of private data or private functions here
	int[][] dirFour = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	int[][] dirEight = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};


	Island() {
		//NOTHING CAN BE CHANGED HERE
		testBench();
	}
	
	@Override
  	void numIslands() {
		//WRITE YOUE CODE
		islands = new ArrayList<>();
		boolean[][] visited = new boolean[numRows()][numCols()];
		//YOU CAN HAVE ANY NUMBER OF PRIVATE functions and variables
		for (int r = 0; r < numRows(); r++) {
			for (int c = 0; c < numCols(); c++) {
				if (!visited[r][c] && getRC(r, c) == '1') {
					ArrayList<Integer> island = new ArrayList<>();
					dfs(r, c, island, visited);
					islands.add(island);
				}
			}
		}
	}

	private void dfs(int r, int c, ArrayList<Integer> island, boolean[][] visited) {
		numRecursion++;
		visited[r][c] = true;
		if (neighbor == 4) {
			for (int[] d : dirFour) {
				int nr = r + d[0];
				int nc = c + d[1];
				if (nr >= 0 && nr < numRows() && nc >= 0 && nc < numCols() && !visited[nr][nc] && getRC(nr, nc) == '1') {
					island.add(nr);
					island.add(nc);
					dfs(nr, nc, island, visited);
				}
			}
		} else {
			for (int[] d : dirEight) {
				int nr = r + d[0];
				int nc = c + d[1];
				if (nr >= 0 && nr < numRows() && nc >= 0 && nc < numCols() && !visited[nr][nc] && getRC(nr, nc) == '1') {
					island.add(nr);
					island.add(nc);
					dfs(nr, nc, island, visited);
				}
			}
		}
	}

 
	
	public static void main(String[] args) {
		//NOTHING CAN BE CHANGED HERE
		System.out.println("Island problem STARTS");
		Island m = new Island() ;
		System.out.println("All Island tests passed. You now understand why DFS is required");
		System.out.println("Island problem ENDS");
	}
}