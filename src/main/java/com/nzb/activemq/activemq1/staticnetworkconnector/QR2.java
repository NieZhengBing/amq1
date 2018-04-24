package com.nzb.activemq.activemq1.staticnetworkconnector;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QR2 {
	
	public static void main(String... args) throws JMSException, InterruptedException {
		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory("tcp://172.27.10.140:61676");
		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory("nio://172.27.10.140:61686");
		
		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory("tcp://172.27.10.140:61776");

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.27.10.140:61776");

		Connection connection = connectionFactory.createConnection();
		
		connection.start();
		
		Enumeration element = connection.getMetaData().getJMSXPropertyNames();
		while(element.hasMoreElements()) {
			String name = (String) element.nextElement();
			System.out.println("name is " + name);
		}
		
		final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("queue_test_avg");
		
		for (int i = 0; i < 1; i++) {
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {

				public void onMessage(Message message) {
					TextMessage msg = (TextMessage) message;
					try {
						System.out.println("QR2 message is: " + msg.getText());
						session.commit();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}

			});
		}
		
		
		// session.close();
		// connection.close();
		
	}

}
