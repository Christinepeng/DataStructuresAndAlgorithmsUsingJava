import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * File Name: Stock1.java 
 * Stock1 concrete class
 * 
 * 
 * To Compile: IntUtil.java RandomInt.java Stock1.java Stock1Base.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class Stock1 extends Stock1Base{
	//You can have any number of private data members here
	//You can have any number of private functions here
	Stock1() {
		//NOTHING CAN BE CHANGED HERE
		testBench();
	}
	
	@Override
	void NSquareTimeConstantSpace() {
		//NOTHING CAN BE CHANGED HERE
    nsquareTimeConstantSpace() ;
	}
	
	@Override
  void NlognTimeLognSpace() {
		//NOTHING CAN BE CHANGED HERE
    nlognTimelognSpace() ;
	}
	
	@Override
  void NTimeLognSpace() {
		//NOTHING CAN BE CHANGED HERE
    nTimelognSpace() ;
	}
	
	/*
	 * Time: O(n^2)
	 * Space: THETA(1)
	 * All your routine should match this answer
	 * Nothing can be changed in this routine
	 */
	private void nsquareTimeConstantSpace() {
		int gp = 0 ;
		int l = size() ;
		for (int buy = 0; buy < l-1; ++buy) {
			for (int sell = buy + 1; sell < l; ++sell) {
				++numSteps;
				int p = profit(sell,buy);
				if (p >= gp) { //So that I can make profit by keeping stock for less time
					gp = p ;
					buyDay = buy ;
					sellDay = sell ;
				}
			}
		}
	}
	
	/*
	 * Time: O(nlogn)
	 * Space: O(logn)
	 */
	private void nlognTimelognSpace() {
        int[] res = _nlognTimelognSpace(0, size()-1);
        buyDay = res[1];
        sellDay = res[2];
        System.out.format("%d %d\n", buyDay, sellDay);
	}

	private int[] _nlognTimelognSpace(int l, int r) {
	    if (l == r) {
	        return new int[]{0, l, r};
        } else {
	        int mid = (l + r) / 2;

	        // recursive
	        int[] l_res = _nlognTimelognSpace(l, mid);
            int[] r_res = _nlognTimelognSpace(mid+1, r);

            // min of left
            int l_idx = -1;
            int l_min = Integer.MAX_VALUE;
            for (int i = l; i <= mid; i++) {
                if (price(i) < l_min) {
                    l_min = price(i);
                    l_idx = i;
                }
                ++numSteps;
            }

            // max of right
            int r_idx = -1;
            int r_max = Integer.MIN_VALUE;
            for (int i = mid+1; i <= r; i++) {
                if (price(i) > r_max) {
                    r_max = price(i);
                    r_idx = i;
                }
                ++numSteps;
            }

            ArrayList<int[]> arr = new ArrayList<>();
            arr.add(l_res);
            arr.add(r_res);
            arr.add(new int[]{r_max-l_min, l_idx, r_idx});

            Collections.sort(arr, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    if (a[0] > b[0]) {
                        return -1;
                    } else if (a[0] == b[0]) {
                        return 0;
                    } else {
                        return +1;
                    }
                }
            });

            return arr.get(0);
        }
    }
	
	private void nTimelognSpace() {
		//NOT required this time
	}
	
	public static void main(String[] args) {
		//NOTHING CAN BE CHANGED HERE
		System.out.println("Stock1 problem STARTS");
		Stock1 m = new Stock1() ;
		System.out.println("All Stock1 tests passed. Now you can pass interviews");
		System.out.println("Stock1 problem ENDS");
	}
}