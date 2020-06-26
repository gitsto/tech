package fr.jurbert.formation.orsys.jap.tp6.baguette;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import stream.StRequest;
import stream.StResponse;


//TODO A revoir, car on n'utilise plus la position du philosophe
// dans l'api IBaguettesService
public class RemoteSocketBaguettesService implements IBaguettesService {

	private Map<Integer, SocketData> sockets = new HashMap<Integer, SocketData>();
	
	private final int nbBaguettes;
	
	private class SocketData {
		ObjectOutputStream oos;
		ObjectInputStream ois;
		public SocketData(Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {
			this.oos = oos;
			this.ois = ois;
		}
	}
	
	public RemoteSocketBaguettesService(int nbBaguettes) {
		this.nbBaguettes = nbBaguettes;
	}

	private SocketData getSocketData(int philoPos) {
		SocketData socketData = sockets.get(philoPos);
		if (socketData == null) {
			try {
				Socket socket = new Socket("mw18100", 6678);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				socketData = new SocketData(socket, oos, ois);
				sockets.put(philoPos, socketData);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (socketData == null) {
			System.err.println("fatal error : socket connection to server is impossible");
			System.exit(0);
		}

		return socketData;
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp2.IBaguettesSource#getBaguettes(int)
	 */
	public void getBaguettes(int philoPos) {
		sendRequest(philoPos, "acqBaguettes");
	}

	private void sendRequest(int philoPos, String reqName) {
		SocketData socketData = getSocketData(philoPos);
		try {
			ObjectOutputStream oos = socketData.oos;
			ObjectInputStream ois = socketData.ois;
			int leftBagInd = getLeftBaguetteIndex(philoPos);
			int rightBagInd = getRightBaguetteIndex(philoPos);
			StRequest request = new StRequest(reqName, leftBagInd, rightBagInd);
			
			boolean responseOk = false;
			while(!responseOk) {
				oos.writeObject(request);
				oos.flush();
				try {
					StResponse response = (StResponse) ois.readObject();
					Object result = response.getReponse();
					if (result instanceof Boolean) {
						Boolean ok = (Boolean) result;
						responseOk = ok;
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see fr.jurbert.formation.orsys.jap.tp2.IBaguettesSource#rendBaguettes(int)
	 */
	public void rendBaguettes(int philoPos) {
		sendRequest(philoPos, "libBaguettes");
	}

	private int getLeftBaguetteIndex(int PhiloPos) {
		return PhiloPos;
	}

	private int getRightBaguetteIndex(int philoPos) {
		return (philoPos + 1) % nbBaguettes;
	}

	public boolean getBaguettes(int bag1, int bag2) {
		// TODO Auto-generated method stub
		return false;
	}

	public void rendBaguettes(int bag1, int bag2) {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return "remote socket service";
	}
}
