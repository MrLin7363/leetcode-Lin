package TenSortAlgorithm;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/07 16:43
 * @Describe: 选择数组最小的元素和第一个元素互换，在剩下的元素里选择最小的元素和第二个元素互换。
 *  空间O n^2
 *  时间O 1
 */
public class SelectionSort {
    public static int[] selectionSort(int[] arr){
        int n=arr.length;
        for (int i = 0; i < n-1; i++) {
            int min=i;
            //找到最小的下标
            for (int j = i+1; j < n; j++) {
                if (arr[min]>arr[j])
                    min=j;
            }
            //交换
            int temp=arr[min];
            arr[min]=arr[i];
            arr[i]=temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] test=new int[]{4,2,7,1,9};
        selectionSort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}
