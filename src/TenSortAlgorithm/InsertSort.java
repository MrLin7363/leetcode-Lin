package TenSortAlgorithm;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/07 16:15
 * @Describe:
 *  1、从第二个元素开始抽取元素
 *  2、拿这个元素和已经有序的最大的元素也就是左边第一个元素相比，直到遇到不比这个数大的元素，插入到当前位置的右边
 *  3、继续选取第3,4个元素
 *
 *  O n^2
 *  O 1
 */
public class InsertSort {
    public static int[] insertSort(int[] arr){
        if (arr==null || arr.length<2) return arr;
        int n=arr.length;
        //依次遍历每一个元素
        for (int i = 1; i < n; i++) {
            //记住当前要插入的元素
            int temp=arr[i];
            //要插入元素的前一个元素的下标
            int k=i-1;
            //找到第一个不比当前元素大的下标
            while (k>=0 && arr[k]>temp)
                k--;
            //依次后移元素腾出位置给迭代元素temp插入，注意k+1在比temp小的数的后一位
            for (int j = i; j > k+1; j--) {
                arr[j]=arr[j-1];
            }
            //将迭代元素temp插入
            arr[k+1]=temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] test=new int[]{4,2,7,1,9};
        insertSort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}
