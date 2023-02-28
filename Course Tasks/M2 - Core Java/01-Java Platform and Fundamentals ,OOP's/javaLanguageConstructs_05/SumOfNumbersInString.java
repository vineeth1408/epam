package javaLanguageConstructs_05;
import java.util.Scanner;

public class SumOfNumbersInString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter String: ");
		String str = sc.next();
		System.out.println(CalculateResult(str));
	}
	private static int CalculateResult(String str) {
		String temp = "0";
		int result = 0;
		for (int i = 0; i < str.length(); i++) {	 
			 if (Character.isDigit(str.charAt(i))) {
					temp += str.charAt(i);
				}
			 else {
				 	result += Integer.parseInt(temp);
				 	temp = "0";
				}
			}
		 return result + Integer.parseInt(temp);
	}
}
