package philosophe.async.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.Future;

import philosophe.api.IGestBaguettes1;
import philosophe.api.IPhiloCallback;
import philosophe.gui.PhiloGUI1;

public class PhiloAsync {
    private int NBROUND=100;
    private int numero;
    private int baguette1;
    private int baguette2;
    private PhiloGUI1 gui;
 
    private ExecutorService serveurs;
    private int quantite;
    
    private IGestBaguettes1 gestionnaire;
    private BlockingQueue<Boolean> libres;
    
    private Thread manger;
    private Thread reflechir;
    
    public PhiloAsync(int n, ExecutorService srv, IGestBaguettes1 stub, int b1, int b2) {
    	numero=n;
    	gui = new PhiloGUI1();
    	gui.initialiser(numero);
    	
    	serveurs = srv;
    	gestionnaire=stub;
    	baguette1 = b1;
    	baguette2 = b2;
    	libres = new ArrayBlockingQueue<Boolean>(2);
    	
    	manger = new Manger();
    	reflechir = new Reflechir();
    }
    
    public void start() {
    	manger.start();
    	reflechir.start();
    }
    
    public IPhiloCallback getPhiloCallback() { return (IPhiloCallback) manger; }
    
    public class Manger extends Thread implements IPhiloCallback {

    	public void run() {
    		try {
    			for (int i = 0; i < NBROUND; i++) {
    				Future<Integer> servi = null;
    				if (quantite == 0) {
    					gui.commander(numero);
    					servi = serveurs.submit(new Callable<Integer>() {
    						public Integer call() throws Exception {
    							Thread.sleep((long) (Math.random() * 5000));	
    							return 5;
    						}
    					});
    				}
				
    				gui.reflechir(numero);
    				Thread.sleep((long) (1000 * Math.random()));
				
    				if (servi != null) {
    					if (!servi.isDone()) gui.commander(numero);
    					try {
    						quantite = servi.get();
    					} catch (InterruptedException e) {
    						e.printStackTrace();
    					} catch (ExecutionException e) {
    						e.printStackTrace();
    					}
    				}
				
    				gestionnaire.reqBaguettes(baguette1, baguette2, this);
    				gui.attendre(numero);
    				libres.take();
				
    				gui.manger(numero);
    				quantite--;
    				try {
    					Thread.sleep((long) (1000 * Math.random()));
    				} catch (InterruptedException e) { e.printStackTrace(); }
    				gestionnaire.libBaguettes(baguette1, baguette2);
    				gui.nePasManger(numero);
    			}
    		} catch (Exception ex) {
    			System.out.println("probleme d'execution dans le philo " + numero);
    			ex.printStackTrace();
    		}
    	}
	
    	public void baguettesAllouees() { 
    		try { libres.put(Boolean.TRUE); } catch (InterruptedException e) { e.printStackTrace(); }
    	}
    }
    
    public class Reflechir extends Thread {

    	public void run() { 
    		try { 
       			for (int i = 0; i < NBROUND; i++) {
       				gui.discuter(numero);
       				try {
       					Thread.sleep((long) (1000 * Math.random()));
       				} catch (InterruptedException e) { e.printStackTrace(); }
       				gui.reflechir(numero);
       				try {
       					Thread.sleep((long) (1000 * Math.random()));
       				} catch (InterruptedException e) { e.printStackTrace(); }
       			}
    			manger.join();
    			gui.terminer(numero);
    		} catch (InterruptedException e) {e.printStackTrace();}
		}
    }
}
