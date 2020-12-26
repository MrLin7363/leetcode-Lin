package Math.hard;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/26
  *@Describe:
 */

public class P65_Valid_Number {
    /*
    有限状态机DFA  81 + 84
     */
    public boolean isNumber(String s) {
        int state=0;
        int finals=0b101101000; //二进制，前面0b是二进制标识要加 右第3,5,6,8 为 1
        int[][] transfer = new int[][]{{ 0, 1, 6, 2,-1},
                {-1,-1, 6, 2,-1},
                {-1,-1, 3,-1,-1},
                { 8,-1, 3,-1, 4},
                {-1, 7, 5,-1,-1},
                { 8,-1, 5,-1,-1},
                { 8,-1, 6, 3, 4},
                {-1,-1, 5,-1,-1},
                { 8,-1,-1,-1,-1}};
        for (char ss:s.toCharArray()){
            int id=make(ss);
            if (id<0) return false; // -1 为不合理字符
            state=transfer[state][id];
            if (state<0) return false; // -1 为该字符添加后不是数字
        }
        // 位与
//        if (state ==3 || state == 5 || state == 6 || state == 8) return true;
//        return false;
        // 位运算，等价于上面， 1 << state 左移几位
        return ( finals & ( 1<<state ) ) >0 ;
    }
    public int make(char c){
        switch (c){
            case ' ': return 0;
            case '+':
            case '-': return 1;
            case '.': return 3;
            case 'e': return 4;
            default:
                if (c>=48 && c <= 57) return 2; // [0,9]
        }
        return -1; // 其他字符
    }


    /*
    逻辑判断
    1.判断是否属于数字的0~9区间
2.遇到点的时候，判断前面是否有点或者E，都需要return false
3.遇到E的时候，判断前面数字是否合理，是否有E，并把numSeen置为false，防止E后无数字
4.遇到-+的时候，判断是否是第一个，如果不是第一个判断是否在E后面，都不满足则return false
5.其他情况都为false
最后返回numSeen的结果即可
    81 + 92
     */
    public boolean isNumber2(String s) {
        if (s.length()==0 ||s==null) return false;
        boolean numSeen=false;
        boolean dotSeen=false;
        boolean eSeen=false;
        char[] arry=s.trim().toCharArray(); // 去空格
        for (int i = 0; i < arry.length; i++) {
            if (arry[i]>=48 && arry[i]<=57){  // >='0' <='9' 一样
                numSeen=true;
            }else if (arry[i]=='.'){
                if (dotSeen || eSeen){
                    return false;
                }
                dotSeen=true;
            }else if (arry[i]=='e' || arry[i]=='E'){
                if (eSeen || !numSeen){
                    return false;
                }
                eSeen=true;
                numSeen=false;
            }else if (arry[i]=='+' || arry[i]=='-'){
                // +-只能在第一位或者跟在 e E 后面
                if (i!=0 && arry[i-1]!='e' && arry[i-1]!='E'){
                    return false;
                }
            }else{ // 空格，其他字符
                return false;
            }
        }
        return numSeen;
    }

}
