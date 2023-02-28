package loopsAndArraysHomeTask_06;
import java.util.Scanner;

public class MaxSpan {
	static int span = 0,temp = 0;

	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		int sizeOfArray = sc.nextInt();
		int arr[] = new int[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(maxSpan(arr));
	}
	private static int maxSpan(int[] nums) {
		for (int fixedIndex = 0; fixedIndex < nums.length; fixedIndex++) {
		    countMaxSpan(fixedIndex,nums);
		  }
		return span;
	}
	private static void countMaxSpan(int FixedIndex, int nums[]) {
		for (int MovingIndex = 0; MovingIndex < nums.length; MovingIndex++) {
		      if (nums[FixedIndex] == nums[MovingIndex]) {
		        temp = MovingIndex - FixedIndex + 1;
		        span = Math.max(temp,span);
		      }
		 }
	}
}
