package philosophe.securite.sign;

import philosophe.api.IGestBaguettes;

import java.net.*;
import java.io.*;
import java.security.Signature;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class SignSkel {
	ServerSocket cnx;
	IGestBaguettes sync;
	PrivateKey privKey;
	CertificateFactory certFactory;
	byte[] certBuffer;

	public SignSkel(IGestBaguettes s, String pStr, String alias) {
		sync = s;
		try {
			int port = Integer.parseInt(pStr, 10);
			cnx = new ServerSocket(port);

			String ksName = "securite-resources\\jav5keystore";
			String ksPasswdString = "jav5keystore";
			char[] ksPasswd = ksPasswdString.toCharArray();
			FileInputStream fIn = new FileInputStream(ksName);
			KeyStore ks = KeyStore.getInstance("JKS");

			ks.load(fIn, ksPasswd);
			privKey = (PrivateKey) ks.getKey(alias, ksPasswd);
			Certificate cert = (Certificate) ks.getCertificate(alias);
			certBuffer = cert.getEncoded();
			certFactory = CertificateFactory.getInstance("X.509");
		} catch (Exception e) {
		}
	}

	public void service() {
		try {
			for (;;) {
				Socket sck = cnx.accept();
				System.out.println("connexion acceptee");
				new PhiloSynk(sck).start();
			}
		} catch (Exception e) {
		}
	}

	class PhiloSynk extends Thread {
		Socket sck;

		PhiloSynk(Socket s) {
			sck = s;
		}

		public void run() {
			try {
				ObjectOutputStream outS = new ObjectOutputStream(sck.getOutputStream());
				ObjectInputStream inS = new ObjectInputStream(sck.getInputStream());
				outS.writeObject(certBuffer);

				Certificate cert = certFactory.generateCertificate(new ByteArrayInputStream((byte[]) inS.readObject()));
				PublicKey pubKey = cert.getPublicKey();
				Signature verifEngine = Signature.getInstance("SHA1withDSA");
				verifEngine.initVerify(pubKey);

				Signature signEngine = Signature.getInstance("SHA1withDSA");
				signEngine.initSign(privKey);
				for (;;) {
					SignRequest req = (SignRequest) inS.readObject();
					verifEngine.update(req.toByteArray());
					byte[] sign = (byte[]) inS.readObject();

					if (verifEngine.verify(sign)) {
						int b1 = req.getBaguette1();
						int b2 = req.getBaguette2();
						if ("acqBaguettes".equals(req)) {
							SignResponse rep = null;
							if (sync.acqBaguettes(b1, b2)) rep = new SignResponse(Boolean.TRUE);
							else rep = new SignResponse(Boolean.FALSE);	
							outS.writeObject(rep);
							outS.writeObject(rep.toByteArray());
						} else if ("libBaguettes".equals(req)) {
							sync.libBaguettes(b1, b2);
						}
					}
				}
			} catch (Exception e) {
				System.out.println("problemes dans service() ...");
				e.printStackTrace();
			}
		}
	}
}
