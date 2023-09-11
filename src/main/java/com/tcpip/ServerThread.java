/**  
 * @Title: ServerThread.java
 * @Package com
 * @Description: [TODO]
 * @author Zhuyj
 * @date 2016-1-20
 */
package com.tcpip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * ClassName: ServerThread 
 * @Description: [TODO]
 * @author Zhuyj
 * @date 2016-1-20
 */
public class ServerThread implements Runnable{

	private Socket client = null;
	
	public ServerThread(Socket client){
		this.client = client;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		try{
			PrintStream out = new PrintStream(client.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			boolean flag = true;
			while(flag){
				String req = is.readLine();
				if(req == null || "".equals(req) || "bye".equalsIgnoreCase(req)){
					flag = false;
					continue;
				}
				out.println("got info");
				System.out.println("request:" + req);
			}
			
			is.close();
			out.close();
			client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
