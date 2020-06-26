package philosophe.async.dgram1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import philosophe.api.IPhiloCallback;

public class DGPhiloCallbackStub implements IPhiloCallback {
	private byte[] buffer;
	private int port;
	private InetAddress adresse;
	private DatagramSocket socket;

	public DGPhiloCallbackStub(long id,int pt, InetAddress ad) {
		try {
			port = pt;
			adresse = ad;
			socket = new DatagramSocket();
			buffer = new byte[9];
			preparePacket(buffer, id);
		} catch (SocketException e) { e.printStackTrace(); }
	}
	
	public void baguettesAllouees() {
		buffer[8] = (byte) SynchroCtes1.NTF;
		try {
			socket.send(new DatagramPacket(buffer, 9, adresse, port));
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	private void preparePacket(byte[] buf, long rId) {
		buf[0] = (byte) ((rId >>> 56) & 0XFF);
		buf[1] = (byte) ((rId >>> 48) & 0XFF);
		buf[2] = (byte) ((rId >>> 40) & 0XFF);
		buf[2] = (byte) ((rId >>> 32) & 0XFF);		
		buf[4] = (byte) ((rId >>> 24) & 0XFF);
		buf[5] = (byte) ((rId >>> 16) & 0XFF);
		buf[6] = (byte) ((rId >>> 8) & 0XFF);
		buf[7] = (byte) (rId & 0XFF);
	}
}
