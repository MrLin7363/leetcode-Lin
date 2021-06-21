package Basic.Lambda;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/29 13:32
 * @Describe:
 */
public class StreamMapTest {
    public static void main(String[] args) {
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People(1, "chenjunlin"));
        peopleList.add(new People(2, "zhaowen"));
        peopleList.add(new People(1, "chenjunlin 2222"));
        peopleList.add(new People(2, "zhaowen  2222"));

        /*
        Collectors.toMap
         */
        Map<Integer, String> map = null;
        try {
            map = peopleList.stream().collect(Collectors.toMap(People::getId, People::getName));
            map.forEach((k, v) -> {
                System.out.println("key=" + k + "  value=" + v);
            });
        } catch (IllegalStateException e) {
            System.out.println("键冲突了" + e.getMessage());
        }

        // 值是实际元素用 Function.identity()
//        Map<Integer,People> map1=peopleList.stream().collect(Collectors.toMap(People::getId,Function.identity()));
//        map1.forEach((k,v)->{ System.out.println("key="+k+"  value="+v); });

        // 解决相同键的冲突   当新旧键冲突时，取原来的键就行    BinaryOperator二元操作符
        Map<Integer, String> map2 = peopleList.stream().collect(Collectors.toMap(People::getId, People::getName, (existValue, newValue) -> existValue));
        map2.forEach((k, v) -> {
            System.out.println("key=" + k + "  value=" + v);
        });

        // 根据Id 分有那几个人
        Map<Integer, List<People>> collect = peopleList.stream().collect(Collectors.groupingBy(People::getId));
        collect.forEach((k, v) -> {
            System.out.println("key=" + k + "  value=" + v.get(1).getName());
        });
        System.out.println("--------------------------------------------------------");


        Map<String, List<Locale>> collect2 = Stream.of(Locale.getAvailableLocales()).collect(Collectors.groupingBy(Locale::getCountry));
//        collect2.forEach((k,v)->{ System.out.println("key="+k+"  value="+v); });
        // Collectors.groupingBy 可以省略为 groupingBy   导入静态Collectors后
        Map<String, Long> collect3 = Stream.of(Locale.getAvailableLocales()).collect(Collectors.groupingBy(Locale::getCountry, counting()));
        // 线程安全的 分组
        ConcurrentMap<String, Long> stringLongConcurrentMap = Stream.of(Locale.getAvailableLocales()).collect(groupingByConcurrent(Locale::getCountry, counting()));

        // 统计每个国家使用的语言
        Map<String, Set<String>> stringSetMap = Stream.of(Locale.getAvailableLocales()).collect(groupingBy(Locale::getCountry, mapping(Locale::getDisplayLanguage, toSet())));

        // 当要用到 Predicate 时用  partitioningBy  分区
        Map<Boolean, List<Locale>> booleanListMap = Stream.of(Locale.getAvailableLocales()).collect(partitioningBy(l -> l.getDisplayLanguage().equals("en")));
        List<Locale> locales = booleanListMap.get(true);


        List<City> cities = new ArrayList<>(Arrays.asList(new City("guangdong", 10000),
                new City("guangdong", 5000), new City("guangxi", 2000)));
        // 统计每个州人口总和
        Map<String, Integer> stringIntegerMap = cities.stream().collect(groupingBy(City::getState, summingInt(City::getPopulation)));
        stringIntegerMap.forEach((k, v) -> {
            System.out.println("key=" + k + "  value=" + v);
        });

        // 每个州人口最多的城市
        Map<String, Optional<City>> stringOptionalMap = cities.stream().collect(groupingBy(City::getState, maxBy(Comparator.comparing(City::getPopulation))));
        stringOptionalMap.forEach((k, v) -> {
            System.out.println("key=" + k + "  value=" + v);
        });

        // 每个州人口数据
        Map<String, IntSummaryStatistics> stringIntSummaryStatisticsMap = cities.stream().collect(groupingBy(City::getState, summarizingInt(City::getPopulation)));

        /*
        约简操作  针对List<数据类型>
         */
        List<Integer> values = new ArrayList<>(Arrays.asList(-1, -1, -1 ));
        Optional<Integer> sum = values.stream().reduce((x, y) -> x + y);// values.stream().reduce(Integer::sum);
        System.out.println("sum=" + sum.get());

        // 么元值 ，例如 int 的么元值=0   不管怎样都会返回0
        Integer sum2 = values.stream().reduce(0, Integer::sum);
        System.out.println("sum2=" + sum2);

        // 统计所有州人口总和数
        int peopleSum = cities.stream().mapToInt(City::getPopulation).sum();
        System.out.println("所有州人口总数为 = " + peopleSum);
        int peopleSum1 = cities.stream().mapToInt(City::getPopulation).reduce(0,Integer::sum);
        System.out.println("所有州人口总数为 = " + peopleSum1);


    }
}
