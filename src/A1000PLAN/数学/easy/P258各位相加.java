package A1000PLAN.数学.easy;

/**
 *desc:
 *@author lin
 *@since 2023/12/1
 **/
public class P258各位相加 {
    /*
    1.能够被9整除的整数，各位上的数字加起来也必然能被9整除，所以，连续累加起来，最终必然就是9。
    2.不能被9整除的整数，各位上的数字加起来，结果对9取模，和初始数对9取摸，是一样的，所以，连续累加起来，最终必然就是初始数对9取摸。
    O1
     */
    public int addDigits(int num) {
        if (num > 0 && num % 9 == 0) {
            return 9;
        }
        return num % 9;
    }
    // 或者直接返回这个不好理解 return (num - 1) % 9 + 1;

    /*
    O n
     */
    public int addDigits2(int num) {
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }
}
