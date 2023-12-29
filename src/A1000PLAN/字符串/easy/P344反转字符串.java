package A1000PLAN.字符串.easy;

/**
 * desc:
 *
 * @author lin
 * @since 2023/12/6
 **/
public class P344反转字符串 {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
