package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/10/27
  *@Describe: 复原IP地址
    官方解法
 */

import java.util.ArrayList;
import java.util.List;

public class Restore_IP_Addresses_93 {

    static final int COUNT=4; // 4段
    static List<String> ans=new ArrayList<>(); // 返回结果
    static int[] segments=new int[COUNT] ; // 每段数据

    public static List<String> restoreIpAddresses(String s) {
       dfs(s,0,0);
       return ans;
    }

    // Id 第几段，start下标
    public  static void dfs(String s, int segId, int segStart) {
        // 结束条件: 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId==COUNT){
            if (segStart==s.length()){
                // 拼接每一段数据
                StringBuilder sb=new StringBuilder();
                for (int i = 0; i < COUNT; i++) {
                    sb.append(segments[i]);
                    if (i!=COUNT-1)
                        sb.append(".");
                }
                ans.add(sb.toString());
            }
            return;
        }
        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart==s.length())
            return;
        //由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0，以该0单独作为一段，不行回溯，将该0添加至上一位的末位作为一段
        if (s.charAt(segStart)=='0'){
            segments[segId]=0;
            dfs(s,segId+1,segStart+1);
        }
        // 一般情况，枚举每一种可能性并递归
        int addr=0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;// 如果当前段已经不合格，那不用再添加后面的尝试
            }
        }
        //到达最后这里也会回溯
    }

    public static void main(String[] args) {
        restoreIpAddresses("25525511135");
        System.out.println(ans);
    }
}
