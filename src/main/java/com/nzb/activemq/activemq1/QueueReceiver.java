package com.nzb.activemq.activemq1;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueReceiver {
	
	public static void main(String... args) throws JMSException, InterruptedException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://172.27.10.140:61676");
		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory("nio://172.27.10.140:61686");
		
		Connection connection = connectionFactory.createConnection();
		
		connection.start();
		
		Enumeration element = connection.getMetaData().getJMSXPropertyNames();
		while(element.hasMoreElements()) {
			String name = (String) element.nextElement();
			System.out.println("name is " + name);
		}
		
//		final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		final Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Destination destination = session.createQueue("queue1");
		MessageConsumer consumer = session.createConsumer(destination);
		int i = 0;
		
		
		while(i < 10) {
//			同步消息接收方式
//			TextMessage message = (TextMessage) consumer.receive();
			MapMessage msg = (MapMessage) consumer.receive();	
			
			System.out.println("收到的消息是: " + msg.getString("my message-------111" + i) + ", property is "
					+ msg.getStringProperty("extra---" + i));
//			session.commit();
			if (i == 9) {
				msg.acknowledge();
			}
			
			i++;
		}
		
		session.close();
        connection.close();
		
	}

}
