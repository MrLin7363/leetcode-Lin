package Array.medium;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: Junlin Chen
 * @Date: 2021/09/18 15:19
 * @Describe:
 */
public class P300_Longest_Increasing_Subsequence {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Class<?> clz = Class.forName("P300_Longest_Increasing_Subsequence");
         Object o = clz.newInstance();
         Method m = clz.getDeclaredMethod("hello", null);
          m.invoke(o);
    }



}
