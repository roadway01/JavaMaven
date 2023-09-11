package com.activemq;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @Name :TopicReceiver
 * @Desc :TODO
 * @author:zhu
 * @date :2016年7月12日
 */
public class TopicReceiver {

	private final String BROKER_URL = "tcp://10.10.12.17:61616";

	private final String DEFAULT_USER = ActiveMQConnection.DEFAULT_USER;
	private final String DEFAULT_PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

	private final String DESTINATION = "js.mq.topic";

	public void run() throws Exception {

		TopicConnection connection = null;
		TopicSession session = null;
		try {
			TopicConnectionFactory factory = new ActiveMQConnectionFactory(this.DEFAULT_USER, this.DEFAULT_PASSWORD, this.BROKER_URL);
			connection = factory.createTopicConnection();
			connection.start();

			session = connection.createTopicSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(this.DESTINATION);
			TopicSubscriber subscriber = session.createSubscriber(topic);

			subscriber.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message msg) {
					if (msg != null) {
						TextMessage textMessage = (TextMessage) msg;
						try {
							System.out.println("接收#" + textMessage.getText());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			Thread.sleep(1000 * 100);

			session.commit();

		} catch (Exception e) {
			throw e;
		} finally {
			// 关闭释放资源
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
		TopicReceiver receiver = new TopicReceiver();
		receiver.run();
	}

}
