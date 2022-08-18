package hw.基础学习.多线程;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledPoolExceptionTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        schedule(executor);
        System.out.println("asd");
        executor.shutdown();
        System.out.println("end");
    }

    static void schedule(ScheduledThreadPoolExecutor executor) {
        executor.schedule(() -> {
            try {
                int n = 1 / 0;
            } catch (Exception e) {
                System.out.println(e);
            }
        }, 4, TimeUnit.SECONDS);
    }
}
