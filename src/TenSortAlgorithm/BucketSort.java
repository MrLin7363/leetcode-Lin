package TenSortAlgorithm;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/22
  *@Describe:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {
    public static int[] BucketSort(int[] arr){
        int n=arr.length;
        // 寻找数组的最大值与最小值
        int max=arr[0],min=arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i]>max){
                max=arr[i];
            }
            if (arr[i]<min){
                min=arr[i];
            }
        }
        //和优化版本的计数排序一样，弄一个大小为 min 的偏移值
        int d=max-min;
        //创建 d / 5 + 1 个桶，第 i 桶存放  5*i ~ 5*i+5-1范围的数
        // 或者创建 d/n 个桶，这里取决于自己
        int bucketNum=d/n+1; // d/5+1
        //初始化桶
        ArrayList<LinkedList<Integer>> bucketList=new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }
        //遍历原数组，将每个元素放入桶中
        for (int i = 0; i < n; i++) {
            int num= (arr[i]-min) /n;
            bucketList.get(num).add(arr[i]);
        }
        // 桶内排序
        for (int i = 0; i < bucketList.size(); i++) {
            Collections.sort(bucketList.get(i));// 这里一般用快速排序，这里用API
        }
        // 将桶中的元素赋值到原序列
        int index=0;
        for (int i = 0; i < bucketList.size(); i++) {
            for (int j = 0; j < bucketList.get(i).size(); j++) {
                arr[index++]=bucketList.get(i).get(j);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr=BucketSort(new int[]{2,3,41,24,12,43,12,1,2,2,3,100,233,3});
        for (int s:arr){
            System.out.println(s);
        }
    }
}
