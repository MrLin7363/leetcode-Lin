package hw.基础学习.多线程;

public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("qqq");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        if(Thread.currentThread().isInterrupted()) {
                            break;
                        }
                    }
                }
            }
        });
        thread.start();
//        Thread.sleep(2000);
        thread.interrupt(); // 用来中断处于阻塞状态的线程,sleep是阻塞，但是while不是阻塞
//        System.out.println("is:"+thread.isInterrupted()); // 如果是false就清理
//        System.out.println("is:"+thread.isInterrupted());
//        Thread.currentThread().interrupt();
//        System.out.println("is:"+Thread.interrupted()); // 如果是true就清理，清理默认值就是false非中断
//        System.out.println("is:"+Thread.interrupted());
//
//
//        Thread thread2 = new Thread() {
//            public void run() {
//                System.out.println("线程启动了");
//                try {
//                    Thread.sleep(1000 * 10);
//                } catch (InterruptedException e) {
//                    System.out.println("中断信号");
//                    e.printStackTrace();
//                }
//                System.out.println("线程结束了");
//            }
//        };
//        thread2.start();
//
//        try {
//            Thread.sleep(1000 * 2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("111");
//        thread2.interrupt();//作用是：在线程阻塞时抛出一个中断信号，这样线程就得以退出阻塞的状态

    }
}
