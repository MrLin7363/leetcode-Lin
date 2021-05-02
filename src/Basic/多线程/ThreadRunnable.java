package Basic.多线程;

/**
 * @author: Junlin Chen
 * @Date: 2021/01/27 21:47
 * @Describe:
 */
public class ThreadRunnable implements Runnable {
    private String taskName;
    public ThreadRunnable() {}
    public ThreadRunnable(final String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("runnable = "+ ThreadDemo.currentThread().getName());
    }

}
