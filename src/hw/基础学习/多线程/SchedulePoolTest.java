package hw.基础学习.多线程;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SchedulePoolTest {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(1);
        schedule(executor);
        System.out.println("qwe");
        executor.shutdown();
    }

    static void schedule(ScheduledThreadPoolExecutor executor){
        executor.schedule(()->{
            System.out.println("123");
            int n=1/0;
            System.out.println(n);
            System.out.println("schdule");
        },1, TimeUnit.SECONDS);
    }
}
