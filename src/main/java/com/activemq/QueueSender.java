package com.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @Name :QueueSender
 * @Desc :TODO
 * @author:zhu
 * @date :2016年7月12日
 */
public class QueueSender {

	private final int SEND_NUM = 10;
	private final String BROKER_URL = "tcp://10.10.12.17:61616";

	private final String DEFAULT_USER = ActiveMQConnection.DEFAULT_USER;
	private final String DEFAULT_PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

	private final String DESTINATION = "HelloWorld";

	public void sendMessage(Session session, MessageProducer producer) throws Exception {
		for (int i = 0; i < this.SEND_NUM; i++) {
			String message = "发送消息第" + (i + 1) + "条";
			TextMessage text = session.createTextMessage(message);

			System.out.println(message);
			producer.send(text);
		}
	}

	public void run() throws Exception {

		Connection connection = null;
		Session session = null;
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(this.DEFAULT_USER, this.DEFAULT_PASSWORD, this.BROKER_URL);
			connection = factory.createConnection();
			connection.start();

			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(this.DESTINATION);
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			sendMessage(session, producer);
			session.commit();

		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		QueueSender sender = new QueueSender();
		sender.run();
	}

}
