package String.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/29 16:00
 * @Describe:
 */
public class Implement_strStr_28 {
    public int strStr(String haystack, String needle) {
        int n=haystack.length(),L=needle.length();
        if(L==0) return 0;
        int pn=0;
        while (pn<n-L+1){
            //找到匹配字符第一个匹配的位置
            while (haystack.charAt(pn)!=needle.charAt(0)) pn++;

            //计算最大的匹配String
            int pL=0,currLen=0;
            while (pL<L && pn<n && haystack.charAt(pn) == needle.charAt(pL)){
                pL++;
                pn++;
                currLen++;
            }

            //如果最大匹配的是匹配字符串
            if(currLen==L){
                return pn-currLen;
            }

            //否则，backTrack，重新测算
            pn=pn-currLen+1;
        }
        return -1;
    }
}
