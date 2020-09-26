package InterviewTopic;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/02 17:52
 *
  代码题给定一个正整数n, 输出1到n的所有排列 输入 3 输出 123, 132, 213, 231, 312, 321 
 * @Describe: 递归：f(n)就是把n插入f(n-1)的结果的每个数字的不同位置，
 * 递归从1开始返回，每一次用一个字符串结果集记住，然后依次遍历每个字符串，再每个字符串的每个数字的前中后插入n
 * 将字符串结果集加入最后结果集
 */

public class array1ToN {

    public static Set<String> selectAllArrange(int n) {
        Set<String> set=new HashSet<>();
        if(n==1){
            set.add("1");
        }else {
            Set<String> oldSet=selectAllArrange(n-1);
            //遍历每个字符集，如n==3时，oldSet有 12 或 21
            for(String str:oldSet){
                //纪录每一个组合，如此时为12
                char[] oldArray=str.toCharArray();
                //遍历每一个位置插入,如i==0时在第0个位置插入新的数字
                for (int i=0;i<n;i++){
                //创建字符串数组,n==3时，插入有三个位置
                    char[] newArray=new char[n];
                    //插入新的数字
                    for (int j=0;j<n;j++){
                        if (j<i){
                            newArray[j]=oldArray[j];
                        }else if(j==i){
                            newArray[j]=String.valueOf(n).charAt(0);
                        }else {
                            newArray[j]=oldArray[j-1];
                        }
                    }
                //加入每一个newArray到Set
                    String s=String.valueOf(newArray);
                    set.add(s);
                }
            }
        }
            return set;
    }

    public static void main(String[] args) {
        Set<String> set = selectAllArrange(3);
        System.out.println("共有" + set.size() + "种排列组合");
    }
}
