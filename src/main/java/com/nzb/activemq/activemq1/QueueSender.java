package com.nzb.activemq.activemq1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueSender {
	public static void main(String... args) throws JMSException, InterruptedException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.27.10.140:61676");
		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory("nio://172.27.10.140:61686");
		
		Connection connection = connectionFactory.createConnection();
		
		connection.start();
		
//		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
		Destination destination = session.createQueue("queue1");
		MessageProducer producer = session.createProducer(destination);
		TemporaryQueue tq = session.createTemporaryQueue();
		
		for (int i = 0; i < 10; i++) {
//			TextMessage msg = session.createTextMessage("my first message_" + i);
			MapMessage msg = session.createMapMessage();
			
			msg.setJMSReplyTo(tq);
			
			
			msg.setStringProperty("extra---" +i, "oko");
			msg.setString("my message-------111" + i, "my string property 6" + "" + i);
//			Thread.sleep(1000);
			producer.send(msg);
		}
		
		session.commit();
        session.close();
		connection.close();
	}

}
