package Basic.JVM;

/**
 * @author: chenjunlin
 * @since: 2021/06/21
 * @descripe:  VM options: -XX:+PrintGCDetails
 */
public class GcTest {
    public static void main(String[] args) {
        if (true) {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
            System.out.println(placeHolder.length / 1024);
        }
        System.gc();
    }
}
