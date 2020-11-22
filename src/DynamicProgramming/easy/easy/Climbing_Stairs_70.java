package DynamicProgramming.easy.easy;

/**
 * @author: Junlin Chen
 * @Date: 2020/07/14 16:47
 * @Describe: 跳台阶
 */
public class Climbing_Stairs_70 {
    /*
    递归优化版1
     */
    public int climbStairs(int n) {
        return Fabonacci(n,1,1);
    }
    private  int Fabonacci(int n,int a,int b) {
        if (n<=1) return b;
        //每次让sum意和当前数相加的思，递归相加
        return Fabonacci(n-1,b,a+b);
    }
    /*
    记忆递归优化版2,每一次记录过的节点不用在递归下去
     */
    public int climbStairs2(int n) {
        //用数组记录已经有结果的递归节点
        int[] memo=new int[n+1];
        return FabonacciMemo(n,memo);
    }
    private  int FabonacciMemo(int n,int[] memo) {
        //如果有记录，不用再递归
        if (memo[n]>0){
            return memo[n];
        }
        if (n==1) memo[n]=1;
        else if (n==2) memo[n]=2;
        else memo[n]=FabonacciMemo(n-1,memo)+FabonacciMemo(n-2,memo);
        return memo[n];
    }
    /*
     非递归优化，滚动数组
     */
    public int climbStairs3(int n) {
        if (n==1) return 1;
        if (n==2) return 2;
        int first=1,second=2;
        int sum=0;
        //每次相加只利用前两个变量，所以不需要数组记录
        for (int i=3;i<=n;i++){
            sum=first+second;
            first=second;
            second=sum;
        }
        return sum;
    }

}
