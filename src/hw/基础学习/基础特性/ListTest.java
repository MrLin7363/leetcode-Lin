package hw.基础学习.基础特性;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class ListTest {
    /**
     * ArrayList 删除倒数第二个不抛异常,其他都报错,因为倒数第二个是提前退出，
     * LinkedList 删除倒数第一、第二个不抛异常,其他都报错
     *  迭代器的remove和list.remove不一样， ArrayList.Itr  上面两个值会重新赋值
     *  list.remove是  List接口里的，只变化modCount
     */
    public static void main(String[] args) {
        List<String> tmpList = new ArrayList<>();
        tmpList.add("Hello");
        tmpList.add("My");
        tmpList.add("Son");
        // modCount表示集合的修改次数
        // expectedModCount则是表示迭代器中对集合进行修改的次数 初始赋值expectedModCount=modCount，迭代器开始后这个用来判断是否有线程并发修改了集合
        // 增强for调用ArrayList,class-Itr里的HasNext()(hasNext里面有cursor!=size的判断) ->next()(next里面会检查modCount和expectCount)
        for (String curStr : tmpList) {
            // 如果这里为Hello就会报错 , Hello删除后modCount=4,expectCount=3
            // 如果是My list.size-1=2,此时下次循环调用HasNext() cursor==size=2退出循环,此时modCont=4,expectCount=3
            if ("My".equals(curStr)) {
                tmpList.remove(curStr); // modCount++，expectCount不会变化
//                 tmpList.add("asd"); 这个也会modCount++,会有问题
            }
        }

        List<String> lis4 = new ArrayList<>();
        lis4.add("apple");
        lis4.add("apple2");
        Iterator<String> it = lis4.iterator();
        while (it.hasNext()) {
            // 增强for调用ArrayList,class-Itr里的next()
            String str = it.next(); // 检查modCount == expectCount
            if (str.equals("apple")) {
                it.remove(); // iterator的remove会重新赋值 expectCount = modCount =2
                // 迭代器的remove和list.remove不一样， ArrayList.Itr  上面两个值会重新赋值
                // list.remove是  List接口里的，只变化modCount
            }
        }

//        List<String> lis = new ArrayList<>();
//        lis.add("apple");
//        Iterator<String> it2 = lis.iterator();
//        while (it2.hasNext()) {
//            // 增强for调用ArrayList,class-Itr里的next()
//            String str = it2.next(); // 检查modCount=2 != expectCount=1
//            if (str.equals("apple")) {
//                lis.add("asd"); // modCount++ =2 ，此时expectCount=1
//            }
//        }

        List<String> linkList=new LinkedList<>();
        linkList.add("11");
        linkList.add("12");
        linkList.remove("12");
        List<String> list5 = new ArrayList<>();
        list5.add("str1");
        list5.add("str1");
        list5.remove("str1");
        list5.remove("str3");
//        list5.remove(0);
//        list5.add(2, "str2");
//        String str22 = list5.get(1);


        String srcTxt = "123\\d";
        String rst1 = srcTxt.replaceAll("\\d", "456"); // \d标识数字  + \转义
        String rst2 = srcTxt.replaceAll(Pattern.quote("\\d"), "456");
        String rst3 = srcTxt.replace("\\d", "456");
        System.out.println(rst1);
        System.out.println(rst2);
        System.out.println(rst3);

        String str = "123";
        String s2 = str + 456914982;
        System.out.println(s2);

        String[] arr = new String[]{"1", "2", "3", "4", "5"};
        List<String> list = Arrays.asList(arr);
        //使用 Arrays.asList()方法得到的List是一个Arrays.ArrayList，不支持增删改操作。要使用new java.util.ArrayList
        //        list.remove("5");
//                list.remove(0);
//                list.add("6");
        list.set(1,"4"); // 但是set沒事 ..
        List<Object> objects = new ArrayList<>();
        objects.add("123");
        List<Object> objects1 = objects.subList(0, 1); // 返回的类重写了java.util.AbstractList(这个不重写默认抛异常)方法增删除可以
        objects1.add("asd");// 会导致原来的集合也变化
        objects.set(0,"1");
//        objects.add("43"); // add会修改
        System.out.println(objects1); // objects修改了原集合，子集合使用会报错 ConcurrentModificationException
        System.out.println(objects);

        Set<String> c = Collections.singleton("c");
        List<String> list2 = Collections.emptyList();
//        list2.add("1");// 报错

        List<String> list3 = new ArrayList<>(10);
        list3.add("1");
        list3.add("2");
        list3.add("3");
        // 传0进去会自动拷贝
        String[] array = list3.toArray(new String[0]);
        for (String s:array){
            System.out.println(s);
        }

        Collection<String> c1 = Arrays.asList("red", "cyan", "red");
        Collection<String> c2 = Arrays.asList("red", "blue");
        Collection<String> c3 = Arrays.asList("pink", "tan");
        System.out.print(Collections.disjoint(c1, c2) + " "); // false 有相交的元素
        System.out.print(Collections.disjoint(c1, c3) + " "); // true 无相交元素
        System.out.println(Collections.frequency(c1, "red")); // 包含后面元素的个数

        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();
        System.out.println(stringArrayList.getClass().equals(integerArrayList.getClass()));
        System.out.println(stringArrayList.getClass());

        List arrayList = new ArrayList();
        arrayList.add("aaaa");
        arrayList.add(100);
        System.out.println((String)arrayList.get(0));
        System.out.println(arrayList.get(1).toString());

        List<String> string = new ArrayList<String>();
        string.add("hello");
        System.out.println(string.size());
        string.add(1, "ok");
        System.out.println(string.get(1));

        LinkedList<String> link=new LinkedList<>();
        link.add(null);

        Optional.ofNullable("asd");

    }
}
