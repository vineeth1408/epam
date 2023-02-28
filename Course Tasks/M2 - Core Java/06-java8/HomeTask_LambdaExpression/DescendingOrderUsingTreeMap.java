package Throughinterfaces;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

interface DescendingOrderTreeMap {
	
	public void withLambdaExpression();
	public void withoutLambdaExpression();
}
class DescendingOrderUsingTreeMap implements DescendingOrderTreeMap {

	public static void main(String[] args) {
		
		DescendingOrderUsingTreeMap descendingOrderUsingTreeMap = new DescendingOrderUsingTreeMap();
		descendingOrderUsingTreeMap.withLambdaExpression();
		descendingOrderUsingTreeMap.withoutLambdaExpression();
	}
	@Override
	public void withoutLambdaExpression() {
		
		Map<Integer, Integer> setOfIntegers = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer num1, Integer num2) {
				return (num1 > num2) ? -1 : (num1 < num2) ? 1 : 0;
			}
		});
		setOfIntegers.put(1, 10);
		setOfIntegers.put(2, 20);
		setOfIntegers.put(3, 30);
		setOfIntegers.put(4, 40);
		setOfIntegers.put(5, 50);
		
		
		System.out.println(setOfIntegers);
	}
	@Override
	public void withLambdaExpression() {
		
		Map<Integer, Integer> setOfIntegers = new TreeMap<Integer, Integer> (
			(num1, num2)->{ return -num1.compareTo(num2);}
		);
		
		setOfIntegers.put(1, 10);
		setOfIntegers.put(2, 20);
		setOfIntegers.put(3, 30);
		setOfIntegers.put(4, 40);
		setOfIntegers.put(5, 50);
		
		for(Map.Entry<Integer, Integer> m : setOfIntegers.entrySet()) {
			System.out.println("Key ="+m.getKey()+" value ="+m.getValue());
		}
		
	}
}