package philosophe.stream;

import philosophe.thread2.passif.base.GestBagPassif;

//import thread.philoActif.GestBagActif;

public class GestBagServer {
	public static void main(String[] args) {
		final int nbPhil = 5;					//Integer.parseInt(args[1], 10); 		
   		final int bPort = Integer.parseInt(args[0]);
   		int nbS = args.length > 1 ? Integer.parseInt(args[1]) : 1;
   		for (int i = 0; i < nbS; i++) {
   			final String nP = "" + (bPort + i);
   			//StSkel srv = new StSkel(new GestBagActif(nbPhil,"servant associe au port " + nP),nP);
   			StSkel srv = new StSkel(new GestBagPassif(nbPhil,"servant associe au port " + nP),nP);
   			srv.start();
  		} 				
	}
}
