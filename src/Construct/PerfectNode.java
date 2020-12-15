package Construct;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/8
  *@Describe: 有点想跳表，树的每一层用next连接
 */

public class PerfectNode {
    public int val;
    public PerfectNode next;
    public PerfectNode left;
    public PerfectNode right;
    public PerfectNode(int val) { this.val = val; }
}
