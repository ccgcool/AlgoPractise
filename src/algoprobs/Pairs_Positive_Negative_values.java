package algoprobs;

import java.util.HashMap;
import java.util.Map;

// http://practice.geeksforgeeks.org/problems/pairs-with-positive-negative-values/0

public class Pairs_Positive_Negative_values {

	public static void main(String[] args) {
		int[] pos_neg_vales = {4, 8, 9, -4, 1, -1, -8, -9};
		
		Map<Integer, Integer> toggleMap = new HashMap<>();
		
		
		for(int i:pos_neg_vales){
			// Get absolute as key
			
			
			if(toggleMap.containsKey(Math.abs(i))) {
				System.out.println(i + " " + toggleMap.get(Math.abs(i)));
			}else {
				toggleMap.put(Math.abs(i), i);
			}
		}

	}

}
