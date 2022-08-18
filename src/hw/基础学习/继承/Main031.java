package hw.基础学习.继承;

import java.util.Arrays;
import java.util.List;

public class Main031 {
    public static <T> T cast(Object obj) {
        return (T) obj; // line 1  这里有问题，未受检查的obj转化为T
    }

    public static void print(Object obj) {
        if (obj instanceof List) {
            List<String> list = Main031.<List<String>>cast(obj); // line 2
            System.out.println(list);
        } else {
            Object cast = Main031.cast(obj);
            String msg = Main031.<String>cast(obj); // line 3
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        List<String> msg = Arrays.asList("hello", "world"); // line 4
        print(msg);
        System.out.println(msg);
    }
}
