package com.nzb.activemq.activemq1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class NonPersistentReceiver {
	public static void main(String... args) throws JMSException {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.27.10.140:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

		Destination destination = session.createTopic("topic1");
		MessageConsumer consumer = session.createConsumer(destination);

		Message message = consumer.receive();

		while (message != null) {
			TextMessage msg = (TextMessage) message;
			System.out.println("received message is " + msg.getText());
			message = consumer.receive(1000L);
		}

		session.commit();
		session.close();
		connection.close();
	}

}
