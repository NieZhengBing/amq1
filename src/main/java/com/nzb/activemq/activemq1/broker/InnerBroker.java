package com.nzb.activemq.activemq1.broker;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InnerBroker {
	

	public static void main(String... args) throws Exception {
		
//		BrokerService brokerService = new BrokerService();
//		
//		brokerService.setUseJmx(true);
//		brokerService.addConnector("tcp://localhost:61616");
//		brokerService.start();
		
		/*
		 * String url = "properties:broker.properties"; BrokerService brokerService =
		 * BrokerFactory.createBroker(url); // BrokerService brokerService =
		 * BrokerFactory.createBroker(new URI(url));
		 * brokerService.addConnector("tcp://localhost:61616"); brokerService.start();
		 */

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	}

}
