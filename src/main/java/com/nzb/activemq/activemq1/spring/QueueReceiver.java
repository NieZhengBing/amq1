package com.nzb.activemq.activemq1.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueReceiver {

	// @Autowired
	// private static JmsTemplate jmsTemplate;

	public static void main(String... args) {
		System.out.println("coming into...");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");
		// String msg = (String) jmsTemplate.receiveAndConvert();
		String msg = (String) jmsTemplate.receiveAndConvert();
		System.out.println("msg: " + msg);
	}

}
