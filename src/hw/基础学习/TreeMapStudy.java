package hw.基础学习;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapStudy {

    public static void main(String[] args) {
        // TreeMap 按key 升序
        Map<Integer, String> map1 = new TreeMap<>();
        map1.put(3, "asdasd");
        map1.put(1, "qweasd");
        map1.put(2, "asd");
        map1.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        System.out.println("-------------------------------------------");
        // TreeMap 按key 降序
        Map<Integer, String> map2 = new TreeMap<>((o1, o2) -> o2 - o1);
        map2.put(3, "asdasd");
        map2.put(1, "qweasd");
        map2.put(2, "asd");
        map2.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        System.out.println("-------------------------------------------");
        // Map  按value 升序  转换为list排序
        Map<Integer, Integer> map3 = new HashMap<>();
        map3.put(5, 2);
        map3.put(6, 32);
        map3.put(3, 2);
        map3.put(4, 11);
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map3.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        // 注意此处不能这样for循环，map存的还是map一开始的数据
//        map3.forEach((key, value) -> {
//            System.out.println(key + ":" + value);
//        });
        for (Map.Entry<Integer,Integer> entry:list){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("-------------------------------------------");
        // map倒序访问   ListIterator可以往前访问，iterator不行
        ListIterator<Map.Entry<Integer, Integer>> li = new ArrayList<>(map3.entrySet()).listIterator(map3.size());
        while(li.hasPrevious()) {
            Map.Entry<Integer, Integer> entry = li.previous();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        System.out.println("-------------------------------------------");
        // 正序
        while (li.hasNext()){
            Map.Entry<Integer, Integer> entry = li.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
