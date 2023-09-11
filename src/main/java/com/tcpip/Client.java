/**  
 * @Title: Client.java
 * @Package com
 * @Description: [TODO]
 * @author Zhuyj
 * @date 2016-1-21
 */
package com.tcpip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * ClassName: Client 
 * @Description: [TODO]
 * @author Zhuyj
 * @date 2016-1-21
 */
public class Client {
	public static void main(String[] args) throws Exception{
		Socket client = new Socket("127.0.0.1",20161);
		client.setSoTimeout(10000);
		
		BufferedReader writeIn = new BufferedReader(new InputStreamReader(System.in));
		PrintStream out = new PrintStream(client.getOutputStream());
		BufferedReader resB = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		boolean flag = true;
		while(flag){
			System.out.print("please input info:");
			String info = writeIn.readLine();
			
			out.println(info);
			
			if("bye".equalsIgnoreCase(info)){
				flag = false;
				continue;
			}
			
			String res = resB.readLine();
			System.out.println("response:" + res);
		}
		
		writeIn.close();
		resB.close();
		client.close();

	}
}
