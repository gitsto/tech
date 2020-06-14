package syntaxJ5;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestPeinture1J4 {

	public static void main(String[] args) {
		PeintureJ4 peint1 = new PeintureJ4(CouleurJ4.BLEU);
		PeintureJ4 peint2 = new PeintureJ4(CouleurJ4.BLEU);
		try {
			Socket socket = new Socket("localhost", 6668);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(peint1);
			out.writeObject(peint2);
		} catch (UnknownHostException e) { e.printStackTrace();
		} catch (IOException e) { e.printStackTrace(); }
	}

}
