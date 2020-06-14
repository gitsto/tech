package philosophe.jms1;

import philosophe.api.IGestBaguettes;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.NamingException;

public class JMSStub implements IGestBaguettes {
	private Session session;
	private MessageProducer sender;
	private MessageConsumer receiver;
	private Queue rcvQueue;
	private int numero;
	
	public JMSStub(Connection cnx, Queue sndQ, byte num) throws NamingException, JMSException {
		session = cnx.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    sender = session.createProducer(sndQ);
	    rcvQueue = session.createTemporaryQueue();
	    receiver = session.createConsumer(rcvQueue);
	    numero = num;
	}

	public boolean acqBaguettes(int b1, int b2) {
		try {
			MapMessage message = session.createMapMessage();
			message.setByteProperty("philo", (byte) numero);
			message.setString("meth", "acqBaguettes");
			message.setInt("bag1", b1);
			message.setInt("bag2", b2);
			message.setJMSReplyTo(rcvQueue);
			sender.send(message);
			//session.commit();		//si session transactionnelle
			message = (MapMessage) receiver.receive();
			return message.getBoolean("resp");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void libBaguettes(int b1, int b2) {
		try {
			MapMessage message = session.createMapMessage();
			message.setByteProperty("philo", (byte) numero);
			message.setString("meth", "libBaguettes");
			message.setInt("bag1", b1);
			message.setInt("bag2", b2);
			sender.send(message);
			//session.commit();		//si session transactionnelle	
			receiver.receive();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
