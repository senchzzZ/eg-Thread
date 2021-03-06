package com.exp.zsq.juc.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaoshengqi on 2017/5/2.
 */
public class CyclicBarrierTest1 {

    private static int SIZE = 5;
    private static CyclicBarrier cb;
    public static void main(String[] args) throws InterruptedException {

        cb = new CyclicBarrier(SIZE);

        // 新建5个任务
        for(int i=0; i<10; i++){
            new InnerThread().start();
            TimeUnit.SECONDS.sleep(1);
        }
    }

    static class InnerThread extends Thread{
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");
                System.out.println(cb.getNumberWaiting());

                // 将cb的参与者数量加1
                cb.await();
                // cb的参与者数量等于5时，才继续往后执行
                System.out.println(Thread.currentThread().getName() + " continued.");
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
