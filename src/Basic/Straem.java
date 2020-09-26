package Basic;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author: Junlin Chen
 * @Date: 2020/09/01 22:06
 * @Describe:
 */
public class Straem {
    public static void main(String[] args) {
        Map<String,Integer> maps=new HashMap<>();
        Stream<Map.Entry<String, Integer>> stream = maps.entrySet().stream();
        Thread thread = new Thread();
    }
}
