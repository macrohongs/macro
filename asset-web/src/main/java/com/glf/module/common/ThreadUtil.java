package com.glf.module.common;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.glf.module.controller.interfac.TradeModel;
import com.glf.utils.DateUtils;

public class ThreadUtil {
    private static final Logger log = LoggerFactory.getLogger(ThreadUtil.class);
	
	public static String task(TradeModel tm,String request) throws InterruptedException{
		int threadNums = Integer.valueOf(tm.getThread());
		int taskNums = Integer.valueOf(tm.getTask());
		int sleepTime = Integer.valueOf(tm.getThreadInterval());
		StringBuilder sb = new StringBuilder();
		Runnable taskTemp = new Runnable() {
            private int iCounter;
    		String res = "";

            @Override
            public void run() {
                for(int i = 1; i <= taskNums; i++) {
                    try {
                    	res = JxInterfaceUtil.Client(tm.getIp(), tm.getPort(), request);
                        iCounter++;
                    	sb.append("response:[" + DateUtils.getDateTime() + "]进程：[" + Thread.currentThread().getName() + "]，任务数：[" + i + "]，总任务数：[" + iCounter  + "]\n<br/>");
                    	sb.append("         [" + res + "]\n<br/>");
                    	Thread.sleep(sleepTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    	sb.append("response:error-[" + DateUtils.getDateTime() + "]进程：[" + Thread.currentThread().getName() + "]，任务数：[" + i + "]，总任务数：[" + iCounter  + "]\n<br/>");
                    }
                }
            }
        };
        ThreadUtil thread = new ThreadUtil();
        thread.startTask(threadNums, taskTemp);
        log.info(sb.toString());
        return sb.toString();
	}
	
	public void startTask(int threadNums, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadNums);
        for(int i = 0; i < threadNums; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        // 使线程在此等待，当开始门打开时，一起涌入门中
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            // 将结束门减1，减到0时，就可以开启结束门了
                            endGate.countDown();
                        }
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            };
            t.start();
        }
        System.out.println("[" + DateUtils.getDateTime() + "][" + Thread.currentThread() + "] 所有线程已准备开启……");
        // 因开启门只需一个开关，所以立马就开启开始门
        startGate.countDown();
        // 等等结束门开启
        endGate.await();
        System.out.println("[" + DateUtils.getDateTime() + "][" + Thread.currentThread() + "] 所有线程已执行完成.");
    }

}
