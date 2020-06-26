package fr.jurbert.formation.orsys.jap.tp6.philo;

public class DiscussTask extends TachePhilosophe {
	
	private static Object speakAuthorization = new Object();
	
	private static boolean canSpeak = true;

	public DiscussTask(IPhilosophe philosophe) {
		super(philosophe, "discuss");
	}
	
	/**
	 * Warning : blocking until speak authorization
	 */
	private void acquireSpeakAuthorization() {
		synchronized (speakAuthorization) {
			while(!canSpeak) {
				try {
					speakAuthorization.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			canSpeak = false;
		}
	}
	
	private void releaseSpeakAuthorization() {
		synchronized (speakAuthorization) {
			canSpeak = true;
			speakAuthorization.notifyAll();
		}
	}
	
	@Override
	protected void beginTask() {
		IPhilosophe philosophe = getPhilosophe();
		
		// Authorization to speak
		acquireSpeakAuthorization();
		
		// Discuss
		philosophe.setDiscussing(true);
		super.beginTask();
	}
	
	@Override
	protected void endTask() {
		IPhilosophe philosophe = getPhilosophe();
		
		// Authorization to speak
		releaseSpeakAuthorization();
		
		// Think
		philosophe.setDiscussing(false);
		super.endTask();
	}
	
	@Override
	protected boolean canExecuteTask() {
		// Only discuss if philosophe is not autist
		return !getPhilosophe().isAutist();
	}

}
