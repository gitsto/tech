package philosophe.dgram;

import philosophe.api.IGestBaguettes;

import java.net.*;

public class DGSkel extends Thread {
	IGestBaguettes sync;
	DatagramSocket sck;

	byte okBuf[] = {(byte) DGSynchroCtes.OK };
	byte noBuf[] = {(byte) DGSynchroCtes.NO };
	
	byte reqBuf[] = new byte[3];
	DatagramPacket reqPacket;

	public DGSkel(IGestBaguettes s, String pStr) {
		sync = s;

		int port = Integer.parseInt(pStr, 10);
		try {
			reqPacket = new DatagramPacket(reqBuf,3);
			sck = new DatagramSocket(port);
		} catch (Exception e) {
			System.out.println("erreur a la creation du serveur DG");
		}
	}

	public void run() {
		System.out.println("serveur Datagram demarre");
		for (;;) {
			try {
				sck.receive(reqPacket);
				int b1 = reqBuf[1];
				int b2 = reqBuf[2];
				int snp = reqPacket.getPort();
				InetAddress add = reqPacket.getAddress();
				DatagramPacket packet;
				
				try {
					if (reqBuf[0] == DGSynchroCtes.ACQ) {
						if (!(sync.acqBaguettes(b1, b2))) {
							packet = new DatagramPacket(noBuf, 1, add, snp);
						} else {
							packet = new DatagramPacket(okBuf, 1, add, snp);
						}
						sck.send(packet);
					} else if (reqBuf[0] == DGSynchroCtes.REL) {
						sync.libBaguettes(b1, b2);
						packet = new DatagramPacket(okBuf, 1, add, snp);
						sck.send(packet);
					}
				} catch (Exception e) {
					sck.send(new DatagramPacket(noBuf, 1, add, snp));
				}
			} catch (Exception e) { e.printStackTrace(); }
		}
	}
}
