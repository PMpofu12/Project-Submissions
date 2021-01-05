import java.util.*;
import java.util.ArrayList;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;


public class Program {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); 
		ArrayList<String> arrayList = new ArrayList<>();
		String input; 
        for (int i = 0; i < n+1; i++) {
        	input = in.nextLine();
        	arrayList.add(input);
		}
        int[] array;
        for (int i = 1; i < arrayList.size(); i++) {
			array = convertToIntArray(arrayList.get(i));
			System.out.println(ternarySearch(array[array.length-1], array));
			//System.out.println(linearSearch(array[array.length-1], array)-1);
		}
		//int[] prac = {1, 2, 3, 4, 5, 6, 7, 7};
        //System.out.println(arrayList.get(2)); // Access the array elements starting from 1.
		//System.out.println(ternarySearch (prac, prac[prac.length-1]));
    } 
	
	static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
	
	static int[] convertToIntArray(String s) { 
		String sNew = s.replace(":", "");
        String sNew2 = sNew.replace("  ", " ");   
        String[] integerToStrings = sNew2.split(" ");
        int[] integerArray = new int[integerToStrings.length];  
		for (int i = 0; i < integerArray.length; i++){ 
		    integerArray[i] = Integer.parseInt(integerToStrings[i]);  
		}
		return integerArray;
	}
	
	
    public static int ternarySearch (int key, int[] array){ 
    	int[] newA = new int[array.length-1];
    	for (int i = 0; i < newA.length; i++) {
			newA[i] = array[i];
		}
        return ternarySearch(0, newA.length - 1, array[array.length-1], newA);
    }
    
    public static int ternarySearch (int start, int end, int key, int[] array){
        int midpoint1 = start + (end-start)/3, midpoint2 = start + 2*(end-start)/3;
        if (array[midpoint1] == key) {
        	return midpoint1;
        } else if (array[midpoint2] == key) { 
            return midpoint2;
        } else if (key < array[midpoint1]) { // Search the 1st third of the split array.
        	return ternarySearch (start, midpoint1-1, key, array);
        } else if (key > array[midpoint2]) { // Search 3rd third of the split array.
            return ternarySearch (midpoint2+1, end, key, array);
        } else { // Search the middle third of the split array. 
            return ternarySearch (midpoint1, midpoint2, key, array);   
        }
    }
}
