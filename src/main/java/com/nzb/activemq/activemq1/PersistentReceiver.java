package com.nzb.activemq.activemq1;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PersistentReceiver {
	public static void main(String... args) throws JMSException {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.27.10.140:61676");
		Connection connection = connectionFactory.createConnection();
		connection.setClientID("cc1");
		
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

		Topic destination = session.createTopic("topic2");
		TopicSubscriber consumer = session.createDurableSubscriber(destination, "T1");
		
		connection.start();
		Message message = consumer.receive();

		/*
		 * consumer.setMessageListener(new MessageListener() { public void
		 * onMessage(Message message) { } });
		 */

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
