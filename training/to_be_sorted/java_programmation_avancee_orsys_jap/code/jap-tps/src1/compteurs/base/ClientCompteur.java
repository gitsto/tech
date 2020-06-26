package compteurs.base;

public class ClientCompteur {
	private ICompteur compteur;
	
	public ClientCompteur(ICompteur cpt) {
		compteur = cpt;
		new Thread(new Task()).start();
	}

	private class Task implements Runnable {
		public void run() {
			try {
				for (int i = 0; i < 1000; i++) {
					compteur.incrementer(i);
					Thread.sleep((long) (500 *Math.random()));
					System.out.println("la valeur du compteur est : " + compteur.getValeur());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
