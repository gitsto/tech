package archi.orb.naming;

import java.io.IOException;


public class LaunchJAPNameServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread() { public void run() {
		try {
			new JAPNameServer(7777).start();
		} catch (IOException e) {
			e.printStackTrace();
		} }}.start();
	}

}
