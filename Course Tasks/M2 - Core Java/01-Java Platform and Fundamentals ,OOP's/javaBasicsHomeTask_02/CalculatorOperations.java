package javaBasicsHomeTask_02;
import java.util.Scanner;

public class CalculatorOperations {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int firstValue = sc.nextInt();
		int secondValue = sc.nextInt();
		char operation = sc.next().charAt(0);
		CalculateResult(firstValue, secondValue, operation);
	}
	private static void CalculateResult(int firstValue, int secondValue, char operation)
	{
		switch(operation) {
			case '+': System.out.println(Addition(firstValue, secondValue));
				break;
			case '-': System.out.println(Subtraction(firstValue, secondValue));
				break;
			case '*': System.out.println(Multiplication(firstValue, secondValue));
				break;
			case '/': System.out.println(Division(firstValue, secondValue));
				break;
			case '%': System.out.println("Remainder= "+(firstValue % secondValue));
					  System.out.println("Percentage= "+(((secondValue - firstValue)*100) / firstValue));
				break;
			default:  System.out.println("Invalid Operation");
		}
	}
	private static int Addition(int firstValue, int secondValue) {
		return firstValue + secondValue;
	}
	private static int Subtraction(int firstValue, int secondValue) {
		return firstValue - secondValue;
	}
	private static int Multiplication(int firstValue, int secondValue) {
		return firstValue * secondValue;
	}
	private static int Division(int firstValue, int secondValue) {
		return firstValue / secondValue;
	}
}
