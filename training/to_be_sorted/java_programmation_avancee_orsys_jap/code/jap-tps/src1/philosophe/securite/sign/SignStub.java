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

public class SignStub implements IGestBaguettes {
	Socket sck;
	ObjectInputStream inputStream;
	ObjectOutputStream outputStream;

	byte[] signAcq, signRel;
	Signature signEngine;
	Signature verifEngine;

	CertificateFactory certFactory;
	
	byte[] certBuffer;
	PrivateKey privKey;
	Certificate srvCertifcate;

	public SignStub(String aStr, String pStr, String alias) {
		try {
			InetAddress add = InetAddress.getByName(aStr);
			int port = Integer.parseInt(pStr, 10);
			sck = new Socket(add, port);
			outputStream = new ObjectOutputStream(sck.getOutputStream());
			inputStream = new ObjectInputStream(sck.getInputStream());
			sck.setSoLinger(true, 50);

			String ksName = "securite-resources\\jav5keystore";
			String ksPasswdString = "jav5keystore";
			char[] ksPasswd = ksPasswdString.toCharArray();
			FileInputStream fIn = new FileInputStream(ksName);
			KeyStore ks = KeyStore.getInstance("JKS");

			ks.load(fIn, ksPasswd);
			privKey = (PrivateKey) ks.getKey(alias, ksPasswd);
			signEngine = Signature.getInstance("SHA1withDSA");
			signEngine.initSign(privKey);

			Certificate cert = (Certificate) ks.getCertificate(alias);
			certBuffer = cert.getEncoded();
			outputStream.writeObject(certBuffer);

			certFactory = CertificateFactory.getInstance("X.509");
			cert = certFactory.generateCertificate(new ByteArrayInputStream((byte[]) inputStream.readObject()));
			PublicKey pubKey = cert.getPublicKey();

			verifEngine = Signature.getInstance("SHA1withDSA");
			verifEngine.initVerify(pubKey);
		} catch (UnknownHostException e) {
			System.err.println("adresse serveur inconnue");
			return;
		} catch (SocketException e) {
			System.err.println("erreur a la creation de la socket");
		} catch (IOException e) {
			System.err.println("erreur durant communication reseau");
		} catch (IllegalArgumentException e) {
			System.err.println("parametres packet illegaux");
		} catch (Exception e) {
			System.err.println("parametres securitaire illegaux");
			e.printStackTrace();
		}
	}

	public boolean acqBaguettes(int b1, int b2) {
		try {
			SignRequest req = new SignRequest("acqBaguette", b1, b2);
			signEngine.update(req.toByteArray());
			signAcq = signEngine.sign();
			outputStream.writeObject(req);
			outputStream.writeObject(signAcq);

			SignResponse rep = (SignResponse) inputStream.readObject();
			byte[] sign = (byte[]) inputStream.readObject();

			verifEngine.update(rep.toByteArray());
			if (verifEngine.verify(sign)) {
				return (Boolean) rep.getReponse();
			} else {
				System.out.println("reponse a ACQ (PB !!!) " + b1 + " " + b2);
				return false;
			}
		} catch (SocketException e) {
			System.err.println("erreur durant communication reseau");
		} catch (IOException e) {
			System.err.println("erreur durant I/O reseau");
		} catch (ClassNotFoundException e) {
			System.err.println("classe inconnue dans reponse serveur");
		} catch (Exception e) {
			System.err.println("erreur securitaire ...");
			e.printStackTrace();
		}
		return false;
	}

	public void libBaguettes(int b1, int b2) {
		try {
			SignRequest req = new SignRequest("libBaguette", b1, b2);
			signEngine.update(req.toByteArray());
			signRel = signEngine.sign();
			outputStream.writeObject(req);
			outputStream.writeObject(signRel);
		} catch (SocketException e) {
			System.err.println("erreur durant communication reseau");
		} catch (IOException e) {
			System.err.println("erreur durant I/O reseau");
		} catch (Exception e) {
			System.err.println("erreur securitaire ...");
			e.printStackTrace();
		}
	}
	
	public void commencer(int numero) {}
	public void terminer(int numero) {}
}
