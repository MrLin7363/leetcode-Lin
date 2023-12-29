package A1000PLAN.数学;

import java.util.Arrays;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/11
 **/
public class P319灯泡开关 {
    /*
    推荐-数学
    https://leetcode.cn/problems/bulb-switcher/solutions/1102293/gong-shui-san-xie-jing-dian-shu-lun-tui-upnnb/
    处于打开状态的充要条件为「该灯泡被切换状态次数为奇数次
     */
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }

    /*
    正确-但是超出内存，如果n很大
    一开始亮
    每两个关闭第二个
    每i个切换第i个
    最后一次切换最后一个
     */
    public int bulbSwitch2(int n) {
        if (n == 0) {
            return 0;
        }
        int[] bulbs = new int[n];
        // 第1轮
        Arrays.fill(bulbs, 1);
        // 第i轮
        for (int i = 2; i <= n; i++) {
            // 最后一轮，切换最后一个
            if (i == n) {
                bulbs[n - 1] = bulbs[n - 1] == 1 ? 0 : 1;
            } else {
                // 第i轮：每i个切换第i个
                for (int j = i - 1; j < n; j += i) {
                    bulbs[j] = bulbs[j] == 1 ? 0 : 1;
                }
            }
        }
        // 统计个数
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (bulbs[i] == 1) {
                cnt++;
            }
        }
        return cnt;
    }
}
