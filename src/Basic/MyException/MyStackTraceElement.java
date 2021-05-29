package Basic.MyException;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/18 23:47
 * @Describe:
 */
public class MyStackTraceElement {
    static int n=10;
    public static void main(String[] args) {
        while (n>=0){
            rec();
        }
    }

    private static void rec(){
        n--;
        System.out.println("rec");
        Throwable throwable=new Throwable();
        StackTraceElement[] frames= throwable.getStackTrace();
        for (StackTraceElement f:frames){
            System.out.println(f);
        }
    }
}
