package com.activemq;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Name  :SpringListener
 * @Desc  :TODO
 * @author:zhu
 * @date  :2016年7月12日
 */
public class SpringListener implements MessageListener{

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		if (message != null) {
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println("spring接收#" + textMessage.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
