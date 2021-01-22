package TenSortAlgorithm;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/22
  *@Describe:
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class RadioSort {
    public static int[] radioSort(int[] arr){
        if (arr==null||arr.length<2) return arr;
        int n=arr.length;
        int max=arr[0];
        // 找出最大值
        for (int i = 1; i < n; i++) {
            if (arr[i]>max){
                max=arr[i];
            }
        }
        // 计算最大值是几位数
        int num=1;
        while (max/10>0){
            max/=10;
            num++;
        }
        // 创建10个桶，初始化
        ArrayList<LinkedList<Integer>> bucketList=new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            bucketList.add(new LinkedList<>());
        }
        // 进行每一趟的排序，从个位数开始排
        for (int i = 1; i <= num; i++) {
            // 遍历每个元素
            for (int j = 0; j < n; j++) {
                // 此时的arr数组已经部分有序，按位数已经有序，再诸位有序就整体有序了
                int radio = (arr[j] / (int) Math.pow(10, i - 1)) % 10; // 平方
                // 放入桶中
                bucketList.get(radio).add(arr[j]);
            }
            // 合并放回数组
            int index=0;
            for (int j = 0; j < 10; j++) {
                for (Integer t:bucketList.get(j)){
                    arr[index++]=t;
                }
                // 取出来合并了之后把桶清光数据
                bucketList.get(j).clear();
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] arr=radioSort(new int[]{2,3,41,24,12,43,12,3});
        for (int s:arr){
            System.out.println(s);
        }
    }
}
