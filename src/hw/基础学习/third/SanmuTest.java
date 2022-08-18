package hw.基础学习.third;

public class SanmuTest {
    public static void main(String[] args) {
        char alpha = 'A';
        int foo = 1;
        boolean trueExp = true;
        short sh=1;
        byte b=12;
        // 三目运算符统一后会和后面那个比较
        System.out.println(trueExp ? alpha : '1');// A
        System.out.println(trueExp ? alpha : "1234");// A
        System.out.println(trueExp ? alpha : 39999);// A
        System.out.println(trueExp ? alpha : 65535);// A   <65536  <char的范围，取char类型
        System.out.println(trueExp ? alpha : 65536);// 65
        System.out.println(trueExp ? alpha : foo);// 65  int型范围更大
        System.out.println(trueExp ? alpha : 123L);// 65
        System.out.println(trueExp ? alpha : sh); // 65 类型上转为int
        System.out.println(trueExp ? alpha : b); // 65  类型上转为int

    }
}
