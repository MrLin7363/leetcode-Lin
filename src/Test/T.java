package Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/09 14:22
 * @Describe:
 */
public class T {
    public static boolean isPrime(int cp ){
        if (cp<2) return false;
        if (cp==2) return true;
        if (cp%2==0) return false;
        for (int i = 3; i < cp; i+=2) {
            if (cp%i==0) return false;
        }
        return true;
    }

    public static int reverse(int cp){
        char[] str=String.valueOf(cp).toCharArray();
        int len=str.length;
        char[] res=new char[len];
        int j=0;
        boolean flag=true;
        for (int i=len-1;i>=0;i--){
            if (str[i]!=0)
                flag=false;
            if (flag==false)
            res[j++]=str[i];
        }
        return Integer.parseInt(String.valueOf(res));
    }

   /* public static int[] sort(int[] arr){
        if (arr==null || arr.length<2) return arr;
        int n=arr.length;
        for (int i = 1; i < n; i++) {
            int temp=arr[i];
            int k=i-1;
            while (k>=0 && arr[k]>temp)
                k--;
            for (int j = i; j > k+1; j--) {
                arr[j]=arr[j-1];
            }
            arr[k+1]=temp;
        }
        return arr;
    }
*/
 /*   public static void printEmirps(int n){
        int[] reverseArray=new int[n];
        for (int i=2;i<n;i++){
            if (isPrime(i))
                reverseArray[i]=reverse(i);
        }
        sort(reverseArray);
        for (int i=0;i<reverseArray.length;i++){
            if (reverseArray[i]!=0)
            System.out.print(reverseArray[i]+",");
        }
    }*/

    public static void main(String[] args) {
//        printEmirps(150);
        System.out.println(reverse(94));
    }

}
