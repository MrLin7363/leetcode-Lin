package A1000PLAN.栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *desc:
 *@author lin
 *@since 2023/11/14
 **/
public class P71简化路径 {
    /*
    先进先出：其实用队列就可以
     */
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        String[] content = path.split("/");
        for (String str : content) {
            if (str.equals("") || str.equals(".")) {
                continue;
            }
            if (str.equals("..")) {
                deque.poll();
            } else {
                deque.push(str);
            }
        }
        if (deque.isEmpty()) {
            return "/";
        }
        StringBuilder res = new StringBuilder("/");
        while (!deque.isEmpty()) {
            res.append(deque.pollLast() + "/");
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "//s/";
        final String[] split = s.split("/");
        System.out.println(split);

        new P71简化路径().simplifyPath("/home//foo/");
    }
}
