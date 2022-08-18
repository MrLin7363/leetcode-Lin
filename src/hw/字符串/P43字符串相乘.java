 

package hw.字符串;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/13
 **/
public class P43字符串相乘 {
    /*
    优化竖式版 - 用数组存每次相乘的结果，通过下标判断
     */
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int nLen = num1.length();
        int m = num2.length();
        StringBuilder ans = new StringBuilder();
        int[] res = new int[nLen + m];
        for (int i = nLen - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                int sum = res[i + j + 1] + x * y;
                res[i + j + 1] = sum % 10; // 值
                res[i + j] += sum / 10; // 进位 注意加号

            }
        }
        for (int i = 0; i < nLen + m; i++) {
            if (i == 0 && res[i] == 0) {
                continue;
            }
            ans.append(res[i]);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("456", "123"));
    }
}
