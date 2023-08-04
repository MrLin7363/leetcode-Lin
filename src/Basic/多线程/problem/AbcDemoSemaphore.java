/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2023-2023. All rights reserved.
 */

package Basic.多线程.problem;

import java.util.concurrent.Semaphore;

/**
 * desc:
 *
 **/
public class AbcDemoSemaphore {
    private static Semaphore semaphoreA = new Semaphore(1);

    private static Semaphore semaphoreB = new Semaphore(0);

    private static Semaphore semaphoreC = new Semaphore(0);

    private static void printA() throws InterruptedException {
        semaphoreA.acquire();
        System.out.println("A");
        semaphoreB.release();
    }

    private static void printB() throws InterruptedException {
        semaphoreB.acquire();
        System.out.println("B");
        semaphoreC.release();
    }

    private static void printC() throws InterruptedException {
        semaphoreC.acquire();
        System.out.println("C");
        semaphoreA.release();
    }

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
