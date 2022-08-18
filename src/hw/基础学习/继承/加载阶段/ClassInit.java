package hw.基础学习.继承.加载阶段;

public class ClassInit {
        private static ClassInit init=new ClassInit();
        public static int x;
        public static int y=0;
        static{
            System.out.println("初始化static");
            x=10;
            y=10;
        }
        public ClassInit(){
            System.out.println("初始化构造方法");
            x++;
            y++;
        }
        public static ClassInit newInstance(){
            ClassInit init = ClassInit.init;// 如果用这个加载的 是new ClassInit()
            return init;
        }
    public static final String NAME = "我是final静态常量";
    public static String NAME2 = "我是非final静态常量";
    static{
        System.out.println("初始化Const类"); // ClassInit.NAME 只是直接访问静态变量不会初始化下面这个静态方法
    }
    //    public ClassInit() {
//        x = 2;
//        y = 2;
//    }
//
//    {
////        System.out.println(x);// 如果x在下面声明那么这里编译错，只能赋值不能使用
//        x = 2;
//        y = 2;
//    }
//
//    {
//        x = 3;
//        y = 3;
//    }
//
//    public int x;
//
//    public int y = 111;

}

