package hw.基础学习.基础特性;

import Basic.Effective.MyException;

public class ExTest {

    public static void main(String[] args) {
        try {
            System.out.println(func()); // 无异常
        } catch (RuntimeException ex) {
            // 处理异常
        }
    }
    public static int func() throws RuntimeException {
        for (int i = 1; i < 3; i++) {
            try {
                throw new RuntimeException();
            } finally {
                continue; // 不推荐
            }
        }
        return 0;
    }
}
