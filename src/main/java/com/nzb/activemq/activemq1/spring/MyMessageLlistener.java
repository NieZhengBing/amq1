package com.nzb.activemq.activemq1.spring;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageLlistener implements MessageListener {

	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;

		try {
			System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
