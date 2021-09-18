package Array.medium;

/**
 * @author: Junlin Chen
 * @Date: 2021/09/16 13:54
 * @Describe: 快速排序 + 选择算法
 */
public class P215_Kth_Largest_Element_in_Array {

    /*
    第k大的数 = 第 length-k个数
    26  15
     */
    private static int res;
    public static int findKthLargest(int[] nums, int k) {
        if (k>nums.length){
            return -1;
        }
        quickSort(nums,0,nums.length-1,nums.length-k);
        return nums[res];
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4},2));
    }

    public static int[] quickSort(int[] arr,int left,int right,int k){
            // 每次排一个节点
            int mid=partition(arr,left,right,k);
            if (mid==k){
                res=mid;
                return arr;
            }
            // 选择往一边排序
            if (mid>k){
                arr=quickSort(arr,left,mid-1,k);
            }else{
                arr=quickSort(arr,mid+1,right,k);
            }
        return arr;
    }
    /*
    先选取一个中间元素，比如最左边的4，然后交换比4大和比4小的元素，
    最后i==j就是中间元素该放的中间位置
     */
    private static int partition(int[]arr, int left, int right,int k){
        int i=left;
        int j=right;
        //中轴元素,这里随机选择最左边元素
        int pivot=arr[left];
        //当i==j时停止循环，找到中间元素
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
        // 剩下的中间位置的和一开始选的中轴位置的互换
        arr[left]=arr[i];
        arr[i]=pivot;
        //此时的i==j
        return i;
    }

}
