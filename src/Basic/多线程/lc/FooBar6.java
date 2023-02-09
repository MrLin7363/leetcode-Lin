package Basic.多线程.lc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 控制先后
 */
public class FooBar6 {
    private int n;

    public FooBar6(int n) {
        this.n = n;
    }

    CyclicBarrier cb = new CyclicBarrier(2);

    volatile boolean fin = true;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!fin)
                ;
            printFoo.run();
            fin = false;
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                cb.await(); // 计数每次达到2重置计数
            } catch (BrokenBarrierException e) {
            }
            printBar.run();
            fin = true;
        }
    }
}
