package Basic.Lambda.HanshuInterface;

import java.util.function.Function;

/**
 * @author: Junlin Chen
 * @Date: 2020/08/30 16:32
 * @Describe:
 */
public class MyFunction {

    public static void main(String[] args) {
       /* convert(100,(Integer i)->{
            return String.valueOf(i);
        });*/
       convert(100,
               (i)-> String.valueOf(i));
    }
    private static void convert(int i, Function<Integer,String> fun){
        String s=fun.apply(i); // 往上执行 String.valueOf(i)
        System.out.println(s);
    }
}
