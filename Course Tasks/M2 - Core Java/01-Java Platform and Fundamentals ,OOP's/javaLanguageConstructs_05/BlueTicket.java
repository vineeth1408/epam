package javaLanguageConstructs_05;
import java.util.Scanner;

public class BlueTicket {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		System.out.println(blueTicket(a,b,c));
	}
	public static int blueTicket(int a, int b, int c) {
		
		  int result = 0;
		  if (Add(a,b) == 10 || Add(b,c) == 10 || Add(a,c) == 10)
		    result = 10;
		  else if ((Add(a,b) == Add(b,c) + 10) || (Add(a,b) == Add(c,a) + 10))
		    result = 5;
		  else
		    result = 0;
		  return result;
	}
	public static int Add(int a,int b) {
		return a + b;
	}
}
