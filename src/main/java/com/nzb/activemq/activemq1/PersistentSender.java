package com.nzb.activemq.activemq1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PersistentSender {
	public static void main(String... args) throws JMSException {
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.27.10.140:61676");
		Connection connection = connectionFactory.createConnection();
		
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		
		
		Destination destination = session.createTopic("topic2");
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		connection.start();
		
		for (int i = 0; i < 3; i ++) {
			TextMessage msg = session.createTextMessage("my message is 222" + i);
			
			producer.send(msg);
		}
		
		session.commit();
		session.close();
		connection.close();
	}
	

}
