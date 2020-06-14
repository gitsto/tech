package fr.jurbert.formation.orsys.jap.tp6.baguette;

public class LocalBaguettesService implements IBaguettesService {

	private final Baguette[] baguettes;

	public LocalBaguettesService(Baguette[] baguettes) {
		if (baguettes == null) {
			throw new IllegalArgumentException("\'baguettes\' parameter cannot be null");
		}

		this.baguettes = baguettes;
	}

	public synchronized boolean getBaguettes(int bag1, int bag2) {
//		int minIndex = Math.min(bag1, bag2);
//		int maxIndex = Math.max(bag1, bag2);
//
//		baguettes[minIndex].get();
//		baguettes[maxIndex].get();
		
		while(baguettes[bag1].isUsed() ||
				baguettes[bag2].isUsed()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		baguettes[bag1].setUsed(true);
		baguettes[bag2].setUsed(true);
		
		return true;
	}

	public synchronized void rendBaguettes(int bag1, int bag2) {
//		baguettes[bag1].leave();
//		baguettes[bag2].leave();
		
		baguettes[bag1].setUsed(false);
		baguettes[bag2].setUsed(false);

		notifyAll();
	}
	
	@Override
	public String toString() {
		return "local service";
	}

}
