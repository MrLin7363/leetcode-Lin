package InterviewTopic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Junlin Chen
 * @Date: 2021/03/15 22:10
 * @Describe:
 */
public class se {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        int count=in.nextInt();
        System.out.println(count(n,count));
    }
    public static int count(String s,int k){
        int[] arr=new int[s.length()];
        int index=0;
        int count=0;
        int i;
        for ( i = 1; i < s.length(); i++) {
            if (s.charAt(i-1)==s.charAt(i)){
                count++;
            }else{
                count++;
                arr[index++]=count;
                count=0;
            }
        }
            count++;
            arr[index++]=count;
            if (index<k) return -1;
        Arrays.sort(arr);
        return arr[arr.length-k];
    }
}
