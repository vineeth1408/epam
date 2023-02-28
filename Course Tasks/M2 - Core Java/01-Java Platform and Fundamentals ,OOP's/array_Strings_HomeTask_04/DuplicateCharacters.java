package array_Strings_HomeTask_04;
import java.util.Scanner;

public class DuplicateCharacters {
	
	static int CharactersCount[] = new int[123]; //last ascii value is 122
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter String: ");
		String SampleString = sc.next();
		
		char CharacterArray[] = SampleString.toCharArray();
		PrintDuplicateCharacters(CharacterArray);
	}
	private static void PrintDuplicateCharacters(char CharacterArray[]) {
		
		countTheCharacters(CharacterArray);
		for (int i = 0; i <= 122; i++) {
			if (CharactersCount[i] > 1){
				System.out.println((char)(i)+" count: "+CharactersCount[i]);
			}
		}
	}
	private static void countTheCharacters(char CharacterArray[]) {
		
		for (int i = 0; i < CharacterArray.length; i++){
			CharactersCount[CharacterArray[i]]++;
		}
	}
}
