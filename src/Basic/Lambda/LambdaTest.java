package Basic.Lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: chenjunlin
 * @since: 2021/05/19
 * @descripe:
 */
public class LambdaTest {
    public static void main(String[] args) {
        Stream<String> stream=Stream.of("123","32","12");
        List<String> names=new ArrayList<>();
        names.add("1234");
        names.add("123");
        names.stream().filter( s -> s.length()>2 );
//        double sum = names.stream().filter( n -> n.length()>3 ).mapToDouble( w -> Double.valueOf(w)).sum();
//        System.out.println(sum);
        List<String> ss= names.stream().map(s->{
            if (s.equals("1234")) {
                return s;
            }
            return null;
        }).collect(Collectors.toList());
        ss.forEach(
                sss-> System.out.println(sss)
        );
        names.forEach(
                name ->
                        System.out.println(name)
        );
    }
}
