package com.nzb.activemq.activemq1.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class QueueSender {

	// @Resource
	// private JmsTemplate jmsTemplate;

	public static void main(String... args) {
		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("applicationContext.xml");
		// QueueSender sender = (QueueSender) ctx.getBean("queueSender");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");

		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("this is my first message 3");
			}

		});

	}

}
