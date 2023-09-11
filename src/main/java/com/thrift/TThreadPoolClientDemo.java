/**
 * 
 */
package com.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.thrift.dataType.ResultStr;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月2日
 */
public class TThreadPoolClientDemo {
	public static final String SERVER_IP = "10.10.12.17";
	public static final int SERVER_PORT = 9090;
	public static final int TIMEOUT = 30000;
	
	public void startClient(String userName){
		//定义传输层对象
		TTransport transport = null;
		try{
			//要跟server端类型一致
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			//定义通信协议对象，与server端一致
			TProtocol protocol = new TBinaryProtocol(transport);
			//TProtocol protocol = new TJSONProtocol(transport);
			//TProtocol protocol = new TCompactProtocol(transport);
			//创建客户端对象
			ThriftServerService.Client client = new ThriftServerService.Client(protocol);
			//打开socket连接
			transport.open();
			//调用server端函数
			ResultStr result = client.sayHello(userName);
			
			System.out.println("client get result :" + result);
		}catch (TTransportException e) {
			e.printStackTrace();
		}catch (TException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != transport){
				transport.close();
			}
		}
	}
	
	public static void main(String args[]){
		TThreadPoolClientDemo clientDemo = new TThreadPoolClientDemo();
		clientDemo.startClient("threadPool");
	}
}
