package Basic.多线程.lc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue
 */
public class H2O5 {
    private BlockingQueue hBlockQueue = new LinkedBlockingQueue<>(2);

    private BlockingQueue oBlockQueue = new LinkedBlockingQueue<>(1);

    private volatile int count = 0;

    public H2O5() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hBlockQueue.put(1);
        releaseHydrogen.run();
        count++;
        if (count == 3) {
            count = 0;
            hBlockQueue.clear();
            oBlockQueue.clear();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oBlockQueue.put(1);
        releaseOxygen.run();
        count++;
        if (count == 3) {
            count = 0;
            hBlockQueue.clear();
            oBlockQueue.clear();
        }
    }
}
