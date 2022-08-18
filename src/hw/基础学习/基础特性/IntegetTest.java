package hw.基础学习.基础特性;

import java.math.BigDecimal;

public class IntegetTest {

    /*
    除了 new Integer 其他都是使用-128~127缓存地址
     */
    public static void main(String[] args) {
        int ai=1;
        Integer a1 = 1;
        Integer b1 = 1;
        System.out.println(ai==a1); // true
        System.out.println(a1 == b1);// true

        Integer c1 = 200; // 超过缓存数据，新建一个内存地址
        Integer d1 = 200;
        System.out.println(c1 == d1); // false 比较内存地址
        System.out.println(c1.equals(d1)); // 转为基本类型再比较
        System.out.println(c1==200);// true 直接拆箱与200比较

        Integer g1 = Integer.valueOf(1); // 使用缓存中 -128~127的地址
        System.out.println(a1 == g1); // true
        Integer z1 = Integer.valueOf(200); // 超过缓存数据，新建一个内存地址
        System.out.println(d1==z1); // false

        Integer h1 = new Integer(1); // 新的内存地址
        System.out.println(a1 == h1);// false

        Integer e1 = new Integer(200);// 新的内存地址
        Integer f1 = Integer.valueOf(200);// 新的内存地址
        System.out.println(e1 == f1);// false

        double i=1.011123d;
        float dd=20.f;

        System.out.println(dd);

        // 不会创建对象的是
        Integer integer = Integer.valueOf("4");// 缓存内，不创建对象，使用缓存，如果是字符串先调用parseInt,超过缓存就是对象
        Integer integer1= Integer.valueOf("1554"); // 创建新对象
        System.out.println(integer==integer1);
        Integer.parseInt("5");// 返回int 类型，不是对象
        Integer i3 = new Integer("6");// 创建对象
        System.out.println(i3);

        System.out.println(Math.abs(Integer.MAX_VALUE + 1));

        Integer d = 3;
        Integer e = 2;
        Integer f = 1;
        Long g = 3L;
        Long h = 2L;
        int i1 = e + f;
        long l = f + h;
        System.out.println(g==(e+f)); // true
        System.out.println(g.equals(f+e)); // false  equals后面的类型如果不是和前面类型一致直接返回false，因为没有重写
        System.out.println(g.equals(f+h)); // true

        System.out.println(Integer.MAX_VALUE+1); // -2147483648
        System.out.println(Math.abs(Integer.MAX_VALUE+1)); // -2147483648
        System.out.println(Math.abs(-1));// 1
        // abs只有在非Math.Min  最小时才有效
        System.out.println(Math.abs(-2147483647));// 2147483647
        System.out.println(Math.abs(Integer.MIN_VALUE));// -2147483648 最小值没有变化

//        System.out.println(Math.multiplyExact(2147483647,323));// 溢出，抛异常
        System.out.println(2147483647*323);// 溢出不抛异常
        System.out.println(2147483647+32312312);// 溢出但是没有抛异常，编程规范不允许  超过溢出部分从MinValue开始

        Integer iii=2147483647;
        System.out.println(iii+222);//-2147483427

        String str = "123";
        String s2 = str + 2147483648865l; // 不加后缀默认后面的数值是int类型
//        String s3 = str + 2147483648; // 2147483648超过int范围编译报错
        System.out.println(s2);

        int jjj=1;
        jjj+=1.8;
        System.out.println(jjj);



    }
}
