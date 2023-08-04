/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2023-2023. All rights reserved.
 */

package Basic.多线程.problem;

/**
 * desc: 三个线程轮流打印abc
 * sync+wait+notify
 * @since 2023/8/2
 **/
public class AbcDemo {
    private volatile int state = 1;

    public void printA() throws InterruptedException {
        // 这里不能锁object,因为是new AbcDemo.printA  会报错IllegalMonitorStateException
        // 对象持有的锁不在 对象执行的方法块里。 要么this要么全部静态方法执行
        synchronized (this) {
            while (state != 1) {
                wait(); // 当前线程wait
            }
            System.out.println(Thread.currentThread().getName() + ":A");
            state = 2;
            notifyAll();
        }
    }

    public void printB() throws InterruptedException {
        synchronized (this) {
            while (state != 2) {
                wait(); // 当前线程wait
            }
            System.out.println(Thread.currentThread().getName() + ":B");
            state = 3;
            notifyAll();
        }
    }

    public void printC() throws InterruptedException {
        synchronized (this) {
            while (state != 3) {
                wait(); // 当前线程wait
            }
            System.out.println(Thread.currentThread().getName() + ":C");
            state = 1;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        AbcDemo abcDeme = new AbcDemo();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    abcDeme.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    abcDeme.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    abcDeme.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
