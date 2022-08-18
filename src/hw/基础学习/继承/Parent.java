package hw.基础学习.继承;

import java.util.Locale;

/**
 * 类初始化过程：
 * 1. 父类静态变量
 * 2. 父类静态代码块（按代码顺序加载）
 * 3. 子类静态变量
 * 4. 子类静态代码块（按代码顺序加载）
 * 5. 父类普通变量
 * 6. 父类普通代码块（按代码顺序加载）
 * 7. 父类构造函数 -- 在普通代码块加载完
 * 8. 子类普通变量
 * 9. 子类普通代码块（按代码顺序加载）
 * 10. 子类构造函数 -- 在普通代码块加载完
 * 11. 方法的调用(被调用的时候才调)
 *
 * 这些变量要在使用之前声明，不然使用不了
 */
public class Parent {
    static int firstVar = 1;
    static {
        System.out.println("Class Parent:static blocks" + firstVar);
        firstVar++;
    }
    static {
        System.out.println("Class Parent:static blocks" + firstVar);
        firstVar++;
    }
    public Parent() {
        System.out.println("constructor Parent: firstVar=" + firstVar + ",secondVar=" + secondVar);
        firstVar++; secondVar++;
//        bDisplay();// 如果
    }
    private int secondVar = 1;
    {
        System.out.println("Class Parent:common blocks firstVar=" + firstVar + ",secondVar=" + secondVar);
        firstVar++; secondVar++;
    }
    // 非静态方法
    public void bDisplay() {
        System.out.println("Class Parent:static void bDisplay(): " + "firstVar=" + firstVar + ",secondVar=" + secondVar);
        secondVar++; firstVar++;
    }
    // 静态方法
    public static void bTest() {
        System.out.println("Class Parent:static void bTest(): firstVar=" + firstVar);
        firstVar++;
    }
}
class Child extends Parent {
    static int firstVar = 1;
    static {
        System.out.println("Class child:static blocks" + firstVar);
        firstVar++;
    }
    static {
        System.out.println("Class child:static blocks" + firstVar);
        firstVar++;
    }
    int secondVar = 1;
    {
        System.out.println("Class child:common blocks firstVar=" + firstVar + ",secondVar=" + secondVar);
        firstVar++;
        secondVar++;
    }
    public Child() {
//        super(); // 如果调了super()而父类里调了子类重写的方法，会走到子类可能报错,name没初始化
        System.out.println("constructor child: firstVar=" + firstVar + ",secondVar=" + secondVar);
        firstVar++; secondVar++;
    }
    public static void aTest() {
        System.out.println("Class child:static void aTest(): firstVar=" + firstVar);
        firstVar++;
    }
    public void aDisplay() {
        System.out.println("Class child:static void aDisplay():  firstVar=" + firstVar + ",secondVar=" + secondVar);
        firstVar++;
    }
    private String name;
    public void bDisplay() {
        System.out.println(name.toUpperCase(Locale.ENGLISH));
    }
    public static void main(String[] args) {
        Child child = new Child();
        child.aDisplay();
    }
}
