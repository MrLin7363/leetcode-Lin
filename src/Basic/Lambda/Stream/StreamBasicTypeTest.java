package Basic.Lambda.Stream;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/29 16:33
 * @Describe: 基本类型流
 */
public class StreamBasicTypeTest {
    public static void main(String[] args) {
        Stream<Integer> integerStream = IntStream.range(0, 100).boxed(); // boxed装箱转为对象类型
//        integerStream.forEach(e-> System.out.println(e));

        // 并行流是把数据拆分为每一块去执行，比如 1-100 拆为CPU线程数，比如 4 个流去执行，再相加  1~25的相加完，再加25~50的
        // 有范围的流用并行流更快
        LongStream.rangeClosed(1, 100).parallel().reduce(0L, Long::sum);


        List<String> words = new ArrayList<>(Arrays.asList("123123", "!@34", "123", "1", "!231241234", "1234123514512342341234"));
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                parallel(words);
            });
            thread.start();
        }

    }
    /*
    并行流分割
     */
    public void test() {
        List<String> shipmentListExcelCOList = new ArrayList<>(29);
        for (int i = 0; i < 29; i++) {
            String shipmentListExcelCO = new String();
            shipmentListExcelCOList.add(shipmentListExcelCO);
        }
        List<List<String>> listList = Stream.iterate(0, n -> n + 1)
                .limit((29+10-1)/10).parallel() // size + maxSize -1  ==  29+10-1   这个就是计算切分次数
                .map(index -> shipmentListExcelCOList.stream().skip(index * 10) // 每次的下标
                        .limit(10).parallel().collect(Collectors.toList())).collect(Collectors.toList()); // 每次下标开始limit多少
        System.out.println("A");
    }

    private static void parallel(List<String> words){
        int[] shortWords=new int[12];
        words.parallelStream().forEach(
                s-> {
                    if (s.length()<12){
                        shortWords[s.length()]++;
                    }
                }
        );
        System.out.println(Thread.currentThread()+Arrays.toString(shortWords));
    }
}
