package Basic.file;

import java.io.File;

/**
 * @author: Junlin Chen
 * @Date: 2021/07/09 23:52
 * @Describe:
 */
public class FileTest {

    public static void main(String[] args) {
//        File file = new File("D:/学习资料");
        String directory = "E:\\test";
        String filename = "test.txt";

        //2.  创建文件夹对象     创建文件对象
        File file = new File(directory);
        //如果文件夹不存在  就创建一个空的文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(directory, filename);

    }
}
