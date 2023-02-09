package Basic.多线程.lc;

/**
 * synchronized + 标志位 + 唤醒
 */
public class FooBar3 {
    private volatile boolean flag = true; // 可以用boolean转换位

    private final Object object = new Object();

    private int n;

    public FooBar3(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (object) {
                while (!flag) {
                    object.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = false;
                object.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (object) {
                while (flag) {
                    object.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = true;
                object.notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FooBar3 fooBar3 = new FooBar3(2);
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        fooBar3.foo(() -> {
                            System.out.println("foo");
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            fooBar3.bar(() -> {
                System.out.println("bar");
            });
        }
    }
}
