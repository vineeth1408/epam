package javaLanguageConstructs_05;
import java.util.Scanner;

public class sumLimit {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		System.out.println(sumLimit(a, b));
	}
	public static int sumLimit(int a, int b) {
		  int sum = a + b;
		  String StringOfSum = String.valueOf(sum);
		  String StringOfA = String.valueOf(a);
		  int lengthofa = StringOfA.length();
		  int lengthofsum = StringOfSum.length();
		  
		  if(lengthofa == lengthofsum)
		    return sum;
		  else
		    return a;
		}
}
