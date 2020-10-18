package String.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/22 12:57
 * @Describe:
 */
public class Longest_Common_Prefix_14 {
    //1、横向比较，看后面的字符串前缀是否都包含第一个字符串，如果不是，则第一个字符串缩减，再比较
    public String longestCommonPrefixByHorizontal(String[] strs) {
            if(strs.length==0)
                return "";
            // 记下第一个字符串
            String prefix=strs[0];
            // 剩下的和第一个逐一比较
            for(int i=1;i<strs.length;i++){
                // 如果prefix不在剩下的字符串的第一个位置出现，prefix就缩减一位
                while(strs[i].indexOf(prefix)!=0){
                    prefix=prefix.substring(0,prefix.length()-1);
                    if (prefix.isEmpty()) return "";
                }
            }
            return prefix;
    }

    //2、纵向比较，拿第一个字符串的字符逐一和后面的字符串的相同位置的字符比较
    public String longestCommonPrefixByVertical(String[] strs) {
        int len=strs.length;
        int n=strs[0].length();
        for (int i=0;i<n;i++){
            //记住每一次比较的字符
            char c=strs[0].charAt(i);
            for (int j=1;j<len;j++){
                //如果长度相等说明当前是最短的前缀了，后面不用比较
                if (i==strs[j].length() || c!=strs[j].charAt(i) ){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }
    /*
    ---------------------------------------------------------------------------
    3、分治法
     */
    //主函数
    public String longestCommonPrefixByDivideAndConquer(String[] strs) {
        if (strs.length==0 || strs==null) return "";
        else return longestCommonPrefix(strs,0,strs.length-1);
    }
    //递归函数
    public String longestCommonPrefix(String[] strs, int start, int end) {
        //遍历到最后一个，则返回
        if (start==end) return strs[start];
        else {
            //官方这样(end-start)/2+start也行但和二分法理解有差距
            int mid=(end+start)/2;
            String lcpLeft=longestCommonPrefix(strs,start,mid);
            String lcpRight=longestCommonPrefix(strs,mid+1,end);
            //判断两个字符串的最长公共前缀
            return commonPrefix(lcpLeft,lcpRight);
        }
    }
    //找出两个字符串的最长公共前缀
    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength=Math.min(lcpLeft.length(),lcpRight.length());
        for (int i=0;i<minLength;i++){
            if (lcpLeft.charAt(i)!=lcpRight.charAt(i))
                return lcpLeft.substring(0,i);
        }
        return lcpLeft.substring(0,minLength);
    }

    /*
    ----------------------------------------
    二分法
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        //防止溢出,取最小的字符串做比较
        Integer minLen=Integer.MAX_VALUE;
        for (String str:strs){
            minLen=Math.min(minLen,str.length());
        }
        int low=1;
        int high=minLen;
        while (low<=high){
            int middle=(low+high)/2;
            //如果前半端是公共前缀，则不用再判断前半端
            if (isCommonPrefix(strs,middle))
                low=middle+1;
            //如果前半端不是公共前缀，则向前二分判断
            else high=middle-1;
        }
        return strs[0].substring(0,(low+high)/2);
    }
    //判断当前长度的字符串在所有字符串中是不是公共前缀
    private boolean isCommonPrefix(String[] strs, int len){
        //取第一个判断
        String str=strs[0].substring(0,len);
        for (int i=1;i<strs.length;i++){
            //如果后面的不是以这个字符串为开头
            if (!strs[i].startsWith(str))
                return false;
        }
        return true;
    }
}
