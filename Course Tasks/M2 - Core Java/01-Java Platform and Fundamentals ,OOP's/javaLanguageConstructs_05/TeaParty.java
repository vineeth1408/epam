package javaLanguageConstructs_05;
import java.util.Scanner;

public class TeaParty {
	static int tea,candy;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		int tea = sc.nextInt();
		int candy = sc.nextInt();
		System.out.println(teaParty(tea, candy));
	}
	private static int teaParty(int tea, int candy) {
		  int good = 0;
		  if (isTeaAndCandyMore())
		    good = 1;
		  if (tea >= (candy + candy) || candy >= (tea + tea) )
		    good = 2;
		  if (tea <5 || candy <5)
		    good = 0;
		  return good;
		}
	private static boolean isTeaAndCandyMore() {
		return (tea >= 5 && candy >= 5);
	}
}
