 

package hw.基础学习;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/24
 **/
public class Syc extends Thread{
    boolean stop=false;
    int count=0;

    public static void main(String[] args) throws InterruptedException {
        Syc thread= new Syc();
        thread.start();
        Thread.sleep(3000);
        System.out.println("first");
        thread.interrupt();
        System.out.println("second");
        Thread.sleep(3000);
        System.out.println("stopping ");
    }

    public void run() {
        while (!stop&& count<100) {
            System.out.println("running");
            count++;
        }
        System.out.println("end");
    }
}
