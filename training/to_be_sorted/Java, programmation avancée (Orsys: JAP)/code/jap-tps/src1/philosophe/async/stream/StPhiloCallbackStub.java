package philosophe.async.stream;

import philosophe.api.IPhiloCallback;

import java.net.*;
import java.io.*;

public class StPhiloCallbackStub implements IPhiloCallback, Serializable {	
	private static final long serialVersionUID = -373262432973569657L;

	private Socket sck;
	private ObjectOutputStream outS;
	
	private StPhiloCallbackSkel skeleton;
	private boolean estInitialise;
			
	public StPhiloCallbackStub(StPhiloCallbackSkel s) { skeleton = s; }

	public void baguettesAllouees() {
		try {
			if (!estInitialise) {
				InetAddress add = skeleton.getAddress();
				int port = skeleton.getPort();
				sck = new Socket(add, port);
				outS = new ObjectOutputStream(sck.getOutputStream());
				estInitialise = true;
			}

			outS.writeObject(new StRequest("baguettesLibres", -1, -1, null));
		} catch (SocketException e) {
			System.err.println("erreur a la cration de la socket");
			return;
		} catch (IOException e) {
			System.err.println("erreur durant communication reseau");
			return;
		}
	}
}
