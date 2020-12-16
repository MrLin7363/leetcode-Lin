package utils;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/16
  *@Describe:
 */

import Construct.ListNode;

public class ListUtil {

    public static void printlnList(ListNode node){
        while (node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }
}
