package philosophe.reflect;

import philosophe.api.IGestBaguettes;

import java.net.*;
import java.io.*;

import archi.infra.JAPGenericRequest;
import archi.infra.JAPGenericResponse;


public class RfStub implements IGestBaguettes {

	JAPGenericRequest requete;

	Socket sck;
	ObjectInputStream inS;
	ObjectOutputStream outS;

	public RfStub(String aStr, String pStr)throws Exception {
		InetAddress add = InetAddress.getByName(aStr);
		int port = Integer.parseInt(pStr, 10);
		sck = new Socket(add, port);
			
		outS = new ObjectOutputStream(sck.getOutputStream());
		inS = new ObjectInputStream(sck.getInputStream());
		sck.setSoLinger(true, 50);
	}

	public boolean acqBaguettes(int b1, int b2) throws IllegalArgumentException {
		try {
			JAPGenericRequest requete = new JAPGenericRequest("acqBaguettes",new Class[] { int.class, int.class }, new Object[] { b1, b2 }, boolean.class, 0);
			outS.writeObject(requete);
			JAPGenericResponse reponse = (JAPGenericResponse) inS.readObject();
			if (reponse.isException()) {
				Exception ex = (Exception) reponse.getResult();
				if (ex instanceof IllegalArgumentException) throw ex;
				else if (ex instanceof RuntimeException) throw ex;
				else throw new RuntimeException(ex);
			}
			return (Boolean) reponse.getResult();
		} catch (RuntimeException e) {
			System.err.println("probleme dans acqBaguettes " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("probleme dans acqBaguettes " + e);
			throw new RuntimeException(e);
		}
	}

	public void libBaguettes(int b1, int b2)  throws IllegalArgumentException {
		try {
			JAPGenericRequest requete = new JAPGenericRequest("libBaguettes",new Class[] { int.class, int.class }, new Object[] { b1, b2 }, void.class, 0);
			outS.writeObject(requete);
			JAPGenericResponse reponse = (JAPGenericResponse) inS.readObject();
			if (reponse.isException()) {
				Exception ex = (Exception) reponse.getResult();
				if (ex instanceof IllegalArgumentException) throw ex;
				else if (ex instanceof RuntimeException) throw ex;
				else throw new RuntimeException(ex);
			}
		} catch (RuntimeException e) {
			System.err.println("probleme dans libBaguettes " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("probleme dans libBaguettes " + e);
			throw new RuntimeException(e);
		}
	}
}
