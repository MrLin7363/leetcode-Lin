package hw.基础学习.second;

public class FinallyTest {

    /**
     * 总结：
     * try语句在返回前，将其他所有的操作执行完，保留好要返回的值，而后转入执行finally中的语句，而后分为以下三种情况：
     *
     * 情况一：如果finally中有return语句，则会将try中的return语句”覆盖“掉，直接执行finally中的return语句，得到返回值，这样便无法得到try之前保留好的返回值。
     *
     * 情况二：如果finally中没有return语句，也没有改变要返回值，则执行完finally中的语句后，会接着执行try中的return语句，返回之前保留的值。
     *
     * 情况三：如果finally中没有return语句，但是改变了要返回的值，这里有点类似与引用传递和值传递的区别，分以下两种情况，：
     *
     * 1）如果return的数据是基本数据类型或文本字符串，则在finally中对该基本数据的改变不起作用，try中的return语句依然会返回进入finally块之前保留的值。
     *
     * 2）如果return的数据是引用数据类型，而在finally中对该引用数据类型的属性值的改变起作用，try中的return语句返回的就是在finally中改变后的该属性的值。
     * ————————————————
     * 版权声明：本文为CSDN博主「有何不可Pp」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/a17633040107/article/details/103709774
     */
    private int x;
    public static void main(String[] args) {
        System.out.println(test1().x);//30
        System.out.println(test2());//200
        System.out.println(test3());//300
        System.out.println(test4());//300
        System.out.println(test5().x);//10
        System.out.println(test6());//
        StringBuilder sb = new StringBuilder("iphone");
        foo(sb);
        System.out.println(sb); // sb不变易错，如果是build直接build会变
    }
    private static void foo(StringBuilder builder) { // builder是个局部变量，局部变量值改变不影响外面的
        builder = new StringBuilder("ipad");
        builder.append("qwd");
    }

    // 相当于有引用类型改变 30
    public static FinallyTest test1(){
        FinallyTest p = new FinallyTest();
        try{
            p.x = 20;
            return p;
        }
        finally{
            p.x =30;
        }
    }
    // 基本类型修改无作用
    public static int test2(){
        int a =100;
        try{
            a = 200;
            return a;
        }
        finally{
            a = 300;
        }
    }

    public static int test3(){
        int a =100;
        try{
            a = 200;
            return a;
        }
        finally{
            a = 300;
            return a;
        }
    }

    public static int test4(){
        int a =100;
        try{
            a = 200;
            return a;
        }
        finally{
            return 300;
        }
    }
    // 10
    private static FinallyTest test5() {
        FinallyTest test = new FinallyTest();
        try {
            test.x = 10;
            return test;// 返回的还是之前那个对象的引用地址
        } catch (Exception e) {
            test.x = 1;
        } finally {
            test = new FinallyTest(); // 之前的引用指向新的地址，但是原先地址不变
        }
        return test;
    }

    // s-finally
    private static String test6() {
        String s = "s";
        try {
            s = "s-try";
            throw new Exception();
        } catch (Exception e) {
            s = "s-catch";
            return s;
        } finally {
            s = "s-finally";
            return s;
        }
    }
}
