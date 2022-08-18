package hw.基础学习.继承;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyOver extends Base {
    public MyOver(int i) {
        // 子类必须显示调用父类的构造方法,如果父类没有默认构造函数
        super(i);
    }
    public static void main(String[] args) {
        MyOver m = new MyOver(10);

        List<String> ss=new ArrayList<>();
        ss.add(null);
        ss.add(null);
        ss.add("null2");
        ss.forEach(s-> System.out.println(s));
        Optional<Object> o = get();
        o.orElse(null);

        int ni = 92;
        double d1 = ni / 30;
        System.out.println(d1);
    }

    private static Optional<Object> get(){
        return Optional.empty();
    }
}
