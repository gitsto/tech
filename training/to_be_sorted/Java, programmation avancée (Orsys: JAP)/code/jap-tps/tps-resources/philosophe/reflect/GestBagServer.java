package philosophe.reflect;

import java.io.IOException;

import philosophe.thread2.actif.GestBagActif;


public class GestBagServer {
	public static void main(String[] args) {
		final int nbPhil = 10;
   		final int bPort = Integer.parseInt(args[0]);
   		int nbS = args.length > 1 ? Integer.parseInt(args[1]) : 1;
   		try {
			for (int i = 0; i < nbS; i++) {
				final String nP = "" + (bPort + i);
				JAPGenericSkel srv = new JAPGenericSkel(new GestBagActif(nbPhil,"servant associe au port " + nP),nP);
				srv.start();			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
