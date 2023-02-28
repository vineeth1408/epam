package javaLanguageConstructs_05;
import java.util.Scanner;

public class shareDigit {
	public static int a,b,value;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for(;;) {// taking input until unless user enter two inputs which are >=10 and <=99
			a = sc.nextInt();
			if (a >= 10 && a <= 99) {
				b = sc.nextInt();
					if (b >= 10 && b <= 99) {
						break;
					}
			}
		}
		System.out.println(findResult(a, b));
	}
	private static boolean findResult(int a, int b) {
		int Quotient = a / 10;
		int Remainder = a % 10;
		if (IsContains(a, b, Quotient))
			return true;
		else if (IsContains(a, b, Remainder))
			return true;
		else
			return false;
		
	}
	private static boolean IsContains(int a, int b, int value) {
		
		String s1 = String.valueOf(a);
		String s2 = String.valueOf(b);
		if (s1.contains(String.valueOf(value)) && s2.contains(String.valueOf(value)))
			return true;
		else
			return false;
	}
}
		
		