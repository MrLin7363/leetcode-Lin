package A1000PLAN.深广遍历.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 *desc:
 *@author lin
 *@since 2023/11/24
 **/
public class P93复原IP地址 {
    private List<String> res;

    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        int len = s.length();
        if (len < 4 || len > 12) {
            return res;
        }
        dfs(0, "", s, 0);
        return res;
    }

    /*
    结束条件：找到4端IP地址
     */
    private void dfs(int segment, String path, String s, int start) {
        if (segment == 4) {
            if (start == s.length()) {
                res.add(path.substring(0, path.length() - 1)); // 去掉最后一个.
            }
            return;
        }
        for (int i = start; i < s.length() && i < start + 3; i++) {
            // 前导0过滤
            if (s.charAt(start) == '0' && i > start) {
                break;
            }
            // 剪枝：剩下的总长度>剩下的段数*每段最多3长度
            if (s.length() - i > 3 * (4 - segment)) {
                break;
            }
            int num = Integer.parseInt(s.substring(start, i + 1));
            if (num >= 0 && num <= 255) {
                dfs(segment + 1, path + num + ".", s, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        // new P93复原IP地址().restoreIpAddresses("0000");
        new P93复原IP地址().restoreIpAddresses("101023");
        new P93复原IP地址().restoreIpAddresses("25525511135");
    }
}
