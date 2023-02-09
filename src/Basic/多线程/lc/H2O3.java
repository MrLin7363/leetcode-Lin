package Basic.多线程.lc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock+Condition = synchronized(object) 不公平锁，有可能重复进入
 */
public class H2O3 {
    private Lock lock = new ReentrantLock(true);

    private Condition condition = lock.newCondition();

    private volatile int hCount = 0;

    private volatile int oCount = 0;

    public H2O3() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (hCount == 2) {
                condition.await();
            }
            hCount++;
            if (hCount == 2 && oCount == 1) {
                hCount = 0;
                oCount = 0;
            }
            releaseHydrogen.run();
            condition.signalAll(); // 注意是All 因为可能有相同的两个h线程或者一个h一个o
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (oCount == 1) {
                condition.await();
            }
            oCount++;
            if (hCount == 2 && oCount == 1) {
                hCount = 0;
                oCount = 0;
            }
            releaseOxygen.run();
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
