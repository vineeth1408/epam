package homeTaskLoops_03;
import java.util.Scanner;

public class PyramidWithDoWhileLoop {
	
	static int OuterIteration = 0,UpdatedValue = 0,xDifference;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Size Of The Pyramid");
		int sizeOfThePyramid = sc.nextInt();
		
		System.out.println("Enter X Difference");
		xDifference = sc.nextInt();
		
		DisplayPyramid(sizeOfThePyramid);
	}
	private static void DisplayPyramid(int sizeOfThePyramid) {
		
		do {
			int InnerIteration = 0;
			PrintPyramid(InnerIteration);
			System.out.println();
			OuterIteration++;
			
		   }while(OuterIteration < sizeOfThePyramid);
	}
	private static void PrintPyramid(int InnerIteration) {
		do {
			System.out.print(UpdatedValue+" ");
			UpdatedValue += xDifference;	
			InnerIteration++;
			
		   }while(InnerIteration <= OuterIteration);
	}
}
