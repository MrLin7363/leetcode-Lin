package String.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Junlin Chen
 * @Date: 2020/10/22 22:30
 * @Describe:
 * 1、暴力回溯法，不剪枝
 */
public class Generate_Parentheses_22 {
    public List<String> generateParenthesis(int n) {
        List<String> result=new ArrayList<>();
        // 2*n最多两个都有对应的括号
        generateAll(new char[2*n],0,result);
        return result;
    }

    private void generateAll(char[] cur,int pos,List<String>result){
        // 结束条件：长度等于对应长度，且检查当前括号是一一对应的
        if (pos==cur.length) {
            if (isValid(cur))
                result.add(new String(cur));
        }
        else{
            cur[pos]='(';
            generateAll(cur,pos+1,result);
            cur[pos]=')';
            generateAll(cur,pos+1,result);
        }

    }
    private boolean isValid(char[] cur){
        int balance=0;
        for (int i=0;i<cur.length;i++){
            if (cur[i]=='('){
                balance++;
            }else balance--;
            if (balance<0) // 右括号比左括号多
                return false;
        }
        // 为0则左右括号对应
        return balance==0;
    }

    /*
    ------------------------------------------------------------------------------
    2.剪枝法
     */
    public List<String> generateParenthesis2(int n) {
        ArrayList<String> result = new ArrayList<>();
        backtrack(result,"",0,0,n);
        return result;
    }
    private void backtrack(List<String> result, String cur,int open, int close, int max){
        // 结束条件
        if (cur.length()==max*2){
            result.add(cur);
//            return;   有无都行，一般来说是结束条件就return
        }
        // 左括号数量不大于 nn，我们可以放一个左括号
        if (open<max)
            backtrack(result,cur+"(",open+1,close,max);
        // 右括号数量小于左括号的数量，我们可以放一个右括号。
        if (close<open)
            backtrack(result,cur+")",open,close+1,max);
    }

}
