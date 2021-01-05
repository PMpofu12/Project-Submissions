import java.util.*;
public class Program {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		// 2. Implement the greedy approach to solve the optimal service problem.
		int total = 0;
		int[] array = new int[4]; // Store all of the inputs. 
		for (int i = 0; i < array.length; i++) {
			int input = in.nextInt();
			array[i] = input;
		}
		bubbleSort(array);
		for (int i = 0; i < array.length; i++) {
			total += array[i] * (array.length-i-1);
		}
		System.out.println(total);
		
	}
	
		static void printArray(int[] array) {
			for (int i = 0; i < array.length; i++) {
				System.out.println(array[i]);
			}
		}
		
		static int[] bubbleSort(int[] array) {
			for (int i = array.length; i > 1; i--) {
				for (int j = 0; j < i-1; j++) {
					if (array[j] > array[j+1]) {
						int temp = array[j];
						array[j] = array[j+1];
						array[j+1] = temp;
					}
				}
			}
			return array;
		}
}
