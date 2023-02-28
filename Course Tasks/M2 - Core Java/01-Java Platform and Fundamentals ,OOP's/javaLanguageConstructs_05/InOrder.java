package javaLanguageConstructs_05;
import java.util.Scanner;

public class InOrder {
	static int a,b,c;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		boolean bOK = sc.nextBoolean();
		System.out.println(inOrder(a, b, c, bOK));
	}
	private static boolean inOrder(int a, int b, int c, boolean bOk) {
		boolean result = false;	
		if (bOk) {
			if (isGreater()) {
				result = true;
			}
		}
		else {
			if (isGreater()) {
				result = true;
			}
		}  
		if (bOk == true && b <= a && c > b)
		    result = true;
		else
		    result = false;
	return result;
	}	
	private static boolean isGreater() {
		return (b > a && c > b);
	}
}

