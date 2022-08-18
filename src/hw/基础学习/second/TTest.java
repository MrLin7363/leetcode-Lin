package hw.基础学习.second;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TTest {
    public static <T> T add(T x,T y){
        return y;
    }
    public static void main(String[] args) throws InterruptedException {
        int i=TTest.add(1, 2);
        Number f=TTest.add(1, 1.2);
        Object o=TTest.add(1, "asd");
        /**指定泛型的时候*/
        int a=TTest.<Integer>add(1, 2);
//        int b=TTest.<Integer>add(1, 2.2);
        Number c=TTest.<Number>add(1, 2.2);

        IntStream.of(1, 2, 3, 4)
            .filter(e -> e > 2)
            .peek(e -> System.out.println("Filtered value: " + e))
            .map(e -> e * e)
            .peek(e -> System.out.println("Mapped value: " + e))
            .sum(); // 9+16=25
        IntStream.rangeClosed(0, 10).filter(it -> it % 2 == 0).forEach(e-> System.out.println("%"+e));
        List<Integer> builder = new LinkedList<>();
        // peek要和sum()结合不然输出不了
        IntStream.rangeClosed(0, 10).filter(it -> it % 2 == 0).peek(it -> System.out.println(it)).sum();
        IntStream.rangeClosed(0, 10)
            .filter(it -> it % 2 == 0)
            .peek(it -> System.out.println("e="+it))
            .mapToObj(it -> String.valueOf(it));

        IntStream intStream = IntStream.rangeClosed(0, 10).filter(
            it -> it % 2 == 0);
        IntStream.rangeClosed(0, 10)
            .filter(it -> it % 2 == 0)
            .peek(it -> builder.add(it))
            .mapToObj(it -> String.valueOf(it));
        System.out.println(builder.toString());


        int[] foo = new int[]{1, 2, 3};
        Integer[] integers1 = Arrays.stream(foo).boxed().toArray(Integer[]::new);
        IntStream stream = Arrays.stream(foo);
        Stream<Integer> boxed = stream.boxed();

        String ss = "asdas";
        try{
            ss.wait();
        }catch (Exception e){

        }
        switch (ss) {
            case "asdas":
                System.out.println("Asd");
        }
        // 报错
        Integer[] integers2 = Stream.of(foo).toArray(Integer[]::new);
        Stream<int[]> foo1 = Stream.of(foo);
    }


}
