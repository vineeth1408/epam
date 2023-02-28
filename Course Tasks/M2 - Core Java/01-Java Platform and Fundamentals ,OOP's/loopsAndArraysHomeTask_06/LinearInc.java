package loopsAndArraysHomeTask_06;

public class LinearInc {

	public static void main(String[] args) {
		int a[] = {1,2,4,4,6};
		int b[] = {2,4};
		System.out.println(methodA(a,b));
		System.out.println(methodB(a,b));
	}
	
	private static boolean methodA(int[] outer, int[] inner) {		
		 int count = 0,j = 0; 
		 for (int i = 0; i < outer.length && j < inner.length; i++){
			 if (outer[i] == inner[j]) {
				 count++;
				 j++;
			 }
		 }
		 if (count == inner.length)
			 return true;
		 else
			 return false;
	}
	
	private static boolean methodB(int[] outer, int[] inner) {	
		  int count = 0;
		  for (int i = 0; i < inner.length; i++) {
			  for (int j = 0; j < outer.length; j++) {
				  if (inner[i] == outer[j]){
					  count++;
					  break;
				  }
			  }
		  }
		  if (count == inner.length)
				 return true;
			 else
				 return false;
	}
}