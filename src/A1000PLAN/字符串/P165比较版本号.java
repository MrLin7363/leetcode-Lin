package A1000PLAN.字符串;

/**
 *desc:
 *@author lin
 *@since 2023/11/25
 **/
public class P165比较版本号 {
    /*
    1. 分割  n+n
     */
    public int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        int n = split1.length;
        int m = split2.length;
        int len = Math.max(n, m);

        for (int i = 0; i < len; i++) {
            int num1 = i < n ? Integer.parseInt(split1[i]) : 0;
            int num2 = i < m ? Integer.parseInt(split2[i]) : 0;
            if (num1 > num2) {
                return 1;
            } else if (num1 < num2) {
                return -1;
            }
        }
        return 0;
    }

    /*
    优化版 2.双指针 n+1
     */
    public int compareVersion2(String version1, String version2) {
        int n = version1.length(), m = version2.length();
        int i = 0, j = 0;
        while (i < n || j < m) {
            int x = 0;
            for (; i < n && version1.charAt(i) != '.'; ++i) {
                x = x * 10 + version1.charAt(i) - '0';
            }
            ++i; // 跳过点号
            int y = 0;
            for (; j < m && version2.charAt(j) != '.'; ++j) {
                y = y * 10 + version2.charAt(j) - '0';
            }
            ++j; // 跳过点号
            if (x != y) {
                return x > y ? 1 : -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        new P165比较版本号().compareVersion("0.1", "1.1");
    }
}
