package HomeTask_LambdaExpression;

interface Palindrome {
	
	boolean IsPalindrome(String inputString);
}
interface CheckPalindrome {
	
	String input = "MALAYALAM";
	public void withLambdaExpression();
	public void withoutLambdaExpression();
}

class Stringpalindrome implements CheckPalindrome {
	
	public void withLambdaExpression() {
		
		Palindrome str = (inputString) -> {
			for (int i = 0; i < inputString.length()/2; i++) {
				if (inputString.charAt(i) != inputString.charAt(inputString.length()-i-1)) {
					return false;
			    }
			}
			return true;
		};
		System.out.println(str.IsPalindrome(input));
	}
	public void withoutLambdaExpression() {
		
		boolean result = true;
		
		for (int i = 0; i < input.length()/2; i++) {
			if (input.charAt(i) != input.charAt(input.length()-i-1)) {
				result = false;
			}
		}
		System.out.println(result);
	}
	public static void main(String[] args) {
		
		Stringpalindrome stringpalindrome = new Stringpalindrome();
		stringpalindrome.withLambdaExpression();
		stringpalindrome.withoutLambdaExpression();
	}
}
