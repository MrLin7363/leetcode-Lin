package Basic.多线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author: Junlin Chen
 * @Date: 2021/01/27 21:41
 * @Describe:
 */
public class ThreadDemo extends java.lang.Thread {
    public void run() {
        System.out.println("thread = "+ java.lang.Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new ThreadDemo().start();
        Thread run1=new Thread(new ThreadRunnable());
        run1.start();

        Callable callable=new ThreadCallble();
        FutureTask<Object> onetask=new FutureTask<>(callable);
        Thread run2=new Thread(onetask);
        run2.start();
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Callable callable2=new ThreadCallble();
            FutureTask<Object> onetask2=new FutureTask<>(callable2);
            Thread run3=new Thread(onetask2);
            executorService.execute(run3);
        }
        executorService.shutdown();
    }

}
