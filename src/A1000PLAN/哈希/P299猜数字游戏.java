package A1000PLAN.哈希;

/**
 *desc:
 *@author lin
 *@since 2023/11/29
 **/
public class P299猜数字游戏 {
    /*
    哈希+计数  自己写完成
    On+On  一趟遍历，过程记录位置和数组同的作为公牛；哈希计数 如果有剩余哈希算作奶牛
     */
    public String getHint(String secret, String guess) {
        int[] nums = new int[10];
        int n = secret.length();
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) != guess.charAt(i)) {
                nums[secret.charAt(i) - '0']++;
            }
        }
        int aCount = 0;
        int bCount = 0;
        for (int i = 0; i < n; i++) {
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            if (c1 == c2) {
                aCount++;
            } else if (nums[c2 - '0'] > 0) {
                bCount++;
                nums[c2 - '0']--;
            }
        }
        return aCount + "A" + bCount + "B";
    }

    // 官方的
    public String getHint2(String secret, String guess) {
        int bulls = 0;
        int[] cntS = new int[10];
        int[] cntG = new int[10];
        for (int i = 0; i < secret.length(); ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++bulls;
            } else {
                ++cntS[secret.charAt(i) - '0'];
                ++cntG[guess.charAt(i) - '0'];
            }
        }
        int cows = 0;
        for (int i = 0; i < 10; ++i) {
            cows += Math.min(cntS[i], cntG[i]);
        }
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        new P299猜数字游戏().getHint("1123", "0111");
        new P299猜数字游戏().getHint("1122", "1222");
        new P299猜数字游戏().getHint("1807", "7810");
    }
}
