package String.medium;

import java.util.Stack;

/**
  *@Author JunLin
  *@Date 2020/10/26
  *@Describe: 路径优化
使用了三种容器来求解绝对路径

首先定义栈用来存储路径信息，定义字符数组 str 来分隔字符串

依次遍历字符数组内容，这里使用增强型 for 循环，如果是 “..” 还要再判断是否为空才能弹出栈

如果不为空也不为 “.” 这说明当前元素是路径信息，入栈即可

最后遍历完之后，先判断栈中是否有元素，没有则返回 “/”

如果有元素，则使用 StringBuilder 来存放可变字符串，最后返回 ans 即可
 */

public class Simplify_Path_71 {
    public String simplifyPath(String path) {
        Stack<String> stack=new Stack<>();
        // ‘/’分隔存储到新的字符数组str中
        String[] str=path.split("/");
        // 为空和为. 情况默认不放行
        for (String s:str){
            // '..'情况，出栈，返回上一级
            if (s.equals("..")){
                if (!stack.isEmpty()){
                    stack.pop();
                }
                // 非空且不是. 说明是路径信息
            }else if (!s.equals("") && !s.equals(".") ){
                stack.push(s);
            }
        }
        // 如果栈内没有元素说明没有路径信息，返回 “/” 即可
        if (stack.isEmpty())
            return "/";
        StringBuilder ans=new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            ans.append("/"+stack.get(i));
        }
        // 这里从栈底开始拿元素
        return ans.toString();
    }
}
