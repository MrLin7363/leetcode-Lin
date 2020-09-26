package TenSortAlgorithm;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/07 15:25
 * @Describe:
 *  1、选取第一个元素作为中轴元素，每次把比中间元素小的元素排在中间元素的左边，
 *  大的在右边。这样下次排序就分开排序，有点分治的思想
 *  O nlogn
 *  O logn
 */
public class QuickSort {
    public static int[] quickSort(int[] arr,int left,int right){
        if (left<right){
            int mid=partition(arr,left,right);
            arr=quickSort(arr,left,mid-1);
            arr=quickSort(arr,mid+1,right);
        }
        return arr;
    }
    private static int partition(int[]arr, int left, int right){
        int i=left;
        int j=right;
        //中轴元素
        int pivot=arr[left];
        //当i==j时停止循环
        while (i<j){
            //从右边往左找比中轴元素小的
            while (i<j && arr[j]>=pivot) j--;
            // 再从左往右边找，直到找到比pivote值大的数
            while (i<j && arr[i]<=pivot) i++;
            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i<j){
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        //将pivot放在中间的位置
        arr[left]=arr[i];
        arr[i]=pivot;
        //此时的i==j
        return i;
    }

    public static void main(String[] args) {
        int[] test=new int[]{4,2,7,1,9};
        quickSort(test,0,test.length-1);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}
