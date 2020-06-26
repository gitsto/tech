package philosophe.thread1;

import philosophe.base.Philosophe;
import philosophe.gui.PhiloGUI1;

public class Philosophe1 extends Philosophe {
    private PhiloGUI1 gui;
   
    private Thread manger;
    private Thread reflechir;
 
    public Philosophe1(String nm, int nu, long dR, long dM) { 
    	super(nm, nu, dR, dM);
    	gui = new PhiloGUI1();
		gui.initialiser(nu);
       	manger = new Thread(new Manger());
    	reflechir = new Thread(new Reflechir());
    }
    
    public Philosophe1(int nu) { this("philo" + nu, nu, 1000, 1000); }
    
    public void start() {
    	manger.start();
    	reflechir.start();
    }
    
    private class Manger implements Runnable {

    	public void run() {
    		gui.initialiser(getNumero());
    		
    		for (int i = 0; i < getNBROUND(); i++) {
    			gui.nePasManger(getNumero());
    			try {
					Thread.sleep((long) (getdManger() * Math.random()));
				} catch (InterruptedException e) { e.printStackTrace(); }
				
    			gui.manger(getNumero());
    			try {
					Thread.sleep((long) (getdManger() * Math.random()));
				} catch (InterruptedException e) { e.printStackTrace(); }
    		}
    	}
    }
    
    private class Reflechir implements Runnable {
    	
       	public void run() {    		
    		for (int i = 0; i < getNBROUND(); i++) {
    			gui.discuter(getNumero());
    	   		try {
    				Thread.sleep((long) (getdReflechir() * Math.random()));
    			} catch (InterruptedException e) { e.printStackTrace(); }
				
    			gui.reflechir(getNumero());
    	   		try {
    				Thread.sleep((long) (getdReflechir() * Math.random()));
    			} catch (InterruptedException e) { e.printStackTrace(); };
    		}
    		try {
				manger.join();
			} catch (InterruptedException e) { e.printStackTrace(); }
			
    		gui.terminer(getNumero());
    	}
    }
}
