package philosophe.dgram;

import philosophe.thread2.passif.base.GestBagPassif;

//import thread.philoActif.GestBagActif;

public class GestBagServer {
	public static void main(final String[] args) {
		final int nbPhil = 5;
   		
   		final int bPort = Integer.parseInt(args[0]);
   		int nbS = args.length > 1 ? Integer.parseInt(args[1]) : 1;
   		
   		//chaque couple (skeleton + serveur de baguettes) s'execute dans un thread propre ....
   		for (int i = 0; i < nbS; i++) { 		
   			final String nP = "" + (bPort + i);
   		   		//DGSkel srv = new DGSkel(new GestBagActif(nbPhil,"servant associe au port " + nP),nP);
   		   		DGSkel srv = new DGSkel(new GestBagPassif(nbPhil,"servant associe au port " + nP),nP);
   		   		srv.start();
   		} 			
	}
}