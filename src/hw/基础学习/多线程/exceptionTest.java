package hw.基础学习.多线程;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class exceptionTest {
    private static final int CORE_POOL_SIZE = 5;

    private static final long THREAD_ALIVE = 15L;

    public static void main(String[] args) {

//        try {
//        }catch (Exception e){
//        }catch (NullPointerException e){  // 子类异常必须在父类catch之前声明
//        }
        //        test1();
        //        test2();
        test3();
    }

    private static void test1() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, CORE_POOL_SIZE + 1, THREAD_ALIVE,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<String> getewayList = new ArrayList<>(10);
        // 多线程并发执行
        List<String> resList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1; i++) {
            getewayList.add(String.valueOf(i));
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String temp = null;
        AtomicInteger i = new AtomicInteger(0);
        try {
            getewayList.forEach(
                software -> {
                    threadPoolExecutor.execute(() -> {
                        i.getAndIncrement();
                        System.out.println("begin : " + software);
                        try {
                            // 这里抛出一个异常，如果不try catch 上层的那个抓不到，因为主线程已经成功运行到await()
                            // 所以上层的catch日志打印不出来，虽然会抛异常，但是日志系统记录不到
                            if (temp.equals(software)) {
                                System.out.println("fail!");
                                countDownLatch.countDown();
                                return;
                            }
                        } catch (Exception e) {
                            // 子线程的异常要另外写在线程池运行里
                            System.out.println("fail : " + software);
                        }
                        System.out.println("success : " + software);
                        resList.add(software);
                        countDownLatch.countDown();
                    });
                }
            );
            countDownLatch.await();
        } catch (Exception e) {
            // 这里抓不到线程池的异常打印不出来
            System.out.println("-error " + e);
        } finally {
            threadPoolExecutor.shutdown();
        }
        System.out.println("test1 ------------------------------------ finally");
    }

    private static void test2() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, CORE_POOL_SIZE + 1, THREAD_ALIVE,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<String> resultList = new ArrayList<>(10);
        for (int i = 0; i < 1; i++) {
            resultList.add(String.valueOf(i));
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String temp = null;
        AtomicInteger i = new AtomicInteger(0);
        try {
            resultList.forEach(
                software -> {
                    threadPoolExecutor.execute(() -> {
                        i.getAndIncrement();
                        System.out.println("begin : " + software);
                        // 这里抛出一个异常，如果不try catch 上层的那个抓不到，因为主线程已经成功运行到await()
                        // 所以上层的catch日志打印不出来，虽然会抛异常，但是日志系统记录不到
                        if (temp.equals(software)) {
                            System.out.println("fail!");
                            countDownLatch.countDown();
                            return;
                        }
                        System.out.println("success : " + software);
                        countDownLatch.countDown();
                    });
                }
            );
            countDownLatch.await();
        } catch (Exception e) {
            // 这里抓不到线程池的异常打印不出来
            System.out.println("-error " + e);
        } finally {
            threadPoolExecutor.shutdown();
        }
        System.out.println("test2 ------------------------------------ finally");
    }

    private static void test3() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, CORE_POOL_SIZE + 1, THREAD_ALIVE,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        List<String> resultList = new ArrayList<>(10);
        for (int i = 0; i < 1; i++) {
            resultList.add(String.valueOf(i));
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String temp = null;
        AtomicInteger i = new AtomicInteger(0);
        try {
            resultList.forEach(
                software -> {
                    // 相当于try catch的功能
                    CompletableFuture.runAsync(() -> {
                        i.getAndIncrement();
                        System.out.println("begin :  thread name = " + Thread.currentThread().getName());
                        testReturn();
                        if (i.get() == 1) {
                            System.out.println("return " + software);
                            return;
                        }
                        System.out.println("success : " + software);
                    }, threadPoolExecutor).thenRunAsync(() -> {
                        System.out.println("then run1   thread name = " + Thread.currentThread().getName());
                    }, threadPoolExecutor).thenRunAsync(
                        () -> {
                            System.out.println("then run2 thread name = " + Thread.currentThread().getName());
                            if (temp.equals(software)) {
                                // 下面的其实没用，因为抛异常不会走下来
                                System.out.println("fail!");
                                countDownLatch.countDown();
                                return;
                            }
                        }
                    ).whenComplete((result, exception) -> {
                        System.out.println("complete ");
                        if (exception != null) {
                            exception.printStackTrace();
                            // 有异常
                            System.out.println("exception :" + exception.getMessage());
                        }
                        System.out.println("complete finally");
                        countDownLatch.countDown();
                    });
                }
            );
            countDownLatch.await();
        } catch (Exception e) {
            // 这里抓不到线程池的异常打印不出来
            System.out.println("-error " + e);
        } finally {
            threadPoolExecutor.shutdown();
        }
        System.out.println("test3 ------------------------------------ finally");
    }

    private static void testReturn() {
        int i = 0;
        if (i != 1) {
            System.out.println("return");
            return;
        }
        System.out.println("finally");
    }
}
