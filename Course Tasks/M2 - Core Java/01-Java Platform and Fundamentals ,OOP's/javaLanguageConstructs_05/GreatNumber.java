package javaLanguageConstructs_05;
import java.util.Scanner;

public class GreatNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int ValueOfA = sc.nextInt();
		int ValueOfB = sc.nextInt();
		System.out.println(greatNumber(ValueOfA, ValueOfB));
	}
	private static boolean greatNumber(int ValueOfA, int ValueOfB)
	{
		boolean result = false;
		if (isNumberSix(ValueOfA) || isNumberSix(ValueOfB))
			result = true;
		if (isSumSix(ValueOfA, ValueOfB) || Math.abs(ValueOfA - ValueOfB) == 6)
			result = true;
		return result;
	}
	private static boolean isNumberSix(int number) {
		return number == 6;
	}
	private static boolean isSumSix(int ValueOfA, int ValueOfB) {
		return (ValueOfA + ValueOfB) == 6;
	}
}
