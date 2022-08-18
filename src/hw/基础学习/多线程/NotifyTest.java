package hw.基础学习.多线程;

public class NotifyTest {
    private static Object object=new Object();

    public static void main(String[] args) {
        test();
        System.out.println("asdqw");
        object.notify();// 这里要在同步快中不然抛错误为走不到这里，一直wati中
    }

    private static  void test() {
        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
