package hw.基础学习.基础特性;

public class IaddTest {
    /**
     * • i++ 即后加加，原理是：先自增，然后返回自增之前的值  (计算完表达式再++)
     * • ++i 即前加加，原理是：先自增，然后返回自增之后的值（计算前就++）
     *
     * 一个变量也是表达式，多个表达式的加减法运算都是从左到右进行的
     */
    public static void main(String[] args) {
        int a = 2;
        int b = (3 * a++) + a;
        System.out.println(b);

        int a1 = 2;
        int b1 = a1 + (3 * a1++);
        System.out.println(b1);

        int i = 1; int j = 1; int k = i++ + ++i + ++j + j++;
        System.out.println(k);

    }
}
