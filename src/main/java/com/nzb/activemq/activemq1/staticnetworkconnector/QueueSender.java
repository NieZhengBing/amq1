package com.nzb.activemq.activemq1.staticnetworkconnector;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueSender {
	public static void main(String... args) throws JMSException, InterruptedException {
		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory("tcp://172.27.10.140:61676");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.27.10.140:61676");
		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory("nio://172.27.10.140:61686");

		// ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
		// "failover:(tcp://172.27.10.140:61676,tcp://172.27.10.140:61776)?randomize=false");

		Connection connection = connectionFactory.createConnection();

		connection.start();

		// Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("MY.QUEUE");
		MessageProducer producer = session.createProducer(destination);
		// TemporaryQueue tq = session.createTemporaryQueue();

		for (int i = 0; i < 3; i++) {
			TextMessage msg = session.createTextMessage("nzb message_" + i);
			// MapMessage msg = session.createMapMessage();

			// msg.setJMSReplyTo(tq);

			// msg.setStringProperty("extra---" + i, "oko");
			// msg.setString("my message-------333" + i, "my string property 7" + "" + i);
			// Thread.sleep(1000);
			producer.send(msg);
		}

		session.commit();
		session.close();
		connection.close();
	}

}
