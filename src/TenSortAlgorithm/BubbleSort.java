package TenSortAlgorithm;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/07 15:14
 * @Describe: 改进算法，如果一遍下来没有发生位置交换，则不用再循环，使用标志位flag判断
 * O n^2
 * O 1
 */
public class BubbleSort {
    public static int[] bubbleSort(int[] arr){
        if(arr==null ||arr.length<2){
            return arr;
        }
        int n=arr.length;
        //每一趟能找到最右边也就是最大的数，然后遍历剩下的
        for (int i = 0; i < n; i++) {
            boolean flag=true;
            for (int j = 0; j <n-i-1 ; j++) {
                if (arr[j+1]<arr[j]){
                    flag=false;
                    int t=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=t;
                }
            }
            //如果==true，没有发生排序交换
            if (flag) break;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] test=new int[]{4,2,7,1,9};
        bubbleSort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}
