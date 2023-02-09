package Basic.多线程.lc;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * zero even zero odd zero even...
 * Semaphore  CountDownLatch  Thread.yield()  ReentrantLock+Condition 几种方法都可以
 * https://leetcode.cn/problems/print-zero-even-odd/solution/chang-you-duo-xian-cheng-zhi-da-yin-ling-qy3o/
 */
public class ZeroEvenOdd {

    private Semaphore semaphore = new Semaphore(1);

    private Semaphore semaphoreEven = new Semaphore(0);

    private Semaphore semaphoreOdd = new Semaphore(0);

    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            semaphore.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) { // 偶数
                semaphoreEven.release();
            } else {
                semaphoreOdd.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                semaphoreEven.acquire();
                printNumber.accept(i);
                semaphore.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                semaphoreOdd.acquire();
                printNumber.accept(i);
                semaphore.release();
            }
        }
    }
}
