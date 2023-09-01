package A1000PLAN.深广遍历.记忆化搜索;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/24
 **/
public class P397整数替换_记忆化搜索 {
    /*
    由于内存限制，记忆化搜索采用Map的方式不采用数组,这样map最大也只是59个，都是2的方根，及-1
     */
    private Map<Integer, Integer> memory = new HashMap<>();

    public int integerReplacement(int n) {
        final int i = integerReplacementMemory(n);
        return i;
    }

    public int integerReplacementMemory(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (!memory.containsKey(n)) {
            if (n % 2 == 0) {
                memory.put(n, integerReplacementMemory(n / 2) + 1);
            } else {
                // 直接两步操作规避+1溢出情况    如 7/2=3=7-1/2 ; 7/2+1 = 4= 7+1/2
                memory.put(n, 2 + Math.min(integerReplacementMemory(n / 2), integerReplacementMemory(n / 2 + 1)));
            }
        }
        return memory.get(n);
    }

    /*
    无记忆化搜索-会溢出可以变成long
     */
    public int integerReplacement2(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n % 2 == 0) {
            return integerReplacement2(n / 2) + 1;
        } else {
            // 直接两步操作规避+1溢出情况    如 7/2=3=7-1/2 ; 7/2+1 = 4= 7+1/2
            return 2 + Math.min(integerReplacement2(n / 2), integerReplacement2(n / 2 + 1));
        }
    }

    /*
    无记忆化搜索-会溢出可以变成long
     */
    public int integerReplacementL(long n) {
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        if (n % 2 == 0) {
            return integerReplacementL(n / 2) + 1;
        } else {
            return Math.min(integerReplacementL(n - 1) + 1, integerReplacementL(n + 1) + 1);
        }
    }

    public static void main(String[] args) {
        int i = new P397整数替换_记忆化搜索().integerReplacement(2147483647);
        System.out.println(i);
    }
}
