package wangzheng;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/14 21:45
 * @Describe: next数组不优化版本好理解
 * 返回匹配字符串下标开始位置
 */
public class KMP {
    /*
    a 主串，b模式串
     */
    public static int kmp1(char[] a, char[] b) {
        int i = 0;
        int j = 0;
        int[] next = getNext(b);
        //遍历
        while (i < a.length && j < b.length) {
            //找到相等的
            if (j == -1 || a[i] == b[j]) {
                i++;
                j++;
            } else
                // 不相等，移动最长前缀匹配字符串长度
                j = next[j];
        }
        if (j == b.length) {
            return i - j; // 返回匹配字符串的开始下标
        }
        return -1;
    }

    /*
    求模式串的子串的前缀集合和后缀集合的最大长度，+1是为了好编程
     */
    private static int[] getNext(char[] b) {
        int[] next = new int[b.length];
        next[0] = -1;// 第一个没有最长匹配前缀
        int i = 0, j = -1; // 注意相差一位
        while (i < b.length) {
            // 匹配字符
            if (j == -1 || b[i] == b[j]) {
                ++i;
                ++j;
                if (i==b.length)
                    return next;
                next[i] = j;
            } else // 不匹配字符
                j = next[j]; //移动
        }
        return next;
    }

    public static void main(String[] args) {
//        int res = kmp1("abe".toCharArray(), "abe".toCharArray());
        int res = kmp2("abe".toCharArray(),3, "we".toCharArray(),2);
        System.out.println(res);
    }


    // a, b分别是主串和模式串；n, m分别是主串和模式串的长度。  王争算法
    public static int kmp2(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0; // 没有置为-1，所以下面的要用next[j-1]
        for (int i = 0; i < n; ++i) {
            while (j > 0 && a[i] != b[j]) { // 一直找到a[i]和b[j]
                j = next[j - 1] + 1; // 移动j判断
            }
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) { // 找到匹配模式串的了
                return i - m + 1;
            }
        }
        return -1;
    }
    // b表示模式串，m表示模式串的长度
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            // 找到不匹配的
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k]; // 移动
            }
            // 如果相等
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;//最长公共前后缀的下标/长度-1
        }
        return next;
    }

}
