/**  
 * @Title: Server.java
 * @Package com
 * @Description: [TODO]
 * @author Zhuyj
 * @date 2016-1-20
 */
package com.tcpip;

import java.net.ServerSocket;

/**
 * ClassName: Server 
 * @Description: [TODO]
 * @author Zhuyj
 * @date 2016-1-20
 */
public class Server {
	private ServerSocket server;
	private boolean listening = true;
	
	public void init(){
		try{
			server = new ServerSocket(20161,10);
		}
		catch(Exception e){
			System.out.println("无法监听20161端口");
			e.printStackTrace();
		}
	}
	
	public void listenServer(){
		try{
			while(listening){
				new Thread(new ServerThread(server.accept())).start();
			}
		}catch(Exception e){
			System.out.println("监听程序出错");
			e.printStackTrace();
		}
	}
	
	public void stopServer(){
		try{
			if(!server.isClosed()){
				server.close();
			}
		}catch(Exception e){
			System.out.println("监听服务停止出错");
			e.printStackTrace();
		}
	}
	
	public Server(){
		init();
		listenServer();
	}
	public static void main(String[] args)throws Exception{
		new Server();
	}
}
