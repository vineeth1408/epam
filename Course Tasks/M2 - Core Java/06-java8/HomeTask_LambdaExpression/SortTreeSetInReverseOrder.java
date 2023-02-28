package Throughinterfaces;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

interface SortTree {
	
	public void withOutLambdaExpression();
	public void withLambdaExpression();
}
class SortTreeSetInReverseOrder implements SortTree {
	
	public static void main(String[] args) {
		
		SortTreeSetInReverseOrder sortTreeSetInReverseOrder = new SortTreeSetInReverseOrder();
		sortTreeSetInReverseOrder.withLambdaExpression();
		sortTreeSetInReverseOrder.withOutLambdaExpression();
	}
	@Override
	public void withOutLambdaExpression() {
		
		Set<Integer> setOfIntegers = new TreeSet<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer num1, Integer num2) {
				return -num1.compareTo(num2);
			}
		});
		setOfIntegers.add(3);
		setOfIntegers.add(1);
		setOfIntegers.add(2);
		setOfIntegers.add(10);
		
		System.out.println(setOfIntegers);
	}
	@Override
	public void withLambdaExpression() {
		
		Set<Integer> setOfvalues = new TreeSet<Integer> (
			(num1, num2)->{ return (num1 > num2) ? -1 : (num1 < num2) ? 1 : 0; }
		);
		setOfvalues.add(3);
		setOfvalues.add(1);
		setOfvalues.add(2);
		setOfvalues.add(10);
		
		System.out.println(setOfvalues);
	}
}
