package Basic.多线程;

import java.util.concurrent.Callable;

/**
 * @author: Junlin Chen
 * @Date: 2021/01/27 21:50
 * @Describe:
 */
public class ThreadCallble implements Callable<Object> {

    @Override
    public Object call() throws Exception {
        System.out.println("Callable = "+ ThreadDemo.currentThread().getName());
        return null;
    }
}
