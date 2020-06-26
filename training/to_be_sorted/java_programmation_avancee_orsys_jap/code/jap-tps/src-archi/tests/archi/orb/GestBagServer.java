package tests.archi.orb;

import java.io.IOException;
import java.net.InetAddress;

import archi.orb.naming.JAPNameService;

import philosophe.api.IGestBaguettes;
import philosophe.thread2.actif.GestBagActif;


public class GestBagServer {
	public static void main(String[] args) {
		final int nbPhil = 10;
   		//final int bPort = Integer.parseInt(args[0]);
   		int nbS = args.length > 0 ? Integer.parseInt(args[0]) : 1;
   		try {
   			JAPNameService sce = new JAPNameService(InetAddress.getLocalHost(), 7777);
			for (int i = 0; i < nbS; i++) {
				IGestBaguettes s = new GestBagActif(nbPhil,"servant numero " + i);
				sce.bind(s, "synchro_baguettes_" + i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
