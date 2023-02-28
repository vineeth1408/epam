package HomeTask_LambdaExpression;

import java.util.Arrays;

interface SecondBiggest {
	int secondBiggest();
}
public interface SecondBiggestElement {
		
		public static void main(String[] args) {
			
			int array[] = {1,2,3,4,5};
			SecondBiggestElement.withLambdaExpression(array);
			SecondBiggestElement.withoutLambdaExpression(array);
		}
		
		public static void withLambdaExpression(int array[]) {
			
			SecondBiggest secondbig = () -> {
				int max, secondMax;
				max=secondMax=Integer.MIN_VALUE;
			
				for (int i = 0; i < array.length; i++) {
					if (array[i] > max) {
						secondMax = max;
						max = array[i];
					}
					else if (array[i] > secondMax && array[i] != max) {
						secondMax = array[i];
					}
				}
				return secondMax;	
			};
			System.out.println(secondbig.secondBiggest());
		}
		
		public static void withoutLambdaExpression(int array[]) {
		
			int result=0;
			Arrays.sort(array);
		
			for (int i = array.length-2; i >= 0; i--) {
				if (array[i] != array[array.length-1]) {
					result = array[i];
					break;
				}
			}
			System.out.println(result);
		}
}
