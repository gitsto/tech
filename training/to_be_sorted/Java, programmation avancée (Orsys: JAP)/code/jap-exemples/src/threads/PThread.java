package threads;

public class PThread extends Thread {
	private Runnable runnable;
	private PThreadPool pool;
	private int index;

	public PThread(PThreadPool p, int i) {
		pool = p;
		index = i;
	}

	public synchronized void load(Runnable r) {
		runnable = r;
	}

	public void run() {
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException ex) {
			}
		}
		while (true) {
			runnable.run();
			pool.notifyEnd(index);
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException ex) {
				}
			}
		}
	}
}
