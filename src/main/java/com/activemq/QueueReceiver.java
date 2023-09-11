package com.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @Name :QueueReceiver
 * @Desc :TODO
 * @author:zhu
 * @date :2016年7月12日
 */
public class QueueReceiver {

	private final String BROKER_URL = "tcp://10.10.12.17:61616";

	private final String DEFAULT_USER = ActiveMQConnection.DEFAULT_USER;
	private final String DEFAULT_PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

	private final String DESTINATION = "HelloWorld";

	public void run() throws Exception {

		Connection connection = null;
		Session session = null;
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(this.DEFAULT_USER, this.DEFAULT_PASSWORD, this.BROKER_URL);
			connection = factory.createConnection();
			connection.start();

			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(this.DESTINATION);
			MessageConsumer consumer = session.createConsumer(destination);

			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message msg) {
					TextMessage text = (TextMessage) msg;
					if (text != null) {
						try {
							System.out.println("接收：" + text.getText());
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				}
			});

			Thread.sleep(1000 * 10);
			
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

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		QueueReceiver receiver = new QueueReceiver();
		receiver.run();
	}

}
