package fr.jurbert.formation.orsys.jap.tp6.philo;

import fr.jurbert.formation.orsys.jap.Utils;

public class TachePhilosophe implements Runnable {

	private static final int MIN_DELAI = 1000;

	private static final int MAX_DELAI = 2000;

	private final IPhilosophe philosophe;

	private final String nomActivite;

	public TachePhilosophe(IPhilosophe philosophe, String nomActivite) {
		if (philosophe == null) {
			throw new IllegalArgumentException("The philosophe cannot be null");
		}

		this.philosophe = philosophe;
		this.nomActivite = nomActivite;
	}

	public final IPhilosophe getPhilosophe() {
		return philosophe;
	}

	public final String getPhilosopheName() {
		if (philosophe != null) {
			return philosophe.getPhilosopheName();
		} else {
			return "<undefined>";
		}
	}

	public final int getPhilosophePosition() {
		if (philosophe != null) {
			return philosophe.getPosition();
		} else {
			return -1;
		}
	}

	private void appendDebutMessage(StringBuffer message) {
		message.append("Philosophe ").append(getPhilosopheName());
		message.append(" (").append(getPhilosophePosition()).append(") ");
	}

	private String formatMessage(boolean debut) {
		StringBuffer message = new StringBuffer();
		appendDebutMessage(message);
		if (debut) {
			message.append("commence à");
		} else {
			message.append("arrête de");
		}
		message.append(" ");
		message.append(nomActivite);
		message.append(".");

		return message.toString();
	}

	protected void beginTask() {
		System.out.println(formatMessage(true));
	}

	protected void endTask() {
		System.out.println(formatMessage(false));
	}

	public final void run() {
		beginTask();

		Utils.sleep(Utils.getRandomDelay(MIN_DELAI, MAX_DELAI));

		endTask();
	}
	
	protected boolean canExecuteTask() {
		return true;
	}
	
	protected boolean canFinish() {
		return true;
	}

}