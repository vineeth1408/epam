package loopsAndArraysHomeTask_06;
import java.util.Scanner;

public class CountClumps {

	static int count = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sizeOfArray = sc.nextInt();
		int arr[] = new int[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(countClumps(arr));
	}
	private static int countClumps(int[] arr) {
		boolean match = false;
		for (int i = 0; i < arr.length-1; i++) {
			if (arr[i] == arr[i+1] && !match) {
				
				match = true;
				count++;
			  }
		      else if (arr[i] != arr[i+1]) { 
		    	 match = false;
		    }
		  }
	    return count;
	}
}
