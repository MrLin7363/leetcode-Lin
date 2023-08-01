package Array.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/25 11:21
 * @Describe:
 */
public class P27移除元素 {
    public int removeElement(int[] nums, int val) {
        int n=nums.length;
        int i =0;
        while(i<n){
            if(nums[i]==val){
                nums[i]=nums[n-1];
                n--;
            }else{
                i++;
            }
        }
        return n;
       /* int i=0;
        for(int j=0;j<nums.length;j++){
            if(nums[j]!=val){
                nums[i++]=nums[j];
            }
        }
        return i;*/
    }

    public static void main(String[] args) {
        int[] t=new int[2];
        System.out.println(t[0]+"+"+t[1]);
    }
}
