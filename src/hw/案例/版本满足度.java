 

package hw.案例;

/**
 * desc:  转化为字符串去写
 *
 * @author junlin
 * @since 2022/1/14
 **/
public class 版本满足度 {

    public static void main(String[] args) {
        String[][] res = new String[1][2];
        res[0][0]=">1.0.0";
        res[0][1]="<=999.999.999";
        check(res);
    }

    /*
    主要为转化为 1000000 六个0的数字
     */
    private static boolean[] check(String[][] rangePairs) {
        boolean[] result = new boolean[rangePairs.length];
        for (int i = 0; i < rangePairs.length; i++) {
            result[i] = checkRange(getRange(rangePairs[i][0]), getRange(rangePairs[i][1]));
        }
        return result;
    }

    private static int[] getRange(String versionRange) {
        // \\w 匹配字母字数下划线， 第一个字母会分割，[0]就是符号
        String symbol = versionRange.split("\\w")[0];
        String[] versions = versionRange.substring(symbol.length()).split("\\.");
        int times = 1000000;
        int intVersion = 0;
        for (String version : versions) {
            intVersion += Integer.parseInt(version) * times;
            times /= 1000;
        }
        switch (symbol) {
            case "=":
                return new int[] {intVersion, intVersion};
            case ">":
                return new int[] {intVersion + 1, 999999999};
            case ">=":
                return new int[] {intVersion, 999999999};
            case "<":
                return new int[] {0, intVersion - 1};
            case "<=":
                return new int[] {0, intVersion};
            default:
                return new int[] {intVersion, intVersion + getSpecialRange(versions) - 1};
        }
    }

    private static int getSpecialRange(String[] versions) {
        if (1 == versions.length) {
            return 1000000;
        }
        if (2 == versions.length) {
            return 1000;
        }
        return 1000 - Integer.parseInt(versions[2]);
    }

    /*
    这里还可以再优化
     */
    private static boolean checkRange(int[] rangeA, int[] rangeB) {
        boolean aInB = (rangeA[0] >= rangeB[0] && rangeA[0] <= rangeB[1]) || (rangeA[1] >= rangeB[0]
                && rangeA[1] <= rangeB[1]);
        boolean bInA = (rangeB[0] >= rangeA[0] && rangeB[0] <= rangeA[1]) || (rangeB[1] >= rangeA[0]
                && rangeB[1] <= rangeA[1]);
        return aInB || bInA;
    }


    /*
    区别点：

l  String symbol = versionRange.split("\\w")[0];通过自带的字符串拆分函数，获得前缀（六种前缀都可以获取到）

l  前缀为~时的处理细节有所不同，通过判断数组versions的长度，分别做不同处理：

a)         仅有主版本：直接加1000000

b)        给定了主版本和次要版本、无修订版本：直接加1000

c)         给定了主版本和次要版本、修订版本：intVersion + 1000 - Integer.parseInt(versions[2]) - 1

这样做的原因是题目要求“如果给定了次要版本，则从该版本到下一个次要版本”，如 ~1.9.5 表示 [1.9.5, 1.10.0)，即需要舍掉修订版本。

intVersion里面已经包含了修订版本，所以这里要减去它。

代码还是很简洁易懂，函数checkRange写的稍微复杂了些，只需要一半的判断代码就可以，例如这样：

return (rangeB[0] >= rangeA[0] && rangeB[0] <= rangeA[1]) || (rangeA[0] >= rangeB[0] && rangeA[0] <= rangeB[1]);

     */
}
