package hw.基础学习.继承;

import AA考试题目.FileSystem;

import java.io.File;

public class Testtt<T> {
    public void show(T t){
        System.out.println(t.toString());
    }

    public static void main(String[] args) {
        char c='2';
        System.out.println(c+1);
        int i=109;
        int j=123;
        int i1 = i * j;
        Testtt<Object> o=new Testtt<>();
        show(o);
    }

    private static void show(Testtt<Object> o) {

    }
}
