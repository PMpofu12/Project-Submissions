import java.util.*;
import java.math.*;
public class Program {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] points = new int[n*2];
		for (int i = 0; i < points.length; i++) {
			int point = in.nextInt();
			points[i] = point;
		}
		double minDistance = 1000000;
		double d = 0.0;
		for (int i = 0; i < points.length; i+=2) {
			for (int j = 0; j < points.length; j+=2) {
				if (i == (n*2)-2) {
					break;
				}
				d = euclideanDistance(points[0], points[1], points[i+2], points[i+3]);
				if (d < minDistance) {
					minDistance = d;
				}
			}
		}
		System.out.println(roundToThreeDecimalPlaces(minDistance));
		//printArray(points);
	}
	
	static double roundToThreeDecimalPlaces(double inputValue) {
		double scalable = Math.pow(10, 3);
		return Math.round(inputValue * scalable)/scalable;
	}
	
	// Used to check that everything has been implemented correctly. 
		static void printArray(int[] array) {
			for (int i = 0; i < array.length; i++) {
				System.out.println(array[i]);
			}
		}
	
	static double euclideanDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
}
