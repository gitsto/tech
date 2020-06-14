package philosophe.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import philosophe.thread2.actif.GestBagActif;


public class GestBagServer {
	public static void main(String[] args) {
		final int nbPhil = 5; 								//Integer.parseInt(args[1], 10);
   		
	    InitialContext ictx;
		try {
			ictx = new InitialContext();
			ConnectionFactory cnxF = (ConnectionFactory) ictx.lookup("synchro-cf0");
			Queue philoQ = (Queue) ictx.lookup("philo-queue");
			Queue synchroQ = (Queue) ictx.lookup("synchro-queue");
	    
			Connection cnx = cnxF.createConnection("root", "root");
			Session session = cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);
			cnx.start();
	    
			JMSSkel srv = new JMSSkel(new GestBagActif(nbPhil, "servant accessible via JMS"), session, synchroQ, philoQ);
			srv.service();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
