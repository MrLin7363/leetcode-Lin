package Math.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/20 20:00
 * @Describe:
 */
public class Reverse_Integer_7 {

    public int reverse(int x) {
        String s=String.valueOf(x);
        int[] res=new int[s.length()];
        res[0]=-2;
        int j=0;
        //首位为-
        if("-".equals(s.charAt(0)) && !"-".equals(res[0])){
            res[j++]=s.charAt(0);
        }
        for(int i=s.length()-1;i>=0;i++){
            //为0
            if(s.charAt(i)==0&&res[0]!=0){
                res[j++]=s.charAt(i);
            }
            //不为0
            else{
                res[j++]=s.charAt(i);
            }
        }
        //转为数字；
        String result=new String();
        for(int i=0;i<result.length();i++){

        }
        return 0;
    }

}
