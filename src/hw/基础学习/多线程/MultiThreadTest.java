package hw.基础学习.多线程;


public class MultiThreadTest {
    private static final int TENK = 10000;
    private static long count = 0;
    private void add10K() {
        int idx = 0;
//        System.out.println(Thread.currentThread().getName()+"idx="+idx);
        while(idx++ < TENK) {
            count += 1;
//        System.out.println(Thread.currentThread().getName()+"count="+count); // 如果加上这个就是不是一万~两万
        }
    }
    public static long calc() throws InterruptedException {
        final MultiThreadTest test = new MultiThreadTest();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(test::add10K);
        Thread th2 = new Thread(test::add10K);
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calc());// 10000~20000之间
    }
}
