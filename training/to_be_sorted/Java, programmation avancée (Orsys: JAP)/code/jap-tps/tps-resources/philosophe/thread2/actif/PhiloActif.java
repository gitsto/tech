package philosophe.thread2.actif;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Future;

import philosophe.api.IGestBaguettes;
import philosophe.gui.PhiloGUI1;

public class PhiloActif {
    private int NBROUND=100;
    private int numero;
    private int baguette1;
    private int baguette2;
    private PhiloGUI1 gui;
 
    private Thread manger;
    private Thread reflechir;
    
    private ExecutorService serveurs;
    private int quantite;
    private IGestBaguettes gestionnaire;
    
    public PhiloActif(int n, ExecutorService srv, IGestBaguettes s, int b1, int b2) {
    	numero=n;
    	gui = new PhiloGUI1();
    	gui.initialiser(numero);
    	
    	serveurs = srv;
    	gestionnaire=s;
    	baguette1 = b1;
    	baguette2 = b2;
    	
    	reflechir = new Reflechir();
    	manger = new Manger();
    }
    
    public void start() {
    	reflechir.start();
    	manger.start();
    }
    
    private class Manger extends Thread {
    	public void run() {
    		try {
    			for (int i = 0; i < NBROUND; i++) {
    				Future<Integer> servi = null;
				
    				if (quantite == 0){
    					gui.commander(numero);
    					servi = serveurs.submit(new Callable<Integer>() {
    						public Integer call() throws Exception {
    							Thread.sleep((long) (Math.random() * 5000));	
    							return 5;
    						}
    					});
    				}

    				gui.nePasManger(numero);
    				Thread.sleep((long) (1000 * Math.random()));
				
    				if (servi != null) {
    					if (!servi.isDone()) gui.commander(numero);
    					quantite = servi.get();
    				}
				
    				while (!(gestionnaire.acqBaguettes(baguette1, baguette2))) {
    					gui.attendre(numero);
    					Thread.sleep((long) 200);
    				}

    				gui.manger(numero);
    				quantite--;
    				Thread.sleep((long) (1000 * Math.random()));
    				gestionnaire.libBaguettes(baguette1, baguette2);

    			}
    		} catch (Exception ex) {
    			System.out.println("probleme d'execution dans le philo " + numero);
    			ex.printStackTrace();
    		}
    	}
    }
	
	private class Reflechir extends Thread {
	    	
		public void run() {    		
	    	for (int i = 0; i < NBROUND; i++) {
	    		gui.discuter(numero);
	    	   	try {
	    			Thread.sleep((int) (1000 * Math.random()));
	    		} catch (InterruptedException e) { e.printStackTrace(); }
					
	    		gui.reflechir(numero);
	    	   	try {
	    			Thread.sleep((int) (1000 * Math.random()));
	    		} catch (InterruptedException e) { e.printStackTrace(); };
	    	}
	    	try {
				manger.join();
			} catch (InterruptedException e) { e.printStackTrace(); }
				
	    	gui.terminer(numero);
	    }
	}
}
