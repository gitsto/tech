package sandbox.exercices_jva.exercice1;

public class Etape2 {

	public static void doWork() {
		System.out.println(Thread.currentThread().getName());
		try {
			Thread.sleep(2000);

			for (int i = 0; i < 50; i++) {
				System.out.println(i);
			}

		} catch (InterruptedException e) {
		}
	}

	Runnable r = () -> doWork();

	public static void main(String[] args) {
		Etape2 name = new Etape2();

		new Thread(name.r, "myThread").start();
	}

}
