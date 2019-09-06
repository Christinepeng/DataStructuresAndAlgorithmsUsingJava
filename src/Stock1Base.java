import java.util.Arrays;

/**
 * File Name: Stock1Base.java 
 * Java11
 * To Compile: IntUtil.java RandomInt.java Stock1Base.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * YOU CANNOT CHANGE ANYTHING IN THIS FILE. READ ONLY
 */

abstract class Stock1Base{
	private class results {
		//ONLY For me - the tester
		private int buyDay ;
		private int sellDay ;
		private int numSteps ;
		results(int b, int s, int z) {
			buyDay = b ;
			sellDay = s ;
			numSteps = z ;
		}
	}
	protected IntUtil u = new IntUtil(); 
	private int[] a; //Data given. You cannot change it
	protected int buyDay = -1 ; //Find buyDay
	protected int sellDay = -1 ; //Find endDay ;
	protected int numSteps = 0 ; //Work done
	
	//I don't know how to write it
  //Override by the concrete class
  abstract void NSquareTimeConstantSpace() ;
  abstract void NlognTimeLognSpace() ;
  abstract void NTimeLognSpace() ;
  
  //You cannot access a
  protected int size() {
  	return a.length ;
  }
  
  //Get the price of the stock on day p
  //All array a access must go through this routine this routine
  protected int price(int p) {
  	int l = a.length ;
  	u.myassert(p >= 0 && p < l);
  	return a[p] ;
  }
  
  //All profit computation must go through this routine
  protected int profit(int s, int b) {
  	int l = a.length ;
  	u.myassert(b >= 0 && b < l);
  	u.myassert(s >= 0 && s < l && b <= s);
  	int p = a[s] - a[b] ;
  	return p ;
  }
  
  protected void testBench() {
  	basic() ;
  	random() ;
	}	
  
  /*
	 * Use this routine to print
	 */
	private void P(String t) {
		if (a.length > 2 && numSteps == 0) {
			System.out.println("How you can solve this problem in 0 steps. Fix numSteps") ;
			u.myassert(false);
		}
		int bp = a[buyDay] ;
		int sp = a[sellDay] ;
		int g = profit(sellDay,buyDay);
		u.myassert((sp - bp) == g);
		System.out.println(t + ": Sold on day " + sellDay + " for " + sp + ". Bought on day " + buyDay + " for " + bp + ". Profit = " + sp + "-" + bp + " = " + g  + ". NumSteps = " + numSteps) ;
	}
	
  
  private void init(final int [] q) {
  	a = q ;
  	buyDay = 0 ; //Find buyDay
  	sellDay = 0 ; //Find enday ;
  	numSteps = 0 ; 
  }
 
  private void assertAnswers(results d1, results d2) {
  	int p1 = profit(d1.sellDay,d1.buyDay) ;
		int p2 = profit(d2.sellDay,d2.buyDay) ;
		if (p1 != p2) {
			System.out.println("Correct answer = " + p1) ;
			System.out.println("Your    answer = " + p2) ;
			u.myassert(false); ;
		}
  }
  
  private void one(final int [] q) {
  	init(q) ;
  	System.out.println("-----------------------------------") ;
  	if (a.length < 16) {
  		u.pLn(a.length);
  		u.pLn(a) ;
  	}else {
  		System.out.println("--- Array length is " + a.length) ;
  	}
  	NSquareTimeConstantSpace() ;
  	P("O(n2)O(1) Alg      ") ;
  	results d1 = new results(buyDay,sellDay,numSteps) ;
  	init(q) ;
  	NlognTimeLognSpace() ;
  	P("O(nlogn)O(logn) Alg") ;
  	results d2 = new results(buyDay,sellDay,numSteps) ;
  	assertAnswers(d1,d2) ;
  }

  private void basic() {
  	{
  		final int [] a = {5};
  		one(a) ;
  	}
  	{
  		final int [] a = {6,5};
  		one(a) ;
  	}
  	{
  		final int [] a = {1,2,3};
  		one(a) ;
  	}
  	{
  		final int [] a = {3,2,1};
  		one(a) ;
  	}
  	{
  		final int [] a = { 5,10,4,6,12 };
  		one(a) ;
  	}
  	{
  		final int [] a = { 1,1,1,1,1,1};
  		one(a) ;
  	}
  	{
      final int a[] = { 1,2,3,4,5,6,7};
      one(a);
    }
  	{
      final int a[] = { 1,2,3,4,5,6};
      one(a);
    }
  	{
      final int a[] = { 7,6,5,4,3,2,1};
      one(a);
    }
  	{
      final int a[] = {6,5,4,3,2,1};
      one(a);
    }
  	{
  		//ascending
  		int N = 1000 ;
  		int [] a = u.generateNumberInIncreasingOrder(N,1);
  		one(a) ;
  	}
  	{
  		//descending
  		int N = 1000 ;
  		int [] a = u.generateNumberInDescendingOrder(N,1);
  		one(a) ;
  	}
  }
  
  private void random() {
  	final int max = 2000;
    final int n = 1000;
    for (int i = 0; i < max; ++i) {
      int [] a = u.generateRandomNumber(n, true, 50,100);
      one(a) ;
    }
  }
	
	public static void main(String[] args) {
		System.out.println("Stock1Base.java STARTS");
		String version = System.getProperty("java.version");
		System.out.println("Java version used for this program is " + version);
		System.out.println("You cannot instantiate Stock1Base class: Stock1Base p = new Stock1Base() ; ");
		//Stock1Base p = new Stock1Base() ;
		System.out.println("Stock1Base.java ENDS");
	}
}