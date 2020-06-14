package compteurs.rmi;

public class ClientCompteur {
	private IRMICompteur compteur;
	
	public ClientCompteur(IRMICompteur cpt) {
		compteur = cpt;
		new Thread(new Task()).start();
	}

	private class Task implements Runnable {
		public void run() {
			try {
				for (int i = 0; i < 1000; i++) {
					compteur.incrementer(i);
					Thread.sleep(500);
					System.out.println("la valeur du compteur est : " + compteur.getValeur());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
