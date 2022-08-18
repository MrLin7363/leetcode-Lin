package hw.基础学习.继承;

import java.util.LinkedList;
import java.util.List;

public class Cat extends Animal{
    public static void say(){
        System.out.println("Cat say");
    }

    @Override
    public void jump() {
        System.out.println("Cat jump");
        List<String> list=new LinkedList<>();
        list.add(null);
    }

    public void run(){
        System.out.println("Cat run");
    }

    public Cat(){
        System.out.println("asd");
    }

    public static void main(String[] args) {
        Cat cat=new Cat();
    }
}
