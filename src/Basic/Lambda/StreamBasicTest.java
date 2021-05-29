package Basic.Lambda;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/29 12:57
 * @Describe:
 */
public class StreamBasicTest {
    /*
    匿名内部类写法
     */
    static class myConsumer implements Consumer<String>{
        @Override
        public void accept(String o) {
            System.out.println(o);
        }
    }
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("sss");
        words.add("sssschen");
        words.add("jun");
        /**
         * Consumer接口 使用匿名内部类
         */
        words.forEach(new myConsumer());
        /*
        Comparator 接口
        Optional
         */
        Optional<String> lagest=words.stream().max(String::compareToIgnoreCase);

        // Supplier 提供者
        System.out.println("lagest="+lagest.orElseGet(String::new));

        /*
        Predicate接口 boolean返回值
         */
        Stream<String> stringStream = words.stream().filter(e -> e.length() > 2);

        /*
        Function 接口
         */
        // map返回 w  Stream<String>
        List<String> collect = words.stream().map(w -> {
            if (w.length() > 3) {
                return w;
            }
            return null;
        }).filter(e -> e != null).collect(Collectors.toList());
        collect.forEach(e-> System.out.println(e));

        // map返回 Stream<Boolean>
        List<Boolean> collect1 = words.stream().map(w -> w.length() > 3).collect(Collectors.toList());
        collect1.forEach(e-> System.out.println(e));

        /*
        Collectors 收集结果
         */
        String result = words.stream().filter(w -> w.length() > 2).collect(Collectors.joining(","));
        System.out.println("result="+result);

        /*
        约简为总和等
         */
        IntSummaryStatistics collect2 = words.stream().collect(Collectors.summarizingInt(String::length));
        System.out.println("max="+collect2.getMax()+"avg="+collect2.getAverage());
    }



}
