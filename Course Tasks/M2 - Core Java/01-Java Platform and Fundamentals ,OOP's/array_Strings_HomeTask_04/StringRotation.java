package array_Strings_HomeTask_04;
import java.util.Scanner;

public class StringRotation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		System.out.println(StringsAreRotational(s1, s2));
	}
	private static boolean StringsAreRotational(String s1, String s2){
		return (s1.length() == s2.length() && (s1 + s1).contains(s2));
	}
}
