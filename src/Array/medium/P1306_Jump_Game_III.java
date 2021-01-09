package Array.medium;/*
    
/**
  *@Author JunLin
  *@Date 2021/1/9
  *@Describe:
 */

public class P1306_Jump_Game_III {
    int pre;
    boolean flag=false;
    public boolean canReach(int[] arr, int start) {
        pre=start;
        return rec(arr,start);
    }
    public boolean rec(int[] arr, int start){
        if (0==arr[start]){
            return true;
        }
        // 如果回到起始点
        if (start==pre && flag==true){
            return false;
        }
        if (start+arr[start]<=arr.length-1){
            flag=true;
            return rec(arr,start+arr[start]);
        }
        if (start-arr[start]>=0){
            flag=true;
            return rec(arr,start-arr[start]);
        }
        return false;
    }


}
