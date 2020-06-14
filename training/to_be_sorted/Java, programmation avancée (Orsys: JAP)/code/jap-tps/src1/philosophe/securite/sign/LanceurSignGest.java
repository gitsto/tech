package philosophe.securite.sign;

import philosophe.thread2.actif.GestBagActif;

public class LanceurSignGest {
	public static void main(String[] args) {
		int nbPhil = 10;						//Integer.parseInt(args[1], 10);
		SignSkel srv = new SignSkel(new GestBagActif(nbPhil), args[0], "fho");
		srv.service();
	}
}
