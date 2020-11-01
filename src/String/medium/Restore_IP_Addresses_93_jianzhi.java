package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/1
  *@Describe:复原IP地址

 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Restore_IP_Addresses_93_jianzhi {
    public static void main(String[] args) {
        restoreIpAddresses("25525511135");
    }
    public static List<String> restoreIpAddresses(String s) {
        int len=s.length();
        List<String> res=new ArrayList<>();
        if (len >12 || len< 4 )
            return res;
        Deque<String> path =new ArrayDeque<>();
        dfs(s,len,0,4,path,res);
        return res;
    }

    /*
    字符串，长度，遍历到的下标，剩余未被分割
     */
    private static void dfs(String s,int len, int begin, int residue, Deque<String> path, List<String> res){
        // 结束条件 遍历到最后没有分割完
        if (begin == len){
            if (residue==0){
                res.add(String.join(".",path));
            }
            return;
        }
        for (int i = begin; i < begin+3; i++) {
            if (i>=len){
                break;
            }
            // 如果当前分割位数太少，导致后面分配三位也分割不完
            if (residue*3 < len - i ){
                continue;
            }
            // begin 不变，i会变
            if (judgeIpSegment(s,begin,i)){
                String currentIpSegment=s.substring(begin,i+1);
                path.addLast(currentIpSegment);

                dfs(s,len,i+1,residue-1,path,res);
                path.removeLast();
            }
        }
    }
    private static boolean judgeIpSegment(String s, int left, int right) {
        int len=right-left+1;
        // 如果以0开头
        if (len>1&&s.charAt(left)=='0'){
            return false;
        }
        // 转化为int类型，注意<=
        int res=0;
        while (left<=right){
            res=res*10+s.charAt(left)-'0';
            left++;
        }
        return res>=0 && res<=255;
    }

}
