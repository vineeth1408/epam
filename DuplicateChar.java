package demo;
import java.util.Scanner;

public class DuplicateChar {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int charCount[] = new int[123];
		String s = sc.next();
		char charArray[] = s.toCharArray();
		for(int i=0;i<charArray.length;i++) {
			charCount[charArray[i]]++;
		}
		for(int i=0;i<=122;i++) {
			if(charCount[i]>1) {
				System.out.println((char)(i)+"count="+charCount[i]);
			}
		}
	}
}