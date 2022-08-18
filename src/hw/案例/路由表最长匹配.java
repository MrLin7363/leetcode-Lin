 

package hw.案例;

/**
 * desc:
 *
 * @author junlin
 * @since 2022/1/14
 **/
public class 路由表最长匹配 {

    /*
    1.获取ip地址转换后的 10位数全部拼在一起  如192.168.0.1 拼为 10010101010101001010101010之类的，每一个间隔8位数字
    2.和给定的IP表匹配，每次直接比较字符串是否相同，且最长匹配大于之前，result更新
     */

    private static String routerSearch(String dstIp, String[][] ipTable) {
        String result = "empty";
        int maxLen = 0;
        // 转换十进制
        String ipStrTen = getTenString(dstIp);
        for (String[] ipTableStr : ipTable) {
            String ipTableStrO = getTenString(ipTableStr[0]);
            Integer ipTableStrT = Integer.valueOf(ipTableStr[1]);
            if ("0".equals(ipTableStrT.toString())) {
                // 0是匹配的，只存在于0.0.0.0，但是匹配长度只有0，也就是其他匹配IP都优先于这个
                result = ipTableStr[0] + "/" + ipTableStrT;
            } else if (ipStrTen.substring(0, ipTableStrT).equals(ipTableStrO.substring(0, ipTableStrT))
                    && ipTableStrT > maxLen) {
                maxLen = ipTableStrT;
                result = ipTableStr[0] + "/" + ipTableStrT;
            }
        }
        return result;
    }

    private static String getTenString(String dstIp) {
        String ipStrTen = "";
//        String[] ipArry = dstIp.replace(".", ",").split(",");
        String[] ipArry = dstIp.split("\\.");
        for (String ipStr : ipArry) {
            String ipStrNew = Integer.toBinaryString(Integer.valueOf(ipStr));
            ipStrTen = ipStrTen + "00000000".substring(0, 8 - ipStrNew.length()) + ipStrNew;
        }
        return ipStrTen;
    }

    public static void main(String[] args) {
        String[][] res = new String[2][2];
        res[0][0] = "0.0.0.0";
        res[0][1] = "0";
        // 192.168.0.1/24
        res[1][0] = "192.168.0.1";
        res[1][1] = "24";
        routerSearch("192.168.0.3", res);
    }
}
