package philosophe.reflect;

import java.lang.reflect.Method;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

import archi.infra.JAPGenericRequest;
import archi.infra.JAPGenericResponse;



public class JAPGenericSkel extends Thread {
	private ServerSocket connect;
	private Object target;
	private Class<?> clazz;	
	private ExecutorService pool;

	public JAPGenericSkel(Object t, ServerSocket s) {
		target = t;
		connect = s;
		pool = Executors.newCachedThreadPool();
		clazz = target.getClass();		
	}
	public JAPGenericSkel(Object t, String pStr) throws IOException {
		this(t, new ServerSocket(Integer.parseInt(pStr, 10)));
	}

	public InetAddress getInetAddress() { return connect.getInetAddress(); }
	public Object getTarget() { return target; }

	public void run() {
		try {
			for (;;) {
				Socket sck = connect.accept();
				pool.execute(new SkeletonTask(sck));
			}
		} catch (Exception e) { 
			System.out.println("probleme a la creation d'une connexion dans le skeleton" + e);
			e.printStackTrace();
		}
	}

	class SkeletonTask implements Runnable {
		Socket socket;

		SkeletonTask(Socket s) { socket = s; }

		public void run() {
			ObjectOutputStream outS = null;
			ObjectInputStream inS = null;
			JAPGenericRequest req = null;
			Method meth = null;
			Object res = null;
			
			try {
				outS = new ObjectOutputStream(socket.getOutputStream());
				inS = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				System.out.println("probleme au demarrage d'une connexion dans le skeleton " + e);
				e.printStackTrace();
				return;
			}
			
			loop : for (;;) {
				try {
					req = null;
					try {
						req = (JAPGenericRequest) inS.readObject();
					} catch (SocketException e) {
						System.out.println("probleme de lecture dans skeleton : " + e);
						return;
					}
				
					JAPGenericResponse rep = null;
					try {
						//A FAIRE .....
					} catch(Exception e) {
						outS.writeObject(new JAPGenericResponse(e, req.getIdent(), true));
						continue loop;
					}

					outS.writeObject(rep);
				} catch (Exception e) {
					System.out.println("probleme d'ecriture dans skeleton : " + e);
					e.printStackTrace();
				}
			}
		}
	}
}