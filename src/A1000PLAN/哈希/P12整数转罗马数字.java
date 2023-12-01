package A1000PLAN.哈希;

/**
 *desc:
 *@author lin
 *@since 2023/11/21
 **/
public class P12整数转罗马数字 {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /*
    1.模拟
     */
    public String intToRoman(int num) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            int val = values[i];
            String symbol = symbols[i];
            while (num >= val) {
                num -= val;
                sb.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return sb.toString();
    }

    /*
    2.硬编码
     */
    private String[] thousands = {"", "M", "MM", "MMM"};

    // 1-10
    String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};

    String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};

    String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public String intToRoman2(int num) {
        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }
}
