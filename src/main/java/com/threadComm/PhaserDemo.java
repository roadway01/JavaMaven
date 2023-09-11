/**
 * 
 */
package com.threadComm;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @desc  : 一种任务可以分为多个阶段，现希望多个线程去处理该批任务，
 * 			对于每个阶段，多个线程可以并发进行，
 * 			但是希望保证只有前面一个阶段的任务完成之后才能开始后面的任务
 * @author: Zhu
 * @date  : 2017年3月1日
 */
public class PhaserDemo {
	public static void main(String args[]){
		
		PhaserDemo demo = new PhaserDemo();
		
		//demo.demoOne();
		
		//demo.demoTwo();
		
		demo.demoThree();
	}
	
	/**
	 * @desc : arriveAndAwaitAdvance():当前线程当前阶段执行完毕，等待其它线程完成当前阶段
	 * @date : 2017年3月2日
	 */
	public void demoOne(){
		int tasks = 3;
		int parts = 5;
		
		final Phaser phaser = new Phaser(tasks){
			@Override
			protected boolean onAdvance(int phase, int registeredParties){
				System.out.println("====== Phase : " + phase + " ======");
				
				return registeredParties == 0;
			}
		};
		
		for(int i = 0; i < tasks; i++){
			int threadId = i;
			
			new Thread(() ->{
				for(int part = 0; part < parts; part++){
					System.out.printf("Thread %s, phase %s\n", threadId, part);
					phaser.arriveAndAwaitAdvance();
				}
				
			}).start();
		}
	}
	
	/**
	 * @desc : awaitAdvance(int phase):该方法等待某一阶段执行完毕,
	 * 								      该阶段数一般由arrive()方法或者arriveAndDeregister()方法返回
	 * @date : 2017年3月2日
	 */
	public void demoTwo(){
		int tasks = 3;
		
		Phaser phaser = new Phaser(tasks);
		

		for(int i = 0; i < tasks; i++){
			String threadName = "thread" + i;
			
			new Thread(() -> {
				Random random = new Random();
				try {
					Thread.sleep(random.nextInt(4) * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(threadName + " arrive");
				phaser.arrive();
			}).start();
		}
		
		System.out.println("main Thread blocking:");
		phaser.awaitAdvance(phaser.getPhase());
		System.out.println("main Thread arrive");
	}
	
	/**
	 * @desc : 动态修改等待的线程数
	 * @date : 2017年3月2日
	 */
	public void demoThree(){
		int tasks = 5;
		int parts = 6;
		
		Phaser phaser = new Phaser(tasks){
			@Override
			protected boolean onAdvance(int phase, int registeredParties){
				System.out.println("====== Phase : " + phase + " ======");
				
				return registeredParties == 0;
			}
		};
		
		for(int i = 0; i < tasks; i++){
			int threadId = i;
			
			new Thread(() ->{
				for(int part = 0; part < parts; part++){
					
					if(threadId == 3 && part == 1){
						//arriveAndDeregister() 该方法立即返回下一阶段的序号，
						//并且其它线程需要等待的个数减一，并且把当前线程从之后需要等待的成员中移除
						phaser.arriveAndDeregister();
						break;
					}
					
					if(threadId == 4 && part == 3){
						//bulkRegister(int parties) 注册多个party
						phaser.bulkRegister(1);
						new Thread(() -> {
							for(int j = 3; j < parts; j ++){
								System.out.printf("Thread %s, phase %s\n", 6, j);
								phaser.arriveAndAwaitAdvance();
							}
						}).start();
					}
					
					System.out.printf("Thread %s, phase %s\n", threadId, part);
					phaser.arriveAndAwaitAdvance();
				}
				
			}).start();
		}
	}
}
