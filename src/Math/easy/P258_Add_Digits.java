package Math.easy;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/29
  *@Describe:
 */

public class P258_Add_Digits {
    // 数根数学 100 + 69
    public int addDigits4(int num) {
        return (num-1)%9+1;
    }


    /*
    循环递归  每个数+，知道剩最后一个数
    每次循环是一次相加  100 + 25
     */
    public int addDigits(int num) {
        if (num < 10) {
            return num;
        }
        int next = 0;
        while (num != 0) {
            next = next + num % 10;
            num /= 10;
        }
        return addDigits(next);
    }

    /*
    循环迭代  每个数+，知道剩最后一个数
    100 + 25
     */
    public int addDigits2(int num) {
        int dightRoot = 0;
        while (num > 0) {
            dightRoot += num % 10;
            num = num / 10;
            if (num == 0 && dightRoot > 9) {
                num = dightRoot;
                dightRoot = 0;
            }
        }
        return dightRoot;
    }

    public int addDigits3(int num) {
        while (num >= 10) {
            int next = 0;
            while (num != 0) {
                next = next + num % 10;
                num /= 10;
            }
            num = next;
        }
        return num;
    }

}
