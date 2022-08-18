package hw.基础学习.基础特性;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListRemoveTEst {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("Hello");
        list.add("Java");
        list.add("World");
        list.add("!!!");
        for (int i = 0; i < list.size(); i++) {
            if ("World".equals(list.get(i))) {
                list.remove(i);
            }
            System.out.println(list.get(i)); // 只有！！！报错，因为删除后get(i)有问题
        }

        ArrayList<String> objects1 = new ArrayList<>();
        objects1.add("A");
        objects1.add("B");
        objects1.add("C");
        objects1.removeIf(s -> "D".equals(s));
        System.out.println(objects1);

        ArrayList<String> objects2 = new ArrayList<>();
        objects2.add("asdasd");
        Iterator<String> iterator = objects2.iterator();
        while (iterator.hasNext()) {
            // 需要先调这个因为，iterator默认cursor是0没有选中，remove会报错
            iterator.next();
            iterator.remove();
        }

        List<String> list1 = Collections.nCopies(5, "java");
        list1.forEach(l -> System.out.println(l));
        //        list1.add("12we");// 报错，返回的是不可修改的List
        System.out.println(list1.size());
    }
}
