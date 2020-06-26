package capteurs.base;


public class ClientMachine {
	private IMachine machine;
	
	public ClientMachine(IMachine cpt) {
		machine = cpt;
		new Thread(new Task()).start();
	}

	private class Task implements Runnable {
		public void run() {
			try {
				for (int i = 0; i < 1000; i++) {
					System.out.println("la temperature courante est : " + machine.getTemperature());
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
