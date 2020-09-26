package String.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/12 17:28
 * @Describe:
 */
public class Reverse_String_344 {
    //双指针法
    public void reverseString(char[] s) {
        int i=0,j=s.length-1;
        char temp;
        while (i<=j){
            temp=s[i];
            s[i]=s[j];
            s[j]=temp;
            i++;
            j--;
        }
    }
    //递归版本
    public void reverseStringByRecursion(char[] s) {
        int left=0,right=s.length-1;
        reverseHelper(s,left,right);
    }
    public void reverseHelper(char[] s , int left,int right){
        if (left<right)
        {
            reverseHelper(s,left+1,right-1);
            char temp;
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
    }
}
