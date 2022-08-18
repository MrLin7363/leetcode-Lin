package hw.基础学习.基础特性;

import java.util.LinkedHashSet;

public class CloneTest {
    public static void main(String[] args) {
        LinkedHashSet<String> set1 = new LinkedHashSet<>();
        set1.add("New York");
        LinkedHashSet<String> set2 = set1;
        LinkedHashSet<String> set3 = (LinkedHashSet<String>) (set1.clone());
        set1.add("Atlanta");
        set2.add("dd");
        set1.forEach(q -> System.out.println(q));
        System.out.println(":");
        set2.forEach(q -> System.out.println(q));
        System.out.println(":");
        set3.forEach(q -> System.out.println(q));
    }
}
