package Throughinterfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

interface ListOfNumbers {
	
	int LIMIT = 100;
	public void withOutLambdaExpression(List<Integer> marks);
	public void withLambdaExpression(List<Integer> marks);
}
public class ListOfNumbersInReverseOrder implements ListOfNumbers {

	public static void main(String[] args) {
		
		List<Integer> listOfMarks = getMarks();
		
		ListOfNumbersInReverseOrder listOfNumbersInReverseOrder = new ListOfNumbersInReverseOrder();
		listOfNumbersInReverseOrder.withLambdaExpression(listOfMarks);
		System.out.println();
		listOfNumbersInReverseOrder.withOutLambdaExpression(listOfMarks);
	}

	public static List<Integer> getMarks() {
	
		List<Integer> marks = new ArrayList<>();
		
		Random rand = new Random(); 
		for (int i = 0; i < LIMIT; i++) {
			marks.add(rand.nextInt(35, 100)); //generating random numbers between 35-100
		}
		return marks;
	}
	@Override
	public void withOutLambdaExpression(List<Integer> marks) {

		Collections.sort(marks, new MyMarksComparator());
		for(Integer i : marks) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	@Override
	public void withLambdaExpression(List<Integer> marks) {

		Collections.sort(marks, (m1, m2) -> (m1 > m2) ? -1: (m1 < m2) ? 1 :0 );
		
		for(Integer i : marks) {
			System.out.print(i+" ");
		}
	}	
}


class MyMarksComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer m1, Integer m2) {
		return (m1 > m2) ? -1 : (m1 < m2) ? 1 : 0;
	}
}
