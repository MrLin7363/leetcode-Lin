package Basic.多线程.lc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Semaphore+CyclicBarrier
 */
public class H2O2 {
    private Semaphore semaphoreH = new Semaphore(2);

    private Semaphore semaphoreO = new Semaphore(1);

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public H2O2() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semaphoreH.acquire();
        releaseHydrogen.run();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {

        }
        semaphoreH.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semaphoreO.acquire();
        releaseOxygen.run();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {

        }
        semaphoreO.release();
    }
}
