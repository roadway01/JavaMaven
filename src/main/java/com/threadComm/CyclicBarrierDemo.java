/**
 * 
 */
package com.threadComm;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

/**
 * @desc  : CyclicBarrier可以在构造时指定需要在屏障前执行await的个数，所有对await的调用都会等待，
 * 			只到调用await的次数达到预定值，所有等待都会立即被唤醒。 since jdk 1.5
 * @author: Zhu
 * @date  : 2017年3月1日
 */
public class CyclicBarrierDemo {
	public static void main(String args[]){
		int totalThread = 5;
		
		CyclicBarrier barrier = new CyclicBarrier(totalThread);
		
		for(int i = 0; i < totalThread; i++){
			String threadName = "thread" + i;
			
			new Thread(() -> {
				System.out.printf("%s\t%s %s\n", new Date(), threadName, "waiting");
				
				try {
					barrier.await();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				System.out.printf("%s\t%s %s\n", new Date(), threadName, "ended");
			}).start();
		}
	}
}
