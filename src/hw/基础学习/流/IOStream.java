package hw.基础学习.流;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOStream {
    /**
     * 关闭流只需要关闭最外层的包装流，其他流会自动调用关闭，这样可以保证不会抛异常
     */
    public static void main(String[] args) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("675"); // 被包装流
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);// bufferedOutputStream包装流

        bufferedOutputStream.write("test write something".getBytes());
        bufferedOutputStream.flush();

        //从包装流中关闭流
        bufferedOutputStream.close();
        bufferedOutputStream.write(8);
    }
}
