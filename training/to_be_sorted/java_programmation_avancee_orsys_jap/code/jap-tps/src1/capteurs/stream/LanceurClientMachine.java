package capteurs.stream;

import capteurs.base.ClientMachine;

public class LanceurClientMachine {
	public static void main(String args[]) {
		new ClientMachine(new StStub(args[0], args[1]));
	}
}
