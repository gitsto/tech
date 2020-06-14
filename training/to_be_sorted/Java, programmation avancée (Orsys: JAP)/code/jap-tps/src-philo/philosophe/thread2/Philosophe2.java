package philosophe.thread2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import philosophe.base.Philosophe;
import philosophe.gui.PhiloGUI1;

public class Philosophe2 extends Philosophe {
    private PhiloGUI1 gui;
    
    private Thread manger;
    private Thread reflechir;
    
    private ExecutorService serveurs;
    private int quantite;
    
    public Philosophe2(String nm, int nu, long dR, long dM, ExecutorService srv) {
    	super(nm, nu, dR, dM);
    	gui = new PhiloGUI1();
		gui.initialiser(getNumero());
       	manger = new Thread(new Manger());
    	reflechir = new Thread(new Reflechir());
       	serveurs = srv;
    }
    
    public Philosophe2(int nu, ExecutorService srv) { this("philo" + nu, nu, 1000, 1000, srv); }
    
    public void start() {
    	reflechir.start();
    	manger.start();
    }
    
    private class Manger implements Runnable {

    	public void run() {
    		try {
    			for (int i = 0; i < getNBROUND(); i++) {
    				Future<Integer> servi = null;
	    		
    				if (quantite == 0){
    					servi = serveurs.submit(new Callable<Integer>() {
    						public Integer call() throws Exception {
    							Thread.sleep((long) (Math.random() * 5000));	
    							return 5;
    						}
    					});
    				}
  
    				gui.nePasManger(getNumero());
    				Thread.sleep((long) (getdManger() * Math.random()));
				
    				if (servi != null){
    					if (!servi.isDone()) gui.commander(getNumero());
    					quantite = servi.get();
    				}
				
    				gui.manger(getNumero());
    				quantite--;
    				Thread.sleep((long) (getdManger() * Math.random()));
    			}
    		} catch (Exception ex) {
    			System.out.println("probleme d'execution dans le philo " + getNumero());
    			ex.printStackTrace();
    		}
    	}
    }
	
    private class Reflechir implements Runnable {
    	
       	public void run() {    		
    		for (int i = 0; i < getNBROUND(); i++) {
    			gui.discuter(getNumero());
    	   		try {
    				Thread.sleep((int) (getdReflechir() * Math.random()));
    			} catch (InterruptedException e) { e.printStackTrace(); }
				
    			gui.reflechir(getNumero());
    	   		try {
    				Thread.sleep((int) (getdReflechir()));
    			} catch (InterruptedException e) { e.printStackTrace(); };
    		}
    		try {
				manger.join();
			} catch (InterruptedException e) { e.printStackTrace(); }
			
    		gui.terminer(getNumero());
    	}
    }
}
