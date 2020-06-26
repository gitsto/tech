package archi.orb.infra;

import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

import archi.common.JAPGenericRequest;
import archi.common.JAPGenericResponse;

public class JAPGenericAnnotSkel extends Thread {
	private ServerSocket connect;
	private Object target;
	private Class<?> clazz;
	private ExecutorService pool;

	private List<Method> remoteMethods = new ArrayList<Method>();

	public JAPGenericAnnotSkel(Object t, ServerSocket s) {
		target = t;
		connect = s;
		pool = Executors.newCachedThreadPool();
		clazz = target.getClass();
		buildAnnotatedMethodList();
	}

	public JAPGenericAnnotSkel(Object t, String pStr) throws IOException {
		this(t, new ServerSocket(Integer.parseInt(pStr, 10)));
	}

	public InetAddress getInetAddress() { return connect.getInetAddress(); }
	public int getPort() { return connect.getLocalPort(); }
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

			loop: for (;;) {
				try {
					JAPGenericRequest req = null;
					try {
						req = (JAPGenericRequest) inS.readObject();
					} catch (SocketException e) {
						//outS.writeObject(new JAPGenericResponse(e, -1, true));
						System.out.println("probleme de lecture dans skeleton : " + e);
						return;
					}

					JAPGenericResponse rep = null;
					try {
						meth = clazz.getMethod(req.getMethodName(), req.getParameterTypes());
						if (!remoteMethods.contains(meth)) {
							JAPRemoteException e = new JAPRemoteException("invocation distante interdite sur " + req.getMethodName());
							outS.writeObject(new JAPGenericResponse(e, req.getIdent(), true));
							continue loop;
						}

						res = meth.invoke(target, req.getValues());
						rep = new JAPGenericResponse(res, req.getIdent());
					} catch (Exception e) {
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

	private void buildAnnotatedMethodList() {
		Class<?>[] itfs = clazz.getInterfaces();
		for (int i = 0; i < itfs.length; i++) {
			if (itfs[i].isAnnotationPresent(archi.orb.infra.JAPRemote.class)) {
				Method[] meths = itfs[i].getMethods();
				try {
					for (int j = 0; j < meths.length; j++) {
						Method meth = clazz.getMethod(meths[j].getName(), meths[j].getParameterTypes());
						if (!remoteMethods.contains(meth)) remoteMethods.add(meth);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
