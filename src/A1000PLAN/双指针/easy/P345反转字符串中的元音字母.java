package A1000PLAN.双指针.easy;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/8
 **/
public class P345反转字符串中的元音字母 {
    public String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;
        StringBuilder sb = new StringBuilder(s);
        while (left < right) {
            while (left < right && !isVowel(sb.charAt(left))) {
                left++;
            }
            while (left < right && !isVowel(sb.charAt(right))) {
                right--;
            }
            if (left < right) {
                char temp = sb.charAt(left);
                sb.setCharAt(left, sb.charAt(right));
                sb.setCharAt(right, temp);
                left++;
                right--;
            }
        }
        return sb.toString();
    }

    // 可以用这个替换set
    public boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    public static void main(String[] args) {
        new P345反转字符串中的元音字母().reverseVowels("hello");
    }
}
