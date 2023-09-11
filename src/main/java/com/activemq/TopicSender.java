package com.activemq;

import javax.jms.DeliveryMode;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @Name :TopicSender
 * @Desc :TODO
 * @author:zhu
 * @date :2016年7月12日
 */
public class TopicSender {

	private final int SEND_NUM = 10;
	private final String BROKER_URL = "tcp://10.10.12.17:61616";

	private final String DEFAULT_USER = ActiveMQConnection.DEFAULT_USER;
	private final String DEFAULT_PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

	private final String DESTINATION = "js.mq.topic";

	public void sendMessage(TopicSession session, TopicPublisher publisher) throws Exception {
		for (int i = 0; i < this.SEND_NUM; i++) {
			String message = "发送消息第" + (i + 1) + "条";

			TextMessage textMessage = session.createTextMessage(message);
			System.out.println(textMessage.getText());
			// 发送 Topic消息
			publisher.send(textMessage);
		}
	}

	public void run() throws Exception {
		TopicConnection connection = null;
		TopicSession session = null;
		try {
			TopicConnectionFactory factory = new ActiveMQConnectionFactory(this.DEFAULT_USER, this.DEFAULT_PASSWORD, this.BROKER_URL);
			connection = factory.createTopicConnection();
			connection.start();

			session = connection.createTopicSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(this.DESTINATION);
			TopicPublisher publisher = session.createPublisher(topic);
			publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			sendMessage(session, publisher);
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
		TopicSender sender = new TopicSender();
		sender.run();
	}

}
