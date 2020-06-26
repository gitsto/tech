package philosophe.rmi.refactor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Future;

import philosophe.base.Philosophe;
import philosophe.gui.PhiloGUI1;

public class PhiloActif extends Philosophe {

    private int baguette1;
    private int baguette2;
    private PhiloGUI1 gui;
 
    private Thread manger;
    private Thread reflechir;
    
    private ExecutorService serveurs;
    private int quantite;
    private IRMIGestBaguettes gestionnaire;
    
    public PhiloActif(String nm, int nu, long dR, long dM, ExecutorService srv, IRMIGestBaguettes s, int b1, int b2) {
    	super(nm, nu, dR, dM);
    	gui = new PhiloGUI1();
    	gui.initialiser(nu);
    	
    	serveurs = srv;
    	gestionnaire=s;
    	baguette1 = b1;
    	baguette2 = b2;
    	
    	reflechir = new Reflechir();
    	manger = new Manger();
    }
    
    public PhiloActif(int nu, ExecutorService srv, IRMIGestBaguettes s, int b1, int b2) { this("philo" + nu, nu, 1000, 1000, srv, s, b1, b2); }

    public void start() {
    	reflechir.start();
    	manger.start();
    }
    
    private class Manger extends Thread {
    	public void run() {
    		try {
    			for (int i = 0; i < getNBROUND(); i++) {
    				Future<Integer> servi = null;
				
    				if (quantite == 0){
    					gui.commander(getNumero());
    					servi = serveurs.submit(new Callable<Integer>() {
    						public Integer call() throws Exception {
    							Thread.sleep((long) (Math.random() * 5000));	
    							return 5;
    						}
    					});
    				}

    				gui.nePasManger(getNumero());
    				Thread.sleep((long) (getdManger() * Math.random()));
				
    				if (servi != null) {
    					if (!servi.isDone()) gui.commander(getNumero());
    					quantite = servi.get();
    				}
				
    				while (!(gestionnaire.acqBaguettes(baguette1, baguette2))) {
    					gui.attendre(getNumero());
    					Thread.sleep((long) 200);
    				}

    				gui.manger(getNumero());
    				quantite--;
    				Thread.sleep((long) (getdManger() * Math.random()));
    				gestionnaire.libBaguettes(baguette1, baguette2);

    			}
    		} catch (Exception ex) {
    			System.out.println("probleme d'execution dans le philo " + getNumero());
    			ex.printStackTrace();
    		}
    	}
    }
	
	private class Reflechir extends Thread {
	    	
		public void run() {    		
	    	for (int i = 0; i < getNBROUND(); i++) {
	    		gui.discuter(getNumero());
	    	   	try {
	    			Thread.sleep((int) (getdReflechir() * Math.random()));
	    		} catch (InterruptedException e) { e.printStackTrace(); }
					
	    		gui.reflechir(getNumero());
	    	   	try {
	    			Thread.sleep((int) (getdReflechir() * Math.random()));
	    		} catch (InterruptedException e) { e.printStackTrace(); };
	    	}
	    	try {
				manger.join();
			} catch (InterruptedException e) { e.printStackTrace(); }
				
	    	gui.terminer(getNumero());
	    }
	}
}
