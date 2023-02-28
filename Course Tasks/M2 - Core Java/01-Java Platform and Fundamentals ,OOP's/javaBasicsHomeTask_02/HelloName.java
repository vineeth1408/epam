package javaBasicsHomeTask_02;
import java.util.Scanner;

public class HelloName {
	
	public static void main(String[] args) {
		Scanner scannerObject = new Scanner(System.in);
		String name = scannerObject.next();
		System.out.println("Hello, "+name);
	}

}
