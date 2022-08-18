package hw.基础学习.多线程;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class NotifyAllTest {
    /**
     * logger：日志处理器
     * isWait：作为所有线程 wait 等待与 notify、notifyAll 通知的同一对象
     */
    public static final Logger logger = Logger.getAnonymousLogger();
    public static final Boolean isWait = true;

    public static void main(String[] args) throws InterruptedException {

        /**
         * 使用无界线程池创建多个子线程并启动它们
         * 调用 wait 与 调用 notify、notifyAll 必须是同一个对象，但可以是任意对象，比如普通的 String 、Array 、Boolean 等等
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyThread(isWait));
        }
        // 睡5秒钟
        for (int i = 5; i > 0; i--) {
            Thread.sleep(1000);
            logger.info("线程 " + Thread.currentThread().getName() + " " + (i) + " 秒后唤醒子线程");
        }
        //Java 强制要求必须在同步代码块中调用 wait、notify、notifyAll
        //如果不在同步代码块中执行，则会抛出异常：java.lang.IllegalMonitorStateException
        synchronized (isWait) {
            isWait.notifyAll();//通知isWait对象的所有线程继续向后运行
        }
    }
}

class MyThread extends Thread {

    private static final Logger logger = Logger.getAnonymousLogger();

    private Boolean isWait;

    public MyThread(Boolean isWait) {
        this.isWait = isWait;
    }

    @Override
    public void run() {
        //Java 强制要求必须在同步代码块中调用 wait、notify、notifyAll
        //如果不在同步代码块中执行，则会抛出异常：java.lang.IllegalMonitorStateException
        synchronized (isWait) {
            try {
                logger.info("线程 " + currentThread().getName() + " 开始阻塞等待...");
                isWait.wait();
                logger.info("线程 " + currentThread().getName() + " 被重新唤醒...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
