package javaLanguageConstructs_05;
import java.util.Scanner;

public class MaxBlock {
	static int TempCount = 1,count = 1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		System.out.println(maxBlock(str));
	}
	public static int maxBlock(String str) {
	    if (str.length() > 0) {
			for (int i = 0; i < str.length(); i++) {
				if (i < str.length()-1 && str.charAt(i) == str.charAt(i + 1)) {
						TempCount++;
					}
				else {
					TempCount = 1;
				}
				if (TempCount > count) {
					count = TempCount;
				}
			}
			return count;
	    }
	    return 0;
	}
}
