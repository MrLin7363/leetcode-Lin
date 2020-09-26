package TenSortAlgorithm;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/07 17:45
 * @Describe:
 */
public class ShellSrot {
    public static int[] shellSort(int[] arr){
        if (arr==null || arr.length<2) return arr;
        int n=arr.length;
        for (int h=n/2;h>0;h/=2 ){
            //对各个局部进行插入排序
            for (int i=h;i<n;i++){
                // 将arr[i]插入到所在分组的正确位置上
                insertI(arr,h,i);
            }
        }
        return arr;
    }
    public static void insertI(int[] arr,int h ,int i){
        int j=i;
        int gap=h;
        int temp=arr[i];
        //对每个分组进行插入排序,只要temp比前面的小，移动位置
        while(j>= gap && temp < arr[j-gap]) {
            arr[j] = arr[j-gap];
            j-=gap;
        }
        arr[j] = temp;
        /*int temp=arr[i];
        int k;
        //这里是依次后移元素，这里是插入排序，前面的一个数比后面的大，才交换，每一趟能把当前第一个元素至当前元素排好序
        for (k=i-h; k>=0 && temp< arr[k]; k-=h){
            arr[k+h]=arr[k];
        }
        //插入元素，如果元素没有后移，这实际上是原来的位置等于原来的值
        arr[k+h]=temp;*/
    }

    public static void main(String[] args) {
        int[] test=new int[]{4,2,7,1,9};
        shellSort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}
