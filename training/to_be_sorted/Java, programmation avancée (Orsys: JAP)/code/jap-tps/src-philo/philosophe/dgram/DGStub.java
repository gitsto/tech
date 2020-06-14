package philosophe.dgram;

import philosophe.api.IGestBaguettes;

import java.net.*;

public class DGStub implements IGestBaguettes {
	DatagramPacket acqPacket;
	DatagramPacket relPacket;
	DatagramPacket dcxPacket;
	DatagramPacket repPacket;

	DatagramSocket sck;
	byte[] acqBuf = {(byte) DGSynchroCtes.ACQ, (byte) - 1, (byte) -1};
	byte[] relBuf = {(byte) DGSynchroCtes.REL, (byte) - 1, (byte) -1 };
	byte[] repBuf = new byte[1];

	public DGStub(String aStr, String pStr) {
		try {
			InetAddress add = InetAddress.getByName(aStr);
			int port = Integer.parseInt(pStr, 10);
			acqPacket = new DatagramPacket(acqBuf, 3, add, port);
			relPacket = new DatagramPacket(relBuf, 3, add, port);
			repPacket = new DatagramPacket(repBuf, 1);
			sck = new DatagramSocket();
		} catch (UnknownHostException e) {
			System.err.println("adresse serveur inconnue");
			return;
		} catch (SocketException e) {
			System.err.println("erreur a la creation de la socket");
			return;
		} catch (IllegalArgumentException e) {
			System.err.println("parametres packet illegaux : " + aStr + " " + pStr);
		}
	}

	public boolean acqBaguettes(int b1, int b2) throws IllegalArgumentException {
		try {
			acqBuf[1] = (byte) b1;
			acqBuf[2] = (byte) b2;
			sck.send(acqPacket);
			sck.receive(repPacket);
			return (repBuf[0] == DGSynchroCtes.OK);
		} catch (Exception e) {
			System.err.println("erreur dans le stub " + e);
		}
		return false;
	}

	public void libBaguettes(int b1, int b2)  throws IllegalArgumentException {
		try {
			relBuf[1] = (byte) b1;
			relBuf[2] = (byte) b2;
			sck.send(relPacket);
			sck.receive(repPacket);
		} catch (Exception e) {
			System.err.println("erreur dans le stub " + e);
		}
	}
}
