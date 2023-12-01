package A1000PLAN.数学.easy;

/**
 * desc:
 *
 * @author 
 * @since 2023/10/10
 **/
public class P9回文数 {
    /*
    反转全部后和原始比较相同 - 推荐好记住
     */
    public boolean isPalindrome2(int x) {
        // 负数或者10的整数,0是回文
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int cur = 0;
        int num = x;
        while (num > 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur == x;
    }

    /*
    反转一半后和原始比较相同
    */
    public boolean isPalindrome(int x) {
        // 负数或者10的整数,0是回文
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int cur = 0;
        // 反转中间的后半段
        while (x > cur) {
            cur = cur * 10 + x % 10;
            x /= 10;
        }
        //除于10就是奇数的比较
        return x == cur || x == cur / 10;
    }
}
