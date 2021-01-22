package TenSortAlgorithm;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/07 17:46
 * @Describe: nlogn
 */
public class HeapSort {
    public static int[] heapSort(int[] arr){
        int n=arr.length;
        //构建大顶堆，从第倒数第二层有孩子结点的结点开始向前遍历,每一次在倒数第二层构造一个小的大顶堆，后面就能合起来了
        for (int i= (n-2)/2; i>=0 ; i--){
            downAjust(arr, i , n-1);
        }
        //父节点下沉，进行堆排序
        for (int i=n-1; i>=1 ; i-- ){
            //交换头尾元素
            int temp=arr[i];
            arr[i]=arr[0];
            arr[0]=temp;
            //再次构建大顶堆,因为出了根节点，其他结点都是大顶堆，所以这里 parent = 0 ,最后一个节点不用管
            downAjust(arr,0, i-1 );
        }
        return arr;
    }
    public static void downAjust(int[] arr, int parent, int n){
        //记下父节点的值
        int temp=arr[parent];
        //这是左孩子结点
        int child= 2 * parent+1;
        //父节点下沉,如果没有孩子结点则循环结束
        while (child<=n){
            //从左右孩子中找较大的孩子下沉，这里条件为右孩子存在，且大于左孩子
            if (child +1 <=n && arr[child+1] > arr[child] )
                child++;
            //如果孩子结点小于或等于父节点，则下沉结束
            if (arr[child]<=temp)
                break;
            //子节点上移
            arr[parent]=arr[child];
            //父节点下标下沉
            parent=child;
            //子节点也随下移判断
            child=2*parent+1;
        }
        //父节点赋值到最终下沉位置
        arr[parent]=temp;
    }

    public static void main(String[] args) {
        int[] test=new int[]{4,2,7,1,9,2,4,5,4,2,1,23};
        heapSort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
}
