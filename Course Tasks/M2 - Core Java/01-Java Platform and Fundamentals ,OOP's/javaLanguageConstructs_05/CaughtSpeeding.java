package javaLanguageConstructs_05;
import java.util.Scanner;

public class CaughtSpeeding {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int speed = sc.nextInt();
		boolean Birthday = sc.nextBoolean();
		System.out.println(caughtSpeeding(speed,Birthday));
	}
	
	private static int caughtSpeeding(int speed, boolean isBirthday) {
		
		  int result = 0;
		  if (isBirthday) {
		    result = SppedOnBirthday(speed);
		  }
		  else {
			  result = SppedOnOtherDays(speed);
		  }		  
	return result;
	}
	private static int SppedOnBirthday(int speed) {
		
		int result = 0;
		if (speed <= 65)
	        result = 0;
	    else if (speed >= 65 && speed <= 85)
	      result = 1;
	    else if (speed >= 90)
	      result = 2;
	return result;
	}
	private static int SppedOnOtherDays(int speed) {
		
		int result = 0;
		if (speed <= 60)
	        result = 0;
	    else if (speed >= 61 && speed <= 80)
	      result = 1;
	    else if (speed >= 81)
	      result = 2;
	return result;
	}
}
