package TenSortAlgorithm;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/22
  *@Describe: 小标对应着值
 */

public class CountSort {
    /*
    优化版，统计数组只需要最小和最大值之差的大小
    */
    public static int[] countSort(int[] arr){
        int n=arr.length;
        // 1.得到数组最大值
        int max=arr[0];
        int min=arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i]>max){
                max=arr[i];
            }
            if (arr[i]<min){
                min=arr[i];
            }
        }
        //2.根据数列的最大值最小值之差 确定统计数组的长度
        int[] countArr=new int[max-min+1];
        //3.遍历数列，填充统计数组
        for (int i = 0; i < n; i++) {
            countArr[arr[i]-min]++;
        }
        //4.遍历统计数组，输出结果
        int index=0;
        int[] sortedArr=new int[n];
        for (int i = 0; i < countArr.length; i++) {
            // 这个小标i就是值，countArr[i]就是有多少个这个值
            for (int j = 0; j < countArr[i]; j++) {
                sortedArr[index++]=i+min;
            }
        }
        return sortedArr;
    }
    public static int[] countSort2(int[] arr){
        int n=arr.length;
        // 1.得到数组最大值
        int max=arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i]>max){
                max=arr[i];
            }
        }
        //2.根据数列的最大值确定统计数组的长度
        int[] countArr=new int[max+1];
        //3.遍历数列，填充统计数组
        for (int i = 0; i < n; i++) {
            countArr[arr[i]]++;
        }
        //4.遍历统计数组，输出结果
        int index=0;
        int[] sortedArr=new int[n];
        for (int i = 0; i < countArr.length; i++) {
            // 这个小标i就是值，countArr[i]就是有多少个这个值
            for (int j = 0; j < countArr[i]; j++) {
                sortedArr[index++]=i;
            }
        }
        return sortedArr;
    }


    public static void main(String[] args) {
        int[] arr=countSort2(new int[]{2,3,41,24,12,43,12,3});
        for (int s:arr){
            System.out.println(s);
        }
    }
}
