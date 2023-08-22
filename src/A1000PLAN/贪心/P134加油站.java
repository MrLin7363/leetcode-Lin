package A1000PLAN.贪心;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/8/15
 **/
public class P134加油站 {
    /*
    我们首先检查第 0 个加油站，并试图判断能否环绕一周；如果不能，就从第一个无法到达的加油站开始继续检查。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int i = 0;
        int n = gas.length;
        while (i < n) {
            int sumOfGas = 0;
            int sumOfCost = 0;
            int count = 0;
            while (count < n) {
                int j = (i + count) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                // 到达不了
                if (sumOfCost > sumOfGas) {
                    break;
                }
                count++;
            }
            // 走了n步，达成循环
            if (count == n) {
                return i;
            }
            // 需要跳过count 否则超时
            i = i + count + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        new P134加油站().canCompleteCircuit(new int[] {1, 2, 3, 4, 5}, new int[] {3, 4, 5, 1, 2});
    }
}
