package philosophe.async.stream;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import philosophe.async.thread.GestBagAsync;

public class GestBagServer {
	public static void main(String[] args) {
		final int nbPhil = 10;					//Integer.parseInt(args[1], 10); 		
   		final int bPort = Integer.parseInt(args[0]);
   		int nbS = args.length > 1 ? Integer.parseInt(args[1]) : 1;
   		ExecutorService exec = Executors.newCachedThreadPool();
   		for (int i = 0; i < nbS; i++) {
 
   			final String nP = "" + (bPort + i);
   			exec.execute(new Runnable() {
   				public void run() {
   					StSkel srv = new StSkel(new GestBagAsync(nbPhil,"servant associe au port " + nP),nP);
   					srv.service();
  				} 				
   			});
   		}
	}
}
