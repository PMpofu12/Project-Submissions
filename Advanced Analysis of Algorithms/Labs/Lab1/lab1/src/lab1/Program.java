package lab1;
import java.util.*;
import java.util.Random;
 

public class Program {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// EXPERIMENTATION
		
		// 1. Best Case (The value is in the first in position 0):
		// Time will always be 0 milliseconds, because it's constant time. 
		
		
		// 2. Average Case (The value is in the middle of the array):
		/*
		 * System.out.
		 * println("Average Case (The value is in the middle of the array): \n" ); int[]
		 * ave_case0 = populateArray(49000); linearSearch(48999,ave_case0);
		 * 
		 * int[] ave_case1 = populateArray(98000); linearSearch(97999,ave_case1);
		 * 
		 * int[] ave_case2 = populateArray(147000); linearSearch(146999,ave_case2);
		 * 
		 * int[] ave_case3 = populateArray(196000); linearSearch(195999,ave_case3);
		 * 
		 * int[] ave_case4 = populateArray(245000); linearSearch(244999,ave_case4);
		 * 
		 * int[] ave_case5 = populateArray(294000); linearSearch(293999,ave_case5);
		 * 
		 * int[] ave_case6 = populateArray(343000); linearSearch(342999,ave_case6);
		 * 
		 * int[] ave_case7 = populateArray(392000); linearSearch(391999,ave_case7);
		 * 
		 * int[] ave_case8 = populateArray(441000); linearSearch(440999,ave_case8);
		 * 
		 * int[] ave_case9 = populateArray(490000); linearSearch(489999,ave_case9);
		 */
		
		// 3. Worst Case (The value is in the end of the array):
		// Just keep adjusting the values to get whatever it is you need.
		int[] worst_case0 = populateArray(1000000); 
		linearSearch(999999,worst_case0);
		

		
		
		
	}
	
	
	// Implement a method that generates an array of n distinct random integers.
	public static int[] randomArray(int size) {
		int upperBound = 180000;
		Random rand = new Random(); 		
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			int random = rand.nextInt(upperBound);
			array[i] = random;
		}
		return array;
	}
	
	// Implement a method that generates an array of n distinct integers which are arranged in an increasing order.
	public static int[] bubbleSort(int size) {
		int[] array = new int[size];
		array = randomArray(size);
		for (int i = array.length; i > 0; i--) {
			for (int j = 0; j < i -1; j++) {
				if (array[j] > array[j+1]) {
					int prev = array[j];
					array[j] = array[j+1];
					array[j+1] = prev;
				}
			}
		}
		return array;
	}
	
	// Implement the linear search algorithm where the key may not exist.
	public static boolean linearSearch(int key, int[] array) {
		long startTime = System.currentTimeMillis(); // record how long the algorithms take to run
		boolean bool = false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				bool = true;
				//System.out.println("The value " + key + " is in the array.");
				break;
			}
		}			
		if(bool == false) {
			//System.out.println("The value " + key + " is not in the array.");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("For n = "+ key + " + 1: The algorithm took " + (endTime - startTime) + " millisecond/s to run.");
		return bool;
	}
	
	// Used to check that everything has been implemented correctly. 
	static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
	
	// Will be used to hardcode a solution. Randomized elements don't guarantee we find the key, and that messes up with the timing of the array.
	static int[] populateArray(int size) {
		int[] array = new int[size]; 
		for (int i = 0; i < array.length; i++) { // populate array
			array[i] = i;
		}
		return array;
	}
}
