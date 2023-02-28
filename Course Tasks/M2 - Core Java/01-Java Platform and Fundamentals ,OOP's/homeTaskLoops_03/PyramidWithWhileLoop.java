package homeTaskLoops_03;
import java.util.Scanner;

public class PyramidWithWhileLoop {
	static int OuterLoopIterator = 0,UpdatedValue = 0,xDifference;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Number");
		int sizeOfThePyramid = sc.nextInt();

		System.out.println("Enter X");
		xDifference = sc.nextInt();
		
		DisplayPyramid(sizeOfThePyramid);
	}
	private static void DisplayPyramid(int sizeOfThePyramid) {

		while (OuterLoopIterator < sizeOfThePyramid) {

			int InnerLoopIterator = 0;
			printPyramid(InnerLoopIterator);
			System.out.println();
			OuterLoopIterator++;
		}
	}
	private static void printPyramid(int InnerLoopIterator) {

		while (InnerLoopIterator <= OuterLoopIterator) {
			
			System.out.print(UpdatedValue+" ");
			UpdatedValue += xDifference;	
			InnerLoopIterator++;
		}
	}
}
