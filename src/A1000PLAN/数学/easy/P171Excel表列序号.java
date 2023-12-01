package A1000PLAN.数学.easy;

/**
 *desc:
 *@author lin
 *@since 2023/12/1
 **/
public class P171Excel表列序号 {
    /*
    26进制
    当列名称的长度为nn 时，列名称的每个字母都有 26 种不同的取值，因此长度为 n 的不同列名称有 26^n个
     */
    public int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int sum = 0;
        int multiple = 1;
        char[] chars = columnTitle.toCharArray();
        for (int i = n - 1; i >= 0; i--) {
            int cur = chars[i] - 'A' + 1;
            sum += cur * multiple;
            multiple *= 26;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new P171Excel表列序号().titleToNumber("AB"));
        System.out.println('B' - 'A' + 1);
    }
}
