package TenSortAlgorithm;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/07 17:45
 * @Describe: 将数组分为无数个小数组，最后两个两个合并
 *   [left,mid] , [mid+1,right] 形式的
 * O nlogn
 * O n
 */
public class MergeSort {
    //递归版归并排序
    public static int[] mergeSort(int[]arr, int left, int right){
        // 如果 left == right，表示数组只有一个元素，则不用递归排序
        if (left < right){
            // 把大的数组分隔成两个数组
            int mid=(left+right)/2;
            // 对左半部分进行排序
            mergeSort(arr,left,mid);
            // 对右半部分进行排序
            mergeSort(arr,mid+1,right);
            //进行合并
            merge(arr,left,mid,right);
        }
        return arr;
    }
    public static void merge(int[]arr,int left,int mid,int right){
        //创建一个新数组
        int[] a=new int[right-left+1];
        int i=left;
        int j=mid+1;
        int k=0;
        while (i<= mid && j <= right){
            //比较然后加入新的数组
            if (arr[i]<arr[j])
                a[k++]=arr[i++];
            else a[k++]=arr[j++];
        }
        //剩下多余的元素,直接加入数组
        while (i<=mid) a[k++]=arr[i++];
        while (j<=right) a[k++]=arr[j++];
        // 把临时数组复制到原数组
        for ( i = 0; i < k; i++) {
            arr[left++]=a[i];
        }
    }

    public static void main(String[] args) {
        int[] test=new int[]{4,2,7,1,9};
        mergeSort(test,0,test.length-1);
        mergeSortNonRecursion(test);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
    //非递归的归并排序
    public static int[] mergeSortNonRecursion(int[]arr){
        int n=arr.length;
        // 子数组的大小分别为1，2，4，8...
        // 刚开始合并的数组大小是1，接着是2，接着4....
        for (int i = 1; i < n; i+=i) {
            int left=0;
            int mid=left+i-1;
            int right=mid+i;
            //进行合并，对数组大小为 i 的数组进行两两合并, 从左往右
            while (right<n){
                merge(arr,left,mid,right);
                left=right+1;
                mid=left+i-1;
                right=mid+i;
            }
            // 因为不可能每个字数组的大小都刚好为 i，所以剩下一些数组,这可能是一个i大小的数组和一个不是i大小的数组
            if (left<n && mid< n){
                merge(arr,left,mid,n-1);
            }
        }
        return arr;
    }
}
