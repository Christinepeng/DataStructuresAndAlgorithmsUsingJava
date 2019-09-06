/**
 * File Name: SnakeAndLadderBase.java 
 * Java11
 * To Compile: IntUtil.java RandomInt.java SnakeAndLadderBase.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * YOU CANNOT CHANGE ANYTHING IN THIS FILE. READ ONLY
 */

abstract class SnakeAndLadderBase{
	protected IntUtil u = new IntUtil(); 
	protected int shortestPath = 0 ; //compute shortest path from 1 to numSquare
	protected int work = 0 ; //You will propagate how much work you did
	protected int numSquare = 0 ; //I will give number of square on board
	protected int [][] ladders;
	protected int [][] snakes ;
	private String title;
	
	//I don't know how to write it
  //Override by the concrete class
  abstract void computeShortestPath() ;
	
	protected void testBench() {
  	basic() ;
  }
	
	private void init(String t, int n, int[][] l, int [][] s) {
		title = t;
		numSquare = n ;
		ladders  = l ;
		snakes = s ;
	}
	
	private void printResult(int expected) {
		System.out.println("Number of squares = " + numSquare) ;
		if (work == 0) {
			System.out.println("You must update how much work you did to compute shortest path") ;
			u.myassert(false);
		}
		System.out.println("Work done         = " + work) ;
		System.out.println("Shorest path from 1 to " +  numSquare + " = " + shortestPath) ;
		if (shortestPath != expected) {
			System.out.println("Expected answer = " + expected) ;
			u.myassert(false);
		}
	}
	
	private void one(String t, int n, int[][] l, int [][] s,int expected) {
		init(t,n,l,s);
		computeShortestPath();
		printResult(expected);
	}
	
	private void basic() {
		{
			int expected = 3 ;
			int[][] l = {
					{3,16},
					{21,32},
					{15,25}
			};
			int[][] s = {
					{12,2},
					{30,4},
					{35,22}
			};
			one("Board 36",36,l,s,expected) ;
		}
		{
			int expected = 3 ;
			int[][] l = {
					{32,62},
					{42,68},
					{12,98}
			};
			int[][] s = {
					{95,13},
					{97,25},
					{93,37},
					{79,27},
					{75,19},
					{49,47},
					{67,17}
			};
			one("Board100case1",100,l,s,expected) ;
		}
		{
			int expected = 5 ;
			int[][] l = {
					{8,52},
					{6,80},
					{42,26},//To trouble you
					{2,72}
			};
			int[][] s = {
					{51,19},
					{39,11},
					{29,37},//To trouble you
					{3,81},////To trouble you
					{59,5},
					{79,23},
					{7,53},
					{43,33},
					{21,77},
			};
			one("Board100case2",100,l,s,expected) ;
		}
		{
			int expected = 3 ;
			int[][] l = {
					{2,82},
					{64,99}
			};
			int[][] s = {
					{84,63},
			};
			one("SnakeisNotBad",99,l,s,expected) ;
		}
  }
	
	public static void main(String[] args) {
		System.out.println("SnakeAndLadderBase.java STARTS");
		String version = System.getProperty("java.version");
		System.out.println("Java version used for this program is " + version);
		System.out.println("You cannot instantiate SnakeAndLadderBase class: SnakeAndLadderBase p = new SnakeAndLadderBase() ; ");
		//SnakeAndLadderBase p = new SnakeAndLadderBase() ;
		System.out.println("SnakeAndLadderBase.java ENDS");
	}
}