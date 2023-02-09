package Basic.多线程.lc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * ReentrantLock + Condition
 * https://leetcode.cn/problems/fizz-buzz-multithreaded/solution/chang-you-duo-xian-cheng-zhi-jiao-ti-da-eeurc/
 */
public class FizzBuzz {
    private final Lock lock = new ReentrantLock(true);

    private Condition condition = lock.newCondition();

    private volatile int state = 0; // 控制其他线程的启动

    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        // for循环直接跳3
        for (int i = 3; i <= n; i += 3) {
            if (i % 3 == 0 && i % 5 == 0) {
                continue;
            }
            lock.lock();
            try {
                while (state != 3) {
                    condition.await();
                }
                printFizz.run();
                state = 0;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 == 0 && i % 5 == 0) {
                continue;
            }
            lock.lock();
            try {
                while (state != 5) {
                    condition.await();
                }
                printBuzz.run();
                state = 0;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            lock.lock();
            try {
                while (state != 15) {
                    condition.await();
                }
                printFizzBuzz.run();
                state = 0;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    //控制其他线程的启动
    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            lock.lock();
            try {
                while (state != 0) {
                    condition.await();
                }
                if (i % 3 != 0 && i % 5 != 0) {
                    printNumber.accept(i);
                } else {
                    if (i % 3 == 0 && i % 5 == 0) {
                        state = 15;
                    } else if (i % 3 == 0) {
                        state = 3;
                    } else if (i % 5 == 0) {
                        state = 5;
                    }
                    condition.signalAll();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
