package capteurs.rmi;

public class RMIClientMachine {
	private IRMIMachine machine;
	
	public RMIClientMachine(IRMIMachine cpt) {
		machine = cpt;
		new Thread(new Task()).start();
	}

	private class Task implements Runnable {
		public void run() {
			try {
				for (int i = 0; i < 1000; i++) {
					System.out.println("la temperature courante est : " + machine.getTemperature());
					Thread.sleep(2000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
