package hw.基础学习.基础特性;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchTest {
    public static void main(String[] args) {
        String s = "\uFE64" + "script" + "\uFE65";
        String s2 = "<" + "script" + "\uFE65";
        Pattern pattern = Pattern.compile("[<>]");//  s1 false
        Pattern pattern2 = Pattern.compile("^<");//  s2 true
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            System.out.println("match");
        } else {
            System.out.println("not match");
        }
    }
}
