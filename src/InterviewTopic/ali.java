package InterviewTopic;

/**
 * @author: Junlin Chen
 * @Date: 2021/03/14 19:56
 * @Describe:
 */
import java.util.Scanner;

public class ali {
    // 本题为考试单行多行输入输出规范示例，无需提交，不计分。
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            if(n<=0) {System.out.println(n);
            return;}
            int res=n;
            for (int i = 0; i < n; i++) {
                if (i>4&&i/4==0){
                    res--;
                    continue;
                }
                if (!isFour(i)){
                    res--;
                }
            }
            System.out.println(res);
        }
        public static boolean isFour(int num){
            String ss=String.valueOf(num);
            for (int i = 0; i < ss.length(); i++) {
                if (ss.charAt(i)=='4'){
                    return false;
                }
            }
            return true;
        }
}
