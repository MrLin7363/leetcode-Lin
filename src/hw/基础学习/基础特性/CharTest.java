package hw.基础学习.基础特性;

public class CharTest {

    public static void main(String[] args) {
        char a='a';
        int i=96;
        //规则1，定义了数据类型的变量与未定义变量的数值，结果自动转换为精度高的  -  取最大类型
        System.out.println(2==2?i:9.0); //输出 96.0

        //jvm给数值分配的数据类型，98并不是int类型的，而是byte/short(反编译字节码文件可看出)，故结果会变为ASCII码98对应的字符
        //java规范中提到 若两个操作数中有一个是常量数字S,另外一个是表达式，且其类型为T,那么，若数字S在T的范围内，則转换为T类型；
        //若S超出了T类型的范围，则T转换为S类  因为98是常量，可以被char表示，输出的结果是char型的，所以是x。
        System.out.println(2==2?98:a); //输出 b
        System.out.println(2==2?65535:a); //输出 字符  char 类型
        System.out.println(2==2?65536:a); //输出 65536  int 类型
        System.out.println(false ? 2147483647L : a);//此时输出的是long类型  输出 97


        //规则2，两个已经定义数据类型的变量，结果自动转换为精度高的
        System.out.println(2==2?a:i); //输出 97  int 类型
        //规则3，两个常量，结果自动转换为精度高的
        System.out.println(2==2?99:9.0); //输出 99.0
    }
}
