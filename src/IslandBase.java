import java.util.ArrayList;

/**
 * File Name: IslandBase.java 
 * Java11
 * To Compile: IntUtil.java RandomInt.java IslandBase.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * YOU CANNOT CHANGE ANYTHING IN THIS FILE. READ ONLY
 */

abstract class IslandBase{
	protected IntUtil u = new IntUtil(); 
	protected int neighbor = 4 ; //Input: Guaranteed to be 4 or 8.
	protected int numRecursion = 0; //YOU MUST POPULATE IF YOU USE RECURSION
	protected ArrayList<ArrayList<Integer> > islands; //YOU MUST POPULATE
	//The size of islands array gives number of islands
	
	//All below is for tester
	private char[][] grid ;
	private int numGridAccess = 0 ;
	private int numRows = 0 ;
	private int numCols = 0 ;
	
	//I don't know how to write it
  //Override by the concrete class
  abstract void numIslands() ;
  
  protected void testBench() {
  	basic4() ;
  	basic8() ;
  }
  
  protected int numRows() {
  	return numRows;
  }
  
  protected int numCols() {
  	return numCols;
  }
  
  protected char getRC(int r, int c) {
  	++numGridAccess ;
  	u.myassert(r >= 0 && r < numRows) ;
  	u.myassert(c >= 0 && c < numCols) ;
  	return (grid[r][c]);
  }
  
  private int assertSize(final String as, int ssize) {
  	int s = as.length();
		if (ssize == 0) {
			ssize = s ;
		}else {
			u.myassert(ssize == s);
		}
		return ssize ;
  }
  
  /*
   * Print matrix
   */
  private void PM(final String[] a) {
  	int l = a.length ;
  	for (int i = 0; i < l ; ++i) {
  		final String as = a[i] ;
  		System.out.println(as) ;
  	}
  }
  
  /*
   * Print island matrix
   */
  private void PM() {
  	char[][] islandMatrix = new char[numRows()][numCols()] ;
  	for (int r = 0; r < numRows(); ++r) {
  		for (int c = 0; c < numCols(); ++c) {
  			islandMatrix[r][c] = '0' ;
  		}
		}
  	int numIsland = islands.size();
  	for (int i = 0; i < numIsland; ++i) {
  		ArrayList<Integer> aisland = islands.get(i);
  		int size = aisland.size();
  		u.myassert(size % 2 == 0); //This must be even size
  		for (int j = 0; j < size; j = j + 2) {
  			int r = aisland.get(j);
  			int c = aisland.get(j+1);
  			char ch = (char)((i + 1) + '0');
  			if (i + 1 > 9) {
  				i = (i + 1) - 10 ;
  				ch = (char)('a' + i );
  			}
  			islandMatrix[r][c] = ch ;
  		}
  	}
  	for (int r = 0; r < numRows(); ++r) {
  		for (int c = 0; c < numCols(); ++c) {
  			char ch = islandMatrix[r][c] ;
  			System.out.print(ch) ;
  		}
  		System.out.println();
		}
  }
  
  private void init(int num) {
  	numRecursion = 0; 
  	numGridAccess = 0 ;
  	neighbor = num ;
  	islands = new ArrayList<ArrayList<Integer> >();
  }
  
  private void one(final String[] a, int neighbor) {
  	init(neighbor) ;
  	System.out.println("--------------- Start -------------------") ;
  	PM(a);
  	int l = a.length ;
  	numRows = l ;
  	int ssize = 0 ;
  	for (int i = 0; i < l ; ++i) {
  		final String as = a[i] ;
  		ssize = assertSize(as,ssize) ;
  	}
  	numCols = ssize ;
  	grid = new char[l][ssize];
  	for (int i = 0; i < l ; ++i) {
  		final String as = a[i] ;
  		for (int j = 0 ; j < ssize; ++j) {
  			final char ch = as.charAt(j);
  			u.myassert(ch == '0' || ch == '1');
  			grid[i][j] = ch ;
  		}
  	}
  	numIslands();
  	int numIsland = islands.size();
  	System.out.println("Number of islands = " + numIsland + " using " + neighbor + " neighbors") ;
  	System.out.println("Number of Recursion = " + numRecursion) ;
  	System.out.println("Number of Grid access = " + numGridAccess) ;
  	PM() ;
  }
  
  private void basic4() {
  	int neighbor = 4 ;
  	{
  		final String[] a = {} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 0);
  	}
  	{
  		final String[] a = {"1"} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 1);
  	}
  	{
  		final String[] a = {"11111"} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 1);
  	}
  	{
  		final String[] a = {"11111", "11111", "11111"} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 1);
  	}
  	{
  		final String[] a = {"00000", "00000", "00000"} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 0);
  	}
  	
  	{
  		final String[] a = {"11111", "00000", "11111"} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 2);
  	}
  	{
  		final String[] a = {"10101", "01010", "10101"} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 8);
  	}
  	{
  		final String[] a = {"10101"} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 3);
  	}
  	{
  		final String[] a = {"1000", "0100", "0010", "0001"} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 4);
  	}
  	{
  		final String[] a = { "11110", "11010", "11000", "00000" } ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 1);
  	}
  	{
  		final String[] a = {"11000","11000","00100","00011" } ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 3);
  	}
  }
  
  private void basic8() {
  	int neighbor = 8 ;
  	{
  		final String[] a = {"11000", "01001", "10011", "00000","10101"} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 5);
  	}
  	{
  		final String[] a = {"11000", "01001", "10011", "00000","10110"} ;
  		one(a,neighbor) ;
  		u.myassert(islands.size() == 4);
  	}
  }
  
  public static void main(String[] args) {
		System.out.println("IslandBase.java STARTS");
		String version = System.getProperty("java.version");
		System.out.println("Java version used for this program is " + version);
		System.out.println("You cannot instantiate IslandBase class: IslandBase p = new IslandBase() ; ");
		//IslandBase p = new IslandBase() ;
		System.out.println("IslandBase.java ENDS");
	}
}
