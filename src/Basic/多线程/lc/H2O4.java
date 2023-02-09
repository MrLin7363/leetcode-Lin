package Basic.多线程.lc;

/**
 * synchronized 非公平锁  只会输出 HHO
 */
public class H2O4 {

    private volatile int state = 0;

    private Object obj = new Object();

    public H2O4() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (obj) {
            while (state == 2) {
                obj.wait();
            }
            state++;
            releaseHydrogen.run();
            obj.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (obj) {
            while (state != 2) {
                obj.wait();
            }
            state = 0;
            releaseOxygen.run();
            obj.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        H2O4 h2O4 = new H2O4();
        Runnable runnable = () -> System.out.println("H");
        Runnable runnable3 = () -> System.out.println("O");
        new Thread(() -> {
            try {
                h2O4.oxygen(runnable3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                h2O4.hydrogen(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                h2O4.hydrogen(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
