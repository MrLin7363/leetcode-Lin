package Basic.多线程.util;/*
    
/**
  *@Author JunLin
  *@Date 2021/2/20
  *@Describe: 这个类能够使一个线程等待其他线程完成各自的工作后再执行。
        CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量
 */

import java.util.concurrent.CountDownLatch;

public class CountdownlanchDemo {
    private Integer i = 0;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void odd(){
        System.out.println("等待10内线程打印完");
        try {
            countDownLatch.await();
            System.out.println("开始执行");
        } catch (Exception e) {
            System.out.println("error");
        }
        while(i < 20){
            i++;
            System.out.println("odd打印"+i);
        }
    }

    public void even() {
        while (i < 10) {
            i++;
            System.out.println(i);
        }
        System.out.println("执行完任务，通知剩余线程执行");
        countDownLatch.countDown();
    }
    public static void main(String[] args){
        final CountdownlanchDemo countDown = new CountdownlanchDemo();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                countDown.odd();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                countDown.even();
            }
        });

        t1.start();
        t2.start();
    }
}
