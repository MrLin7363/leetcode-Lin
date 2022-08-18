package hw.基础学习.继承;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FanxinTest {
    static class Fruit{}
    static class Apple extends Fruit{}
    static class BigApple extends Apple {}


    public static void process(List<? extends Apple> list) {

    }

    public static void main(String[] args) throws Exception {
        List<Apple> appleList=new ArrayList<>();
        List<Fruit> fruitListTest=new ArrayList<>();
        fruitListTest.addAll(appleList);
        // 上届通配符用于读取
//        process(new ArrayList<Fruit>()); // 编译错
        process(new ArrayList<Apple>());
        process(new ArrayList<BigApple>());

        // 上届通配符主要用于读取
        List<? extends Fruit> fruitList = new ArrayList<>();
        fruitList.add(null);
        List<? extends Fruit> fruitList2 = new ArrayList<>();
        List<? extends Apple> applelist = new ArrayList<>();
        List<BigApple> bigApplelist = new ArrayList<>();
        applelist.add(null);//只能addnull
        fruitList=applelist; // 读取
        applelist.get(0);
        fruitList=fruitList2; // 读取
        fruitList=bigApplelist;
//        applelist=fruitList; // 读取 - 编译错
//        applelist.add(new Fruit());//编译错
//        applelist.add(new Apple());//编译错
//        Fruit fruit = fruitList.get(0);

        // 下界通配符主要用于插入,也可以读取
        List<? super Apple> apples = new ArrayList<>();
        List<? super Fruit> fruits = new ArrayList<>();
//        applelist=fruits;//编译错
//        fruits=apples;//编译错
//        apples.add(new Fruit());// 编译错
        apples=fruits;
        apples.add(new Apple());
        apples.get(0);
        apples.add(new BigApple());
        fruits.add(new Apple());
        Object object = apples.get(0);

        switch (5) {
            default:
                System.out.print(5);
            case 5:
                System.out.println(0);
            case 1:
                System.out.print(1);
                break;
            case 2:
                System.out.print(2);
                break;
        }

        Animal cat=new Cat();
        cat.jump();// 执行子类的方法
        cat.say();
//        cat.run();父类运行不了
        int i=0;
        Number ii=1;
        i= (int) ii; //下转强制
        ii=i; // 上转默认
        System.out.println(ii);
        transfer(i);
        byte b=0;
        transfer(b);
        short s=0;
        transfer(s);
        char c='c';
        transfer(c);
        long l=1;
        transfer(l);
        float f=1;
        transfer(f);

        List<String> list=new ArrayList<>();
        list.add("sda");
        System.out.println(list);
        int[] nums=new int[]{1,2};
        System.out.println(nums);
        Map<String,String> map=new HashMap<>();
        map.put("asd","ad");
        System.out.println(map);

        String sss="qwd,qwd";
        String[] split = sss.split(",");
        System.out.println(sss);
        System.out.println(split);

        StringBuilder sb=new StringBuilder(2);

        char[] chars=new char[4];
        chars[0]='\n';
        chars[1]='\u0012';
        chars[1]='\1';
        chars[1]='\12';
        chars[1]='\123';
        chars[1]='\f';
        chars[1]='\t';

        Object[] arr=new Integer[10]; // 当向数组中添加类型不匹配的元素时，在运行期才会发生错误，而对于集合，在编译期就会报错
        arr[0]=1;
        arr[1]="1"; // 报错


    }

    private static void transfer(double f) {

    }
}
