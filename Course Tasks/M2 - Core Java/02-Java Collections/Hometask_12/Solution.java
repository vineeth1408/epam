package hometask_12;

public class Solution {
	 public static void main(String args[]) {
	        Printer arr = new Printer ();

	        // create arrays of Integer, Double and Character
	        Integer[] integerArray = {1, 2, 3, 4, 5, 6};
	        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
	        Character[] characterArray = {'H', 'E', 'L', 'L', 'O'};
	        
	        System.out.println("Array integerArray contains:");
	        Printer.printArray(integerArray); // pass an Integer array
	        
	        System.out.println("\nArray doubleArray contains:");
	        Printer.printArray(doubleArray); // pass a Double array
	        
	        System.out.println("\nArray characterArray contains:");
	        Printer.printArray(characterArray); // pass a Character array

	    }
	}