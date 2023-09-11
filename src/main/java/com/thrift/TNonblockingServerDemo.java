/**
 * 
 */
package com.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * @desc  : 使用非阻塞式IO，服务端和客户端需要指定 TFramedTransport 数据传输的方式
 * @author: Zhu
 * @date  : 2017年3月2日
 */
public class TNonblockingServerDemo {
	public static final int SERVER_PORT = 9090;
	
	public void startSever(){
		try{
			System.out.println("server start ...");
			
			//定义一个processor对象
			TProcessor processor = new ThriftServerService.Processor<ThriftServerService.Iface>(new ThriftServerServiceImpl());
			//定义socket对象，用于tcp的socket通信
			TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(SERVER_PORT);
			//定义参数对象
			TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverSocket);
			//赋值
			tArgs.processor(processor);
			//传输模式，以帧方式传输数据
			tArgs.transportFactory(new TFramedTransport.Factory());
			//使用二进制协议
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			//tArgs.protocolFactory(new TJSONProtocol.Factory());
			//tArgs.protocolFactory(new TCompactProtocol.Factory());
			//定义server对象
			TServer server = new TNonblockingServer(tArgs);
			//启动
			server.serve();
		}catch(Exception e){
			System.out.println("server start error!");
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		TNonblockingServerDemo serverDemo = new TNonblockingServerDemo();
		serverDemo.startSever();
	}
}
