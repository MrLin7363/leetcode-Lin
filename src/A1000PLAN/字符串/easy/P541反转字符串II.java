package A1000PLAN.字符串.easy;

/**
 * desc:
 *
 * @author lin
 * @since 2023/12/6
 **/
public class P541反转字符串II {
    /*
    每次2k,前k个反转； 如果<k，全部反转
     */
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i += 2 * k) {
            // 如果剩下<k,全部反转
            if (arr.length - i < k) {
                reverse(arr, i, arr.length - 1);
            } else {
                // 前k个反转
                reverse(arr, i, i + k - 1);
            }
        }
        return new String(arr);
    }

    private void reverse(char[] s, int left, int right) {
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
