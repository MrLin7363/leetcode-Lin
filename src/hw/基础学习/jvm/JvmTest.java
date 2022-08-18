package hw.基础学习.jvm;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public class JvmTest{
    public static JvmTest instance = null;
    public void isAlive() {
        System.out.println("alive");
    }
    protected void finalize() throws Throwable {
        super.finalize();
        JvmTest.instance = this;
    }
    public static void main(String[] args) throws Throwable {
        // false的话会重写txt
        FileOutputStream outputStream = new FileOutputStream("test.txt",true);
        outputStream.write("ABCDE".getBytes(StandardCharsets.UTF_8));
        outputStream.close();

        JvmTest.getFormatTimeStr(1000);

        instance = new JvmTest();
        instance = null;
        System.gc();
        Thread.sleep(500);
        if (instance != null) {
            instance.isAlive();
        } else {
            System.out.println("dead");
        }
        instance = null;
        System.gc();
        Thread.sleep(500);
        if (instance != null) {
            instance.isAlive();
        } else {
            System.out.println("dead");
        }
    }

    private static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<>();
    static {

        System.out.println("static");
        dateFormatter.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    public static String getFormatTimeStr(long time) {
        return dateFormatter.get().format(time);
    }

}
