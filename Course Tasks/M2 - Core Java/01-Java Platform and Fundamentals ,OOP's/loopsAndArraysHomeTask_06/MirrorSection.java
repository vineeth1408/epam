package loopsAndArraysHomeTask_06;
import java.util.Scanner;

public class MirrorSection {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sizeOfArray = sc.nextInt();
		int arr[] = new int[sizeOfArray];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(maxMirror(arr));
	}
	public static int maxMirror(int nums[]) {
		int max = 0;
	    for (int start = 0; start < nums.length; start++) {
	        for (int end = nums.length-1; end >= 0; end--) {
	            int size = 0;
	            int startingIndex = start;
	            int endingIndex = end;
	            while (startingIndex < nums.length && endingIndex >= 0 && nums[startingIndex] == nums[endingIndex]) {
	                size++;
	                startingIndex++;
	                endingIndex--;
	          }
	          max = Math.max(max, size);
	        }
	    }
	  return max;
	}
}
