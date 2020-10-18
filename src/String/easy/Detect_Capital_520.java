package String.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/10/15
  *@Describe: 检测大写，1.所有大写；2.所有小写；3.只有第一个大写
  word.charAt(i) >= 'A' && word.charAt(i) <= 'Z' 这个也能检测大小写
  2.3不管第一个如何，剩下都是i小写
 */

public class Detect_Capital_520 {
    public boolean detectCapitalUse(String word) {
        boolean max=true;
        boolean min=true;
        for (int i = 0; i < word.length(); i++) {
            if ((i+1)!=word.length() && Character.isUpperCase(word.charAt(i+1))){
                min=false;
            }
            if (Character.isLowerCase(word.charAt(i))){
                max=false;
            }
        }
        if (min==true)  // 除了第一个剩下都是小写，2.3.情况合起来了，
            return true;
        if (max==true) // 所有大写
            return true;
        return false;
    }

    /*
    速度慢不推荐
     */
    public boolean detectCapitalUse2(String word) {
//        return word.matches("[A-Z]*|[a-z]*|[A-Z][a-z]*");
        return word.matches("[A-Z]*|.[a-z]*"); // .匹配一个任意字符
    }
}

