 

package hw.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:
 *
 * @author junlin
 * @since 2021/12/13
 **/
public class P93复原IP地址 {

    static int COUNT = 4;

    static List<String> ansList = new ArrayList<>();

    static int[] segments = new int[COUNT];

    public static List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return ansList;
    }

    /**
     * 每一段是一次遍历
     *
     * @param s
     * @param segId
     * @param segStart 字符串中的下标
     */
    private static void dfs(String s, int segId, int segStart) {
        // 到了第4段IP
        if (segId == COUNT) {
            if (segStart == s.length()) {
                StringBuilder ipAddr = new StringBuilder();
                for (int i = 0; i < COUNT; i++) {
                    ipAddr.append(segments[i]);
                    if (i != COUNT - 1) {
                        ipAddr.append(".");
                    }
                }
                ansList.add(ipAddr.toString());
            }
            return;
        }
        // 没到第4段就遍历到最后
        if (segStart == s.length()) {
            return;
        }
        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }
        // 枚举每一种可能拼接情况
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); segEnd++) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            // 需要大于0
            if (addr > 0 && addr <= 255) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("010010"));
    }

}
