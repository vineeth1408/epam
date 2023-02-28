package javaLanguageConstructs_05;
import java.util.Scanner;

public class ReplaceSubString {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String base = sc.nextLine();
		String remove = sc.next();
		System.out.println(withoutString(base, remove));
	}
	public static String withoutString(String base, String remove) {
		base = base.replaceAll(remove, "");
		return base;
	}
}
