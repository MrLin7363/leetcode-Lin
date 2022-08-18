package hw.基础学习.基础特性;

public class JingduTest {
    /**
     * 损失精度的转型，全都向0靠拢
     *
     */
    public static void main(String[] args) {
//        for (byte i = -128; i < 128; i++) {
//            // 死循环，当i=127 的时候，  i++ 转为byte -> -127再循环一次
//            System.out.println(i);
//        }

        double fst = 4d;
        double snd = 0.99d;
        double trd = -99d;
        double d1 = 1.2;
        double d2 = -0.2;
        double d3 = 2147483650d;
        System.out.println((int)fst);
        System.out.println((int)snd);
        System.out.println((int)trd);
        System.out.println((int)d1);
        System.out.println((int)d2);
        System.out.println((int)d3);// d3损失精度变成int的最大值

        float f1=1.2f;
        float f2=-1.2f;
        float f3=-0.4f;
        System.out.println((int)f1);
        System.out.println((int)f2);
        System.out.println((int)f3);
    }
}
