package String.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/06/29 15:18
 * @Describe: 思路，用递归，从1开始返回算,如1->11->21->1211->111221
 */
public class Count_and_Say_38 {
    public String countAndSay(int n) {
        StringBuilder s=new StringBuilder();
        int pl=0;
        int cur=1;
        if (n==1){
            return "1";
        }
        //从1开始递归
        String str=countAndSay(n-1);
        //如果后面的与前一个不一样，则加入个数和数字，如21，则添加12
        for (cur = 1; cur < str.length(); cur++) {
            if(str.charAt(pl)!=str.charAt(cur)){
                int count=cur-pl;
                s.append(count).append(str.charAt(pl));
                pl=cur;
            }
        }
        //剩下最后相等的或者1个的加进去，如21上面遍历了2，剩1，这里为加入11
        int count=cur-pl;
        s.append(count).append(str.charAt(pl));
        return s.toString();
    }
}
