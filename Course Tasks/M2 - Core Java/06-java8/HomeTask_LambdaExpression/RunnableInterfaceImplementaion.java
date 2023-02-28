package HomeTask_LambdaExpression;

interface RunnnableInterface {
	
	 int LIMIT = 100;
	 public void withoutLamdaExpression();
	 public void withtLamdaExpression();
}
class RunnableInterfaceImplementaion implements RunnnableInterface {
	 
	public static void main(String[] args) {
		
		RunnableInterfaceImplementaion runnableInterfaceImplementaion = new RunnableInterfaceImplementaion();
		runnableInterfaceImplementaion.withoutLamdaExpression();
		System.out.println();
		runnableInterfaceImplementaion.withtLamdaExpression();
	}

	public void withoutLamdaExpression() {
		
		Runnable runnable1 = new Runnable() {			
			@Override
			public void run() {
				for (int i = 10; i <= LIMIT; i++) {
					System.out.print(i+" ");
				}
			}
		};
		Thread thread1 = new Thread(runnable1);
		thread1.start();
		
		try {
			thread1.join();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void withtLamdaExpression() {
		
		Runnable runnable2 = () -> {
			for (int i = 10; i <= LIMIT; i++) {
				System.out.print(i+" ");
			}
		};
		Thread thread2 = new Thread(runnable2);
		thread2.start();
		
		try {
			thread2.join();
		}
		catch (Exception e) {
			System.out.println(e);
		}	 
	}
}
