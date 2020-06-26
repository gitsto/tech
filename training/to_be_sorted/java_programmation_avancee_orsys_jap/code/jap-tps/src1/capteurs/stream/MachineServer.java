package capteurs.stream;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import capteurs.base.Machine;

//import thread.philoActif.GestBagActif;

public class MachineServer {
	public static void main(String[] args) {		
   		final int bPort = Integer.parseInt(args[0]);
   		int nbS = args.length > 1 ? Integer.parseInt(args[1]) : 1;
   		ExecutorService exec = Executors.newCachedThreadPool();
   		for (int i = 0; i < nbS; i++) {
 
   			final String nP = "" + (bPort + i);
   			exec.execute(new Runnable() {
   				public void run() {
   					//StSkel srv = new StSkel(new GestBagActif(nbPhil,"servant associe au port " + nP),nP);
   					StSkel srv = new StSkel(new Machine(10, 20),nP);
   					srv.start();
  				} 				
   			});
   		}
	}
}
