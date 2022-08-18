package hw.基础学习.多线程;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class Foo {
    private Helper helper = null; // 多线程之间不可见，只用记得这个就行
    public Helper getHelper() {
        if (helper == null) { // 这里一开始取到是null
            synchronized (this) {
                if (helper == null) {
                    // 错误是因为这里赋值后，并释放锁，上一个也是null的会再创建一次
                    System.out.println("create");
                    helper = new Helper(42);
                }
            }
        }
        return helper;
    }
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        Foo foo = new Foo();
        Foo foo2 = new Foo();
        System.out.println(foo==foo2);
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                System.out.println(foo.getHelper().getI());
            });
        }
        System.out.println("end");
        pool.shutdown();
    }
}
