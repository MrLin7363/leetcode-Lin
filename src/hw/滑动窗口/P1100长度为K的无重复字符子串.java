 

package hw.滑动窗口;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/3
 **/
public class P1100长度为K的无重复字符子串 {

    /*
    通用模板
     */
    public static int numKLenSubstrNoRepeats(String s, int k) {
        int n = s.length();
        int left = 0;
        int right = 0;
        int ans = 0;
        char[] nums = new char[26];
        while (right < n) {
            nums[s.charAt(right) - 'a']++;
            //  不满足条件左指针收缩
            while (nums[s.charAt(right) - 'a'] > 1 || right - left + 1 > k) {
                nums[s.charAt(left) - 'a']--; // 会跟上重复的然后去掉
                left++;
            }
            // 符合条件
            if (right - left + 1 == k) {
                ans++;
            }
            right++;
        }
        return ans;
    }

    /*
    "havefunonleetcode"
     */
    public static void main(String[] args) {
        numKLenSubstrNoRepeats("baahu", 3); // 17个
        numKLenSubstrNoRepeats("home", 5); // 17个
        numKLenSubstrNoRepeats("havefunonleetcode", 5); // 17个
    }

}
