package Math.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/21 20:13
 * @Describe:
 */
public class P12_IntegerToRoman {
    public String intToRoman(int num) {
       String[] roman={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
       int[] values=new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
       int sum=0;
       int i=0;
       String res="";
       while(sum<num){
           if(sum+values[i]>num){
               i++;
               continue;
           }
           sum+=values[i];
           res+=roman[i];
       }
       return res;
    }
}
