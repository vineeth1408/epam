package homeTaskLoops_03;
import java.util.Scanner;

public class PyramidWithForLoop {
	static int outerIteration = 0,updatedValue = 0,xDifference;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Number");
		int sizeOfThePyramid = sc.nextInt();
		
		System.out.println("Enter X");
		xDifference = sc.nextInt();
		
		DisplayPyramid(sizeOfThePyramid);
	}
	private static void DisplayPyramid(int sizeOfThePyramid) {
		for (int outerIterator=0;outerIterator<sizeOfThePyramid;outerIterator++) {
			printPyramid(outerIterator);
			System.out.println();
		}
	}
	private static void printPyramid(int OuterIterator) {
		for (int InnerIterator = 0; InnerIterator <= OuterIterator; InnerIterator++) {
			System.out.print(updatedValue+" ");
			updatedValue += xDifference;
		}
	}
}
