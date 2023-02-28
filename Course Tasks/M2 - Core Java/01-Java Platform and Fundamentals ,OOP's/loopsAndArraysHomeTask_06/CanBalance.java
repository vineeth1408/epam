package loopsAndArraysHomeTask_06;
import java.util.Scanner;

public class CanBalance {

	static int startSum = 0,endSum = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter size ");
		int sizeOfArray = sc.nextInt();
		System.out.println("Enter "+sizeOfArray+" elements");
		int elements[] = new int[sizeOfArray];
		for (int i = 0; i < sizeOfArray; i++) {
			elements[i] = sc.nextInt();
		}
		System.out.println(canBalance(elements));
	}
	private static boolean canBalance(int[] nums) {
		startSum = sum(nums);
		for (int i = nums.length-1; i >= 0; i--) {
		    endSum += nums[i];
		    startSum -= nums[i];
		    if(startSum == endSum) {
		    	  return true;
		    }
		}
		return false;
	}
	private static int sum(int nums[]) {
 
		for (int i = 0; i < nums.length; i++)
			    startSum += nums[i];
		return startSum;
	}
}
