package array_Strings_HomeTask_04;
import java.util.Scanner;

public class MissingNumberInArray {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Array Size: ");
		int n = sc.nextInt();

		System.out.println("Enter elements in array (n-1): ");
		int arr[] = new int[n];

		for (int i = 0; i <n-1; i++) {
			arr[i] = sc.nextInt();
		}

		FindMissingNumber(arr, n);
	}
	private static void FindMissingNumber(int arr[], int n) {
		
		int ArraySum = 0;
		for (int i = 0; i < arr.length; i++) {
			ArraySum = ArraySum + arr[i];
		}
		System.out.println("Missing Number is: "+(sumOfNdigits(n) - ArraySum));
	}
	private static int sumOfNdigits(int n) {
		return (n *(n + 1) /2);
	}
}
