/**
 * 
 */
package com.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;

/**
 * @desc  : 简单的单线程模型，一般用于测试
 * @author: Zhu
 * @date  : 2017年2月28日
 */
public class TSimpleServerDemo {
	public static final int SERVER_PORT = 9090;
	
	public void startSever(){
		try{
			System.out.println("server start ...");
			
			//定义一个processor对象
			TProcessor processor = new ThriftServerService.Processor<ThriftServerService.Iface>(new ThriftServerServiceImpl());
			//定义socket对象，用于tcp的socket通信
			TServerSocket serverSocket = new TServerSocket(SERVER_PORT);
			//定义参数对象
			TServer.Args tArgs = new TServer.Args(serverSocket);
			//赋值
			tArgs.processor(processor);
			//传输模式，以帧方式传输数据
			tArgs.transportFactory(new TFramedTransport.Factory());
			//使用二进制协议
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			//tArgs.protocolFactory(new TJSONProtocol.Factory());
			//tArgs.protocolFactory(new TCompactProtocol.Factory());
			//定义server对象
			TServer server = new TSimpleServer(tArgs);
			//启动
			server.serve();
		}catch(Exception e){
			System.out.println("server start error!");
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		TSimpleServerDemo serverDemo = new TSimpleServerDemo();
		serverDemo.startSever();
	}
}
