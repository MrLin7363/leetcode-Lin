package String.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/11/1
  *@Describe:
 */

import java.util.Arrays;
import java.util.List;

public class Compare_Version_Numbers_165 {

    /*
    官方答案：分割+解析，两次遍历，线性空间
     */
    /*
    自己写的：分隔开，转化整数，依次比较每一级
    T 90%
    S 37%
     */
    public static int compareVersion(String version1, String version2) {
        List<String> v1 = Arrays.asList(version1.split("\\."));
        List<String> v2 = Arrays.asList(version2.split("\\."));
        int len1 = v1.size();
        int len2 = v2.size();
        int i = 0, j = 0;
        while (i < len1-1 && j < len2-1) {
            int value1 = Integer.parseInt(v1.get(i++));
            int value2 = Integer.parseInt(v2.get(j++));
            if (value1 > value2)
                return 1;
            else if (value2 > value1)
                return -1;
        }
        // 有一个到末尾了,比较末尾
        if (Integer.parseInt(v1.get(i))>Integer.parseInt(v2.get(j)))
            return 1;
        else if (Integer.parseInt(v1.get(i))<Integer.parseInt(v2.get(j)))
            return -1;
        i++;j++;
        // 末尾一位相等，看谁多的，且有>0
        while (len1>len2 &&i < len1  )
            if (Integer.parseInt(v1.get(i++)) > 0)
            return 1;
        while (len2>len1 && j<len2)
            if (Integer.parseInt(v2.get(j++))>0)
            return -1;
        return 0;
    }

    public static void main(String[] args) {
        compareVersion("1","1.1");
    }
}
