package threads;

public class TestThreadPool {

	public static void main(String[] args) {
		PThreadPool pool = new PThreadPool(5);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				HelloThread th = new HelloThread();
				pool.loadAndRun(th);
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ex) {
				System.out.println("PB dans le main de TestThreadPool");
			}
		}
	}
}

class HelloThread implements Runnable {
	private static int NUMSEQ = 0;

	public void run() {
		NUMSEQ++;
		System.out.println("execution de HelloThread " + NUMSEQ);
	}
}
