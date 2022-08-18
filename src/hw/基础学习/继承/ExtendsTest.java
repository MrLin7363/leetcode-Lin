package hw.基础学习.继承;

public class ExtendsTest {

    public static void main(String[] args) {

        System.out.println("--------父类引用指向子类对象-----------");

        Father instance = new Son();
        instance.printValue();
        instance.printValue2();
        System.out.println(instance.getClass());
        // 通过父类变量引用子类对象，方法签名相同会引用子类的（除非是静态方法或者子类没有），属性相同会继续引用父类
        System.out.println(instance.name);// name引用father的
        System.out.println(instance.staticName);
        System.out.println(Father.staticName);
        instance.printValue3();


        System.out.println("----------创建子类对象------------");

        // 通过子类构造对下，方法签名相同会引用子类的（除非子类没有），属性相同会继续引用子类的，(除非子类没有)
        Son son = new Son();
        son.printValue();
        son.printValue2();
        System.out.println(son.name);
        System.out.println(son.name2);
        System.out.println(son.staticName4);
        System.out.println(son.staticName2);
        System.out.println(son.staticName);
        son.printValue3();

        System.out.println("---------创建父类对象-----------");

        // 通过父类构造对，不能引用子类的方法或属性
        Father father = new Father();
        System.out.println(father.getClass());
        father.printValue();
        father.printValue2();
        System.out.println(father.name);
        System.out.println(father.staticName);
        father.printValue3();
    }

}

class Father {

    public String name = "father";
    public String name2 = "father";

    public static String staticName = "staticName-father";

    public  String staticName4 = "staticName4-father";

    public void printValue() {
        System.out.println("this is father's printValue() method.---"+this.name);
    }

    public void printValue2(){
        System.out.println("this is father's printValue2() method.---"+this.name);
        System.out.println(getClass());
    }

    public static void  printValue3(){
        System.out.println("this is father's static printValue3() method.");
    }

}

class Son extends Father {

    public String name = "Son";

    public static String staticName2 = "staticName2-son";

    public static String staticName = "staticName-son";



    public void printValue() {
        System.out.println("this is Son's printValue() method.---"+this.name);
    }

    public static void  printValue3(){
        System.out.println("this is Son's static printValue3() method.");
    }

    public static void  printValue4(){ // 通过父类引用不了，只能父类有
        System.out.println("this is Son's static printValue4() method.");
    }
}
