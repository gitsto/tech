package philosophe.jms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import philosophe.thread2.actif.PhiloActif;


public class LanceurPhilo {
	InitialContext ictx;
	Session session;
	Connection cnx;
	
	public static void main(String args[]) {
	    InitialContext ictx;
		try {
			
			int nbPhil = 5;
			int nbServ = 3;
			
			if (args.length > 0) {
				nbPhil = Integer.parseInt(args[0], 10);
				nbServ = Integer.parseInt(args[1], 10);
			}
	
			ictx = new InitialContext();
			ConnectionFactory cnxF = (ConnectionFactory) ictx.lookup("synchro-cf0");
			Queue recvQ = (Queue) ictx.lookup("philo-queue");
			Queue sendQ = (Queue) ictx.lookup("synchro-queue");
	    
			Connection cnx = cnxF.createConnection("root","root");
			cnx.start();

			ExecutorService serveurs = Executors.newFixedThreadPool(nbServ, Executors.defaultThreadFactory());
			
			for (int i = 0; i < nbPhil; i++) {
				
				PhiloActif pRef = new PhiloActif(i, serveurs, new JMSStub(cnx, sendQ, recvQ,(byte) i), i, (i + 1) % nbPhil);
				pRef.start();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
