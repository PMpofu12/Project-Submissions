import java.util.Scanner;

class Program{
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String [] inputTemp = input.split(" ");
		int n = Integer.parseInt(inputTemp[0]);
		int size = Integer.parseInt(inputTemp[1]);
		int k, mod;
		
		String [] initialArray = new String[n];
		String [] outputArray = new String[size];
		
		for(int i = 0; i < n; i++) {
			initialArray[i] = in.nextLine();
		}
		
		for(int i = 0; i < n; i++) {
			char [] word = initialArray[i].toCharArray();
			k = getASCII(word);
			mod = k % size;
			while(outputArray[mod] != null) {
				mod = (mod + 1) % size;
			}
			outputArray[mod] = initialArray[i];
		}
		for(int i = 0; i < size ; i++) {
			if(outputArray[i] == null) {
				System.out.println("nil");
			}
			else {
				System.out.println(outputArray[i]);
			}
		}
	}
	
public static int getASCII(char [] word) {
		int k = 0;
		for(int i = 0; i < word.length; i++) {	
			k += (int) word[i];
		}
		return k;
	}
}

