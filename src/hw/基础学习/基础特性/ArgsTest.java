package hw.基础学习.基础特性;

public class ArgsTest {

    public static void main(String[] args) {
        String[] s = new String[2];
        s[0] = "12e";
        s[1] = "1qd";
        test("", s);
    }

    // ... 参数的必须在最后，否则编译错误
    //4. 一个方法的参数列表,只能有一个可变参数
    //　　5. 如果方法的参数有多个,那么可变参数必须写在参数列表的末尾
    private static void test(String sq, String... args) {

        System.out.println(args[0]);
    }
}
