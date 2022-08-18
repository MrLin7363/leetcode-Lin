package hw.基础学习.流;

public class ClassTest {

    private int ss=1;

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("hw.基础学习.流.ClassTest");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.getName());
        Class<?> bClass = Class.forName("hw.基础学习.流.ClassTest");
        System.out.println(bClass);
        System.out.println(bClass.getClassLoader());
        System.out.println(bClass.getName());
        System.out.println(aClass==bClass);
        Class<?> cClass = Class.forName("ClassTest");
        System.out.println(cClass);
    }
}
