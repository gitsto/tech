package fr.orsys.jva.exercice1;

public class Etape2 {

	public static void doWork() {
		String name	= Thread.currentThread().getName();
		for (int i = 0; i < 50; i++) {
			System.out.println(name);
			try {
				long millis	= Math.round(Math.random() * 50);
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1	= new Thread(Etape2::doWork);
		t1.start();
		Thread t2	= new Thread(() -> doWork());
		t2.start();
		Thread t3	= new Thread(new Runnable() {
			public void run() {
				doWork();
			}
		});
		t3.start();
	}
}
