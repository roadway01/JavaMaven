/**  
 * @Title: UDPClient.java
 * @Package com
 * @Description: [TODO]
 * @author Zhuyj
 * @date 2016-1-27
 */
package com.tcpip;

/**
 * ClassName: UDPClient 
 * @Description: [TODO]
 * @author Zhuyj
 * @date 2016-1-27
 */
public class UDPClient {
	
	private static final int TIME_OUT = 5000;
	private static final int MAX_NUM = 5;
	
	public static void main(String[] args){
		String send_str = "Hello world!";	
		System.out.println(send_str + (TIME_OUT + MAX_NUM));
	}
}
