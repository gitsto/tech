package syntaxJ5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestPeinture2J4 {

	public static void main(String[] args) {
		try {
			ServerSocket sSocket = new ServerSocket(6668);
			Socket socket = sSocket.accept();
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			PeintureJ4 peint1 = (PeintureJ4) in.readObject();
			PeintureJ4 peint2 = (PeintureJ4) in.readObject();
			if (peint1.getCouleur() == CouleurJ4.BLEU)
				System.out.println("la couleur de peint1 vaut Couleur.BLEU " + peint1.getCouleur());
			if (peint1.getCouleur() == peint2.getCouleur())
				System.out.println("les couleurs de peint1 et peint2 sont identiques et valent " + peint1.getCouleur());
		} catch (UnknownHostException e) { e.printStackTrace();
		} catch (IOException e) { e.printStackTrace();
		} catch (ClassNotFoundException e) { e.printStackTrace();
		}
	}
}