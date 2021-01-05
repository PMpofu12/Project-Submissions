package lab2;
import java.util.Random;
import java.util.Scanner;
public class Program {
	public static void main(String[] args) {
		//Scanner input = new Scanner(System.in);
		//int[] array = randomArray(1000000);
		//printArray(mergeSort(array, 0, array.length - 1));   
        
        System.out.println("For n = 100 000:");
        int[] array_1 = randomArray(100000);
        bubbleSort_Escape(array_1);
        
        System.out.println("For n = 150 000:");
        int[] array_2 = randomArray(150000);
        bubbleSort_Escape(array_2);
        
        System.out.println("For n = 200 000:");
        int[] array_3 = randomArray(200000);
        bubbleSort_Escape(array_3);
        
        System.out.println("For n = 2 500 000:");
        int[] array_4 = randomArray(250000);
        bubbleSort_Escape(array_4);
        
        System.out.println("For n = 3 000 000:");
        int[] array_5 = randomArray(300000);
        bubbleSort_Escape(array_5);

        System.out.println("For n = 3 500 000:");
        int[] array_6 = randomArray(350000);
        bubbleSort_Escape(array_6);
        
        System.out.println("For n = 4 000 000:");
        int[] array_7 = randomArray(400000);
        bubbleSort_Escape(array_7);
       
        System.out.println("For n = 4 500 000:");
        int[] array_8 = randomArray(450000);
        bubbleSort_Escape(array_8);
        
        System.out.println("For n = 5 000 000:");
        int[] array_9 = randomArray(500000);
        bubbleSort_Escape(array_9);
        
        System.out.println("For n = 5 500 000:");
        int[] array_10 = randomArray(550000);
        bubbleSort_Escape(array_10);
	}
	
	// LAB 1 STUFF:
	// Implement a method that generates an array of n distinct random integers.
	public static int[] randomArray(int size) {
		int upperBound = 2000000; 
		Random rand = new Random(); 		
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			int random = rand.nextInt(upperBound); 
			array[i] = random;
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
	
	// LAB 2 STUFF:
	// Implement the bubble sort algorithm with no escape clause.
	static int[] bubbleSort(int[] array) {
		long startTime = System.currentTimeMillis(); // record how long the algorithms take to run
		int keyComparisons = 0;
		for (int i = array.length; i > 1; i--) {
			for (int j = 0; j < i-1; j++) {
				if (array[j] > array[j+1]) {
					keyComparisons = keyComparisons + 1;
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		long endTime = System.currentTimeMillis(); 
		System.out.println("The algorithm took " + (endTime - startTime) + " millisecond/s to run.");
		System.out.println("The algorithm made " + keyComparisons + " comparisons.");
		return array;
	}
	
	// Implement the bubble sort algorithm with an escape clause.
	static int[] bubbleSort_Escape(int[] array) {
		long startTime = System.currentTimeMillis(); // record how long the algorithms take to run
		int i = array.length;
		boolean sorting = true;
		while (i >= 1 && sorting == true ) {
			boolean swopped = false;
			for (int j = 0; j < i-1; j++) {
				if (array[j] > array[j+1]) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					swopped = true;
				}
			}
			if (swopped == false) {
				sorting = false;
			}else {
				i = i-1;
			}
		}
		long endTime = System.currentTimeMillis(); 
		System.out.println("The algorithm took " + (endTime - startTime) + " millisecond/s to run.");
		return array;
	}
	 
	// Helper function for the mergeSort function.
    static void merge(int arr[], int l, int m, int r) {
    	
    	// Merges two subarrays of arr[] 
    	// First subarray is arr[l..m] 
        // Second subarray is arr[m+1..r]
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int[n1]; 
        int R[] = new int[n2]; 
  
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j = 0; j < n2; ++j) 
            R[j] = arr[m + 1 + j]; 
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) { 
            if (L[i] <= R[j]) { 
                arr[k] = L[i]; 
                i++; 
            } 
            else { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    }
    
    // Implement the mergeSort algorithm.
    static int[] mergeSort(int array[], int l, int r) {
    	
        if (l < r) { 
            // Find the middle point 
            int m = (l + r) / 2; 
  
            // Sort first and second halves 
            mergeSort(array, l, m); 
            mergeSort(array, m + 1, r); 
  
            // Merge the sorted halves 
            merge(array, l, m, r); 
        }
        
        return array;
    }
	
	
	
}