package HomeTask_LambdaExpression;

import java.util.function.*;
interface StringRotatation {
	
	String s1 = "ABCD", s2 = "DABC";
	public void withLambdaExpression();
	public void withoutLambdaExpression();
}

class AreStringsRotational implements StringRotatation {
	
	public static void main(String[] args) {
		
		AreStringsRotational areStringsRotational = new AreStringsRotational();
		areStringsRotational.withLambdaExpression();
		areStringsRotational.withoutLambdaExpression();
	}
	
	public void withLambdaExpression() {
		
		BiFunction<String, String, Boolean> f1 = (s1, s2)-> s1.length()==s2.length();
		BiFunction<String, String, Boolean> f2 = (s1, s2)-> (s1+s1).contains(s2);
		
		System.out.println(f1.apply(s1, s2) && f2.apply(s1, s2));
	}
	
	public void withoutLambdaExpression() {
		
		boolean result = s1.length() == s2.length() && (s1+s1).contains(s2);
		System.out.println(result);
	}
}
