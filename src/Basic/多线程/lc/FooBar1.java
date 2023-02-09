package Basic.多线程.lc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BLOCKING Queue
 */
public class FooBar1 {
    private int n;

    private BlockingQueue<Integer> bar = new LinkedBlockingQueue<>(1);

    private BlockingQueue<Integer> foo = new LinkedBlockingQueue<>(1);

    public FooBar1(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.put(i); // 阻塞在放不进去
            printFoo.run();
            bar.put(i);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.take();
            printBar.run();
            foo.take();
        }
    }
}
