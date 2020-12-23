package Math.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/13 22:39
 * @Describe:
 */
public class Count_Primes_204 {


    /*
    厄拉多塞筛法 89 + 51
     */
    public int countPrimes(int n){
        int count=0;
        boolean[] isPrime=new boolean[n];
        //先都赋值为true，布尔数组没有默认
        for (int i=2;i<n;i++){
            isPrime[i]=true;
        }
        //质数n,2到根号n之间的都没有能被整除，则是质数
        for (int i=2;i*i<n;i++){
            if (!isPrime[i]) continue;
            //把平方+i的数去掉，这样每次判断质数少判断很多 如 i = 2 排除  4 5 6 8 10 12 ...
            for (int j=i*i;j<n;j+=i){
                isPrime[j]=false;
            }
        }
        for (int i=2;i<n;i++){
            if (isPrime[i]) count++;
        }
        return count;
    }

    /*
       超时枚举 [ 2 , 根号n ]
    */
    public int countPrimes2(int n) {
        int ans=0;
        for (int i = 2; i < n; i++) {
            ans+=isPrime(i)?1:0;
        }
        return ans;
    }
    private boolean isPrime(int x ){
        for (int i = 2; i*i<=x; i++) {
            if (x%i==0){
                return false;
            }
        }
        return true;
    }
}
