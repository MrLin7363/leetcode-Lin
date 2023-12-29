package A1000PLAN.深广遍历.递归;

/**
 * desc:
 *
 * @author lin
 * @since 2023/12/4
 **/
public class P306累加数 {
    /*
    自己写的递归：官方也是 On^3 可以不用看
    确定第1，2个数再递归查阅是否合理
     */
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i < n; i++) {
            // 过滤前导0
            if (num.substring(0, 1).equals("0") && i > 1) {
                break;
            }
            for (int j = i; j < n; j++) {
                // 过滤前导0
                if ((num.substring(i, i + 1).equals("0") && j > i)) {
                    break;
                }
                long num1 = Long.valueOf(num.substring(0, i));
                long num2 = Long.valueOf(num.substring(i, j + 1));
                if (j + 1 < n && check(num.substring(j + 1), num1, num2)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 递归可以改：传入四个坐标，然后字符串判断不用转long
    private boolean check(String num, long num1, long num2) {
        long sum = num1 + num2;
        // 计算下一个字符串位数
        int cnt = 0;
        // 单独000的情况
        if (sum == 0) {
            cnt = 1;
        }
        while (sum != 0) {
            sum /= 10;
            cnt++;
        }
        sum = num1 + num2;

        if (cnt > num.length()) {
            return false;
        }
        Long next = Long.valueOf(num.substring(0, cnt));
        if (sum != next) {
            return false;
        }
        if (cnt == num.length()) {
            return true;
        }

        return check(num.substring(cnt), num2, sum);
    }

    public static void main(String[] args) {
        String num = "000";
        System.out.println(num.substring(0, 1));
        System.out.println(num.substring(0));
        new P306累加数().isAdditiveNumber("000");
        new P306累加数().isAdditiveNumber("1023");
        new P306累加数().isAdditiveNumber("199100199");
        new P306累加数().isAdditiveNumber("112358");
    }
}
