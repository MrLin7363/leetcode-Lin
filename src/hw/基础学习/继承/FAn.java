package hw.基础学习.继承;

public class FAn<T> {
    public  void asd(T t){ // 这里加static编译错，泛型不能应用于静态方法
        System.out.println(t.toString());
    }
}
