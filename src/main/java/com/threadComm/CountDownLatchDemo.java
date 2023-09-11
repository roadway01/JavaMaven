/**
 * 
 */
package com.threadComm;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @desc  : 某个线程需要等待一个或多个线程操作结束（或达到某种状态）才开始执行 . since jdk 1.5
 * @author: Zhu
 * @date  : 2017年3月1日
 */
public class CountDownLatchDemo {
	public static void main(String args[]) throws InterruptedException{
		int totalThreads = 3;
		long start = System.currentTimeMillis();
		
		CountDownLatch latch = new CountDownLatch(totalThreads);
		
		for(int i = 0; i < totalThreads; i++){
			final String threadName = "thread" + i;
			new Thread(() -> {
				System.out.printf("%s\t%s %s\n", new Date(), threadName, "started");
				
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				System.out.printf("%s\t%s %s\n", new Date(), threadName, "ended");
				latch.countDown();
			}).start();
		}
		
		latch.await();
		
		long end = System.currentTimeMillis();
		System.out.printf("total time : %sms\n", end - start);
	}
}
