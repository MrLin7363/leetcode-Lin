package A1000PLAN.哈希.easy;

import java.util.HashSet;
import java.util.Set;

/**
 *desc:
 *@author lin
 *@since 2023/11/15
 **/
public class P202快乐数 {
    // 推荐做法
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int i = n % 10;
            n = n / 10;
            sum += i * i;
        }
        return sum;
    }

    /*
    hash记录每次结果，遇到重复就不是快乐数
     */
    public boolean isHappy2(int n) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            if (n == 1) {
                return true;
            }
            set.add(n);
            char[] charArray = String.valueOf(n).toCharArray();
            int cur = 0;
            for (int i = 0; i < charArray.length; i++) {
                // 不推荐，推荐使用n/的方式
                double pow = Math.pow(Character.getNumericValue(charArray[i]), 2);
                cur += pow;
            }
            n = cur;
        }
        return false;
    }

    public static void main(String[] args) {
        new P202快乐数().isHappy(19);
    }
}
