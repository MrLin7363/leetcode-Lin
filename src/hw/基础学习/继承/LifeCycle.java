package hw.基础学习.继承;

/**
 * 以下为编程规范要求的顺序
 * 静态属性/静态代码块（看代码顺序） -> 普通属性/普通代码块(看代码顺序) -> 构造函数
 */
public class LifeCycle {
    // 静态属性
    private static String staticField = getStaticField();
    // 静态方法块
    static {
        System.out.println(staticField);
        System.out.println("静态方法块初始化");
        System.out.println("Static Patch Initial");
    }
    // 普通属性
    private String field = getField();
    // 普通方法块
    {
        System.out.println(field);
        System.out.println("普通方法块初始化");
        System.out.println("Field Patch Initial");
    }
    // 构造函数
    public LifeCycle() {
        System.out.println("构造函数初始化");
        System.out.println("Structure Initial ");
    }

    public static String getStaticField() {
        String statiFiled = "Static Field Initial";
        System.out.println("静态属性初始化");
        return statiFiled;
    }

    public static String getField() {
        String filed = "Field Initial";
        System.out.println("普通属性初始化");
        return filed;
    }

    public String getField2() {
        System.out.println("普通方法初始化");
        return "";
    }

    public static String getField3() {
        System.out.println("静态方法初始化");
        return "";
    }

    // 主函数
    public static void main(String[] argc) {
        new LifeCycle();
    }
}
