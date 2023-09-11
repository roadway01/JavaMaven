/**
 * 
 */
package com.thrift;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月2日
 */
public class TThreadedSelectorServerDemo {
	public static final int SERVER_PORT = 9090;
	
	public void startSever(){
		try{
			System.out.println("server start ...");
			
			//定义一个processor对象
			TProcessor processor = new ThriftServerService.Processor<ThriftServerService.Iface>(new ThriftServerThreadServiceImpl());
			//定义socket对象，用于tcp的socket通信
			TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(SERVER_PORT);
			//定义参数对象
			TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverSocket);
			//赋值
			tArgs.processor(processor);
			//传输模式，以帧方式传输数据
			tArgs.transportFactory(new TFramedTransport.Factory());
			//使用二进制协议
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			//tArgs.protocolFactory(new TJSONProtocol.Factory());
			//tArgs.protocolFactory(new TCompactProtocol.Factory());
			
			//SelectorThread对象专门用于处理业务socket的网络I/O操作
			tArgs.selectorThreads(2);
			
			//ExecutorService线程池中的线程完成此次调用的具体执行
			ExecutorService pool = Executors.newFixedThreadPool(2);
			
			tArgs.executorService(pool);
			
			//定义server对象
			TServer server = new TThreadedSelectorServer(tArgs);
			//启动
			server.serve();
		}catch(Exception e){
			System.out.println("server start error!");
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		TThreadedSelectorServerDemo serverDemo = new TThreadedSelectorServerDemo();
		serverDemo.startSever();
	}
}
