package Array.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/11/05 0:06
 * @Describe: DP历史最低点是动态变化的
 */
public class Best_Time_to_Buy_and_Sell_Stock_121 {
    public int maxProfit(int[] prices) {
        int minprice=Integer.MAX_VALUE;
        int maxprofit=0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i]<minprice){ // 价格比历史最低点小
                minprice=prices[i];
            }else if (prices[i]-minprice>maxprofit){ // 价格差比最大收益还要大
                maxprofit=prices[i]-minprice;
            }
        }
        return maxprofit;
    }
}
