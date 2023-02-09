package Basic.多线程.lc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁 + Condition  = 自定义object锁对象await
 */
public class FooBar4 {
    private int n;

    public FooBar4(int n) {
        this.n = n;
    }

    Lock lock = new ReentrantLock(true);

    private final Condition foo = lock.newCondition();

    volatile boolean flag = true;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock(); // 防止程序直接跑完了
            try {
                while (!flag) {
                    foo.await(); // 释放lock锁
                }
                printFoo.run();
                flag = false;
                foo.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (flag) {
                    foo.await();
                }
                printBar.run();
                flag = true;
                foo.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
