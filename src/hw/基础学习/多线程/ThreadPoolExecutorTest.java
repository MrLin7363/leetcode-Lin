package hw.基础学习.多线程;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        // 因为是无界阻塞队列，所以不会创建大于核心线程数的线程，一直是2
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 2,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 100000; i++) {
            threadPoolExecutor.execute(() -> {
                System.out.println("thread name = " + Thread.currentThread().getName());
            });
        }
    }
}
