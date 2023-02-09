package Basic.多线程.lc;

/**
 * 自旋 + 让出CPU
 */
public class FooBar5 {
    private int n;

    public FooBar5(int n) {
        this.n = n;
    }

    volatile boolean permitFoo = true;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; ) {
            if(permitFoo) {
                printFoo.run();
                i++;
                permitFoo = false;
            }else{
                Thread.yield(); // 线程让步:让自己或其他线程再次共同竞争CPU使用权
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; ) {
            if(!permitFoo) {
                printBar.run();
                i++;
                permitFoo = true;
            }else{
                Thread.yield();
            }
        }
    }
}
