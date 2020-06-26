package classLoader.annotated;

import classLoader.annotations.Instrument;

public class AnnotatedHello {
	public void sayHello1() {
		//System.out.println("execution de sayHello1");
	}
	
	@Instrument
	public void sayHello2() {
		//System.out.println("execution de sayHello2");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		AnnotatedHello aH = new AnnotatedHello();
		for (int i = 0; i < 10; i++) {
			aH.sayHello1();
			aH.sayHello2();			
		}
	}

}
