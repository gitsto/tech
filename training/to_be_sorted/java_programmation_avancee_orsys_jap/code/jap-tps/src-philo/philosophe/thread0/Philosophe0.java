package philosophe.thread0;

import philosophe.gui.PhiloGUI0;

public class Philosophe0 extends Thread {
    private int numero;
    private String nom;
    private PhiloGUI0 gui;
    
    private int NBROUND=100;
    private long dReflechir;
    private long dManger;
    
    public Philosophe0(String nm, int nu, long dR, long dM) {
    	nom = nm;
    	numero=nu;
    	dReflechir = dR;
    	dManger = dM;
    	gui = new PhiloGUI0();
    }
    
    public Philosophe0(int nu) { this("philo" + nu, nu, 1000, 1000); }
    
    public void run() { new Task().run(); }
    
	private class Task implements Runnable {
		public void run() {
			try {
				gui.initialiser(numero);
				for (int i = 0; i < NBROUND; i++) {
					gui.reflechir(numero);
					Thread.sleep((long) (dReflechir * Math.random()));
					gui.manger(numero);
					Thread.sleep((long) (dManger * Math.random()));
				}
				gui.terminer(numero);
			} catch (Exception ex) {
				System.out.println("probleme d'execution dans le philo "+ nom);
				ex.printStackTrace();
			}
		}
	}
}
