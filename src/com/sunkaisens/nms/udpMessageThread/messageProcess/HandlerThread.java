package com.sunkaisens.nms.udpMessageThread.messageProcess;

import java.util.Observable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class HandlerThread extends Observable implements Runnable {

	private String name;
	private Thread thread;
	protected int nIdleCount;
	protected BlockingQueue<ReceivedMessage> messageQueue = new LinkedBlockingQueue<ReceivedMessage>();
	protected Object lock = new Object();

	public HandlerThread() {
		
	}

	public HandlerThread(String name) {
		this.name = name;
	}

	public void run() {
		while (true) {
			synchronized (lock) {
				if (messageQueue.isEmpty()) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (!messageQueue.isEmpty()) {
					ReceivedMessage receivedMsg = (ReceivedMessage) messageQueue.poll();
					onThreadMessage(receivedMsg.getType(), receivedMsg.getMessage());
				} else {
					
				}
			}
		}
	}

	public void startThread() {
		createThread();
	}

	private void createThread() {
		thread = new Thread(this, name + "Thread");
		thread.start();
	}

	public void postThreadMessage(int type, Object msg) {
		
	}
	
	//继承类继承该方法进行处理
	public void onThreadMessage(int string, Object msg) {

	}

}
