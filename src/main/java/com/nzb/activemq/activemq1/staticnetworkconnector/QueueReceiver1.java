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

public class QueueReceiver1 {
	public static void main(String... args) throws JMSException, InterruptedException {
		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory("tcp://172.27.10.140:61676");
		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory("nio://172.27.10.140:61686");

		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory("tcp://172.27.10.140:61776");

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.27.10.140:61676");

		for (int i = 0; i < 150; i++) {
			new MyThread(connectionFactory).start();
			Thread.sleep(1000L);
		}

		/*
		 * Connection connection = connectionFactory.createConnection();
		 * 
		 * connection.start();
		 * 
		 * Enumeration element = connection.getMetaData().getJMSXPropertyNames(); while
		 * (element.hasMoreElements()) { String name = (String) element.nextElement();
		 * System.out.println("name is " + name); }
		 * 
		 * // final Session session = connection.createSession(true, //
		 * Session.AUTO_ACKNOWLEDGE); final Session session =
		 * connection.createSession(false, Session.CLIENT_ACKNOWLEDGE); Destination
		 * destination = session.createQueue("queue_test222"); MessageConsumer consumer
		 * = session.createConsumer(destination); int i = 0;
		 * 
		 * while (i < 10) { // 同步消息接收方式 // TextMessage message = (TextMessage)
		 * consumer.receive(); MapMessage msg = (MapMessage) consumer.receive();
		 * 
		 * System.out.println("收到的消息是: " + msg.getString("my message-------222" + i) +
		 * ", property is " + msg.getStringProperty("extra---" + i)); //
		 * session.commit(); if (i == 9) { msg.acknowledge(); }
		 * 
		 * i++; }
		 * 
		 * session.close(); connection.close();
		 */

	}

}

class MyThread extends Thread {

	private ConnectionFactory connectionFactory;

	MyThread(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void run() {
		try {
			final Connection connection = connectionFactory.createConnection();

			connection.start();

			Enumeration element = connection.getMetaData().getJMSXPropertyNames();
			while (element.hasMoreElements()) {
				String name = (String) element.nextElement();
				System.out.println("name is " + name);
			}

			// final Session session = connection.createSession(true,
			// Session.AUTO_ACKNOWLEDGE);
			final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("queue_test31");
			MessageConsumer consumer = session.createConsumer(destination);

			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					if (!(message instanceof TextMessage)) {
						try {
							session.commit();
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					TextMessage txtMsg = (TextMessage) message;
					try {
						System.out.println("Receive the message: " + txtMsg.getText());
					} catch (JMSException e1) {
						e1.printStackTrace();
					}
					try {
						session.commit();
						session.close();
						connection.close();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}

			});
			/*
			 * int i = 0;
			 * 
			 * while (i < 10) { // 同步消息接收方式 // TextMessage message = (TextMessage)
			 * consumer.receive(); MapMessage msg = (MapMessage) consumer.receive();
			 * 
			 * System.out.println("收到的消息是: " + msg.getString("my message-------222" + i) +
			 * ", property is " + msg.getStringProperty("extra---" + i)); //
			 * session.commit(); if (i == 9) { msg.acknowledge(); }
			 * 
			 * i++; }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
