package philosophe.jms;

import philosophe.api.IGestBaguettes;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.NamingException;

public class JMSSkel {
	private Session session;
	private MessageConsumer receiver;
	private IGestBaguettes sync;
	private MessageProducer sender;

	public JMSSkel(IGestBaguettes s, Session ses, Queue rcvQ, Queue sndQ) throws NamingException, JMSException {
		sync = s;
		session = ses;
		sender = session.createProducer(sndQ);
	    receiver = session.createConsumer(rcvQ);	    
	}

	public void service() {
		System.out.println("serveur JMS demarre");

		for (;;) {
			try {
				Message requestMsg;
				requestMsg = receiver.receive();

				if (requestMsg instanceof MapMessage) {
					String op = ((MapMessage) requestMsg).getString("meth");
					int b1 = ((MapMessage) requestMsg).getInt("bag1");
					int b2 = ((MapMessage) requestMsg).getInt("bag2");
					MapMessage message = session.createMapMessage();
					message.setByteProperty("philo", (byte) requestMsg.getByteProperty("philo"));
					
					switch (op.charAt(0)) {
					case 'a' :
						message.setBoolean("resp", sync.acqBaguettes(b1, b2));
						//message.setJMSDestination(requestMsg.getJMSReplyTo());
						sender.send(message);
						//session.commit();
						break;
					case 'l':
						sync.libBaguettes(b1, b2);
						sender.send(message);
					}
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
