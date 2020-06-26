package threads;

public class PThreadPool {
    private boolean[] inUse;
    private PThread[] pool;

    public PThreadPool(int size) {
	inUse = new boolean[size]; pool = new PThread[size];
	for (int i=0; i<size; i++) {
	    pool[i] = new PThread(this,i);
	    pool[i].start();
	}
    }

    public synchronized void loadAndRun(Runnable _run) {
	for (int i = 0; i< pool.length; i++) {
	    if (inUse[i]) continue;
	    synchronized(pool[i]) {
		inUse[i] = true;
		pool[i].load(_run);
		pool[i].notify();
		return;
	    }
	}
    }

    public void notifyEnd(int i) {
	inUse[i] = false;
    }
}
