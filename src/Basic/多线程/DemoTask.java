package Basic.多线程;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: chenjunlin
 * @since: 2021/05/17
 * @descripe: 启动3个线程打印递增的数字, 线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15. 接着再由线程1打印16,17,18,19,20…
 * 竞争型：每个线程都抢着去打印，如果发现不该自己打印，则准备下一轮抢。由于大家都是竞争的，因此需要用锁机制来保护。
 * 协同型：当前线程线程打印之后通知下一个线程去打印，这种需要确认好第一个线程打印时机。由于是协同型的因此可以不用锁机制来保护，但是需要一个通知机制。
 */
public class DemoTask implements Runnable {
    /**
     * 竞争型打印
     * 多个线程竞争型打印，优势是代码简单易懂，劣势是线程争抢是CPU调度进行的，可能该某个线程打印时结果该线程迟迟未被CPU调度，结果其他线程被CPU调度到但是由于不能执行打印操作而继续争抢，造成CPU性能浪费
     */
    // 这里将lock对象换成 Lock(ReentrantLock) 进行lock/unlock也是可以的
    /*private static final Object lock = new Object();
    private static final int MAX = 14;
    private static int current = 1;

    private int index;

    public DemoTask(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        while (current <= MAX) {
            synchronized (lock) {
                if ((current <= MAX) && (current % 3 == index)) {
                    System.out.println(current++);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        List<Thread> threadList = Arrays.asList(
                new Thread(new DemoTask(0)),
                new Thread(new DemoTask(1)),
                new Thread(new DemoTask(2))
        );

        threadList.forEach(Thread::start);

        // 等待线程死亡,关闭
        for (Thread thread : threadList) {
            thread.join();
        }
    }*/

    /**
     * 协同型打印
     * 多个线程协同型打印，优势是各个线程使用“通知”机制进行协同分工，理论上执行效率较高，不过要使用对应的“通知”机制。关于如何“通知”，
     * 第一种是可使用Java对象的 wait/notify 或者Conditon对象的 await/signal ，第二种是以事件或者提交任务的方式（比如通过提交“待打印数字”这个任务给下一个线程）。
     * 下面以第二种方式进行代码分析，比如当前线程通过submit给下一个线程一个“待打印数字”的任务，这样很容易想到使用只包含1个线程的线程池来实现
     */


    // 主线程要等待线程打印完毕，使用CountDownLatch通知机制
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static List<ExecutorService> threadList = new ArrayList<>();
    private static final int MAX = 50;
    private static int current = 1;

    @Override
    public void run() {
        if (current <= MAX) {
            System.out.println(Thread.currentThread().getName() + "执行" + current++);
            threadList.get(current % threadList.size()).submit(new DemoTask()); // 提交给下一个线程去执行任务
        } else {
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            threadList.add(Executors.newFixedThreadPool(1));
        }
        // 提交给0线程去执行任务
        threadList.get(0).submit(new DemoTask());
        // 调用countDownLatch 能让主线程等待三个线程执行完打印，再走await()以下的代码
        countDownLatch.await();
        System.out.println("主线程");
        // 关闭线程池
        threadList.forEach(ExecutorService::shutdown);
    }


}
