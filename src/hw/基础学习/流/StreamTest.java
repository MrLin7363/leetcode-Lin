package hw.基础学习.流;

import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        // 会一直迭代下去有问题
//        IntStream.iterate(0,i->i+1).forEach(System.out::println);
        // 也会一直迭代下去
//        IntStream.iterate(0,i->i+1).distinct().forEach(System.out::println);
        // 没问题十个后终止
        IntStream.iterate(0,i->i+1).limit(10).forEach(System.out::println);
        // distinct由于找不到10个唯一的，所以limit操作一直进行不到，如果limit 2 就可以
        IntStream.iterate(0,i->(i+1)%2).distinct().limit(10).forEach(System.out::println);
        IntStream intStream = IntStream.of(1, 23, 2);
    }
}
