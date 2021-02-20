package Basic;/*
    
/**
  *@Author JunLin
  *@Date 2021/2/20
  *@Describe: 这个类能够使一个线程等待其他线程完成各自的工作后再执行。
CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。
 */

import java.util.concurrent.CountDownLatch;

public class CountdownlanchDemo {
    private Integer i = 0;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void odd(){
        while(i < 10){
            if(i%2 == 1){
                System.out.println("奇数："+i);
                i++;
                countDownLatch.countDown();
            } else {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void even(){
        while(i < 10){
            if(i%2 == 0){
                System.out.println("偶数："+i);
                i++;
                countDownLatch.countDown();
            } else {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args){
        final CountdownlanchDemo countDown = new CountdownlanchDemo();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                countDown.odd();
            }
        },"奇数");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                countDown.even();
            }
        },"偶数");

        t1.start();
        t2.start();
    }
}
