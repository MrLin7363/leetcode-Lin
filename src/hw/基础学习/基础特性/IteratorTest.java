package hw.基础学习.基础特性;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

    public static void main(String[] args) {
        List<String> sList=new ArrayList<>();
        sList.add("asdasdq");
        sList.add(":11d");
        Iterator<String> iterator = sList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(iterator.hasNext());

        ArrayList<String> objects2 = new ArrayList<>();
        objects2.add("asdasd");
        Iterator<String> iterator2 = objects2.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.hasNext());// 只是 hashNext不会移动cursor
            iterator2.next();// 需要先调这个因为，iterator默认cursor是0没有选中，remove会报错
            iterator2.remove(); // 如果没有next()则cursor=0 没有元素，remove会报错
        }

    }
}
