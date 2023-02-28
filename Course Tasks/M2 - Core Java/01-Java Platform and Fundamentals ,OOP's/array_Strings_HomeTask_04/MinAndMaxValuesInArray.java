package array_Strings_HomeTask_04;
import java.util.Scanner;

public class MinAndMaxValuesInArray {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Array Size: ");
		int n = sc.nextInt();
		
		System.out.println("Enter "+n+" Elements");
		int arr[] = new int[n];

		for (int i = 0; i < n; i++){
			arr[i] = sc.nextInt();
		}
		minMaxInArray(arr);
		// Naive approach is sort the array and return 0th index for min value
		// for max value return (n-1) index value
	}
	private static void minMaxInArray(int arr[]) {
		
		int min,max;
		if (arr[0] > arr[1]) {
			max = arr[0];
			min = arr[1];
		}
		else {
			max = arr[1];
			min = arr[0];
		}
		
		for (int i = 2; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
			else if (min > arr[i]) {
				min = arr[i];
			}
		}
		
		System.out.println("Min Value= "+min+" Max Value= "+max);
	}
}
