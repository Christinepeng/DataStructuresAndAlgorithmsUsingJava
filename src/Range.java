import java.util.Arrays;

/**
 * File Name: Range.java 
 * Range concrete class
 * 
 * 
 * To Compile: Hw1.IntUtil.java Hw1.RandomInt.java Range.java RangeBase.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class Range extends RangeBase{
	Range() {
		//NOTHING CAN BE CHANGED HERE
		testBench();
	}
	
	@Override
	int[] Range(int [] a, int n)  {
		//NOTHING CAN BE CHANGED HERE
		numSteps = 0 ; //MUST update this data. Without this assert fails
		return alg(a,n) ;
	}
	
	private int[] alg(int [] a, int n) {
		//WRITE CODE
		int[] res = new int[2];
		Arrays.fill(res, -1);

		//find head index
		int l = 0;
		int r = a.length - 1;
		while (l < r) {
			numSteps++;
			int m = (r - l)/2 + l;
			if (a[m] == n) {
				if (m == 0 || a[m - 1] != n) {
					res[0] = m;
					r = l;
					break;
				} else {
					r = m;
				}
			} else if (a[m] > n) {
				r = m;
			} else {
				l = m + 1;
			}
		}

		//find tail index
		l = 0;
		r = a.length - 1;
		while (l < r) {
			numSteps++;
			int m = (r - l)/2 + l;
			if (a[m] == n) {
				if (m == a.length - 1 || a[m + 1] != n) {
					res[1] = m;
					r = l;
					break;
				} else {
					l = m + 1;
				}
			} else if (a[m] > n) {
				r = m;
			} else {
				l = m + 1;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		//NOTHING CAN BE CHANGED HERE
		System.out.println("Range problem STARTS");
		Range m = new Range() ;
		System.out.println("All Range tests passed. Get me a bar of candy");
		System.out.println("Range problem ENDS");
	}
}