package syntaxJ5;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestPeinture1 {

	public static void main(String[] args) {
		Peinture peint1 = new Peinture(Couleur.BLEU);
		Peinture peint2 = new Peinture(Couleur.BLEU);
		try {
			Socket socket = new Socket("localhost", 6667);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(peint1);
			out.writeObject(peint2);
		} catch (UnknownHostException e) { e.printStackTrace();
		} catch (IOException e) { e.printStackTrace(); }
	}

}
