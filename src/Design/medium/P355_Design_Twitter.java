package Design.medium;/*
    
/**
  *@Author JunLin
  *@Date 2020/12/20
  *@Describe:
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class P355_Design_Twitter {
    /*
    哈希 + 链表   67 + 28
     */
    // 存储用户信息
    static class Node{
        Set<Integer> followee; // 关注的人的 ID
        LinkedList<Integer> tweets; //  用链表存储 tweetId
        public Node(){
            followee=new HashSet<>();
            tweets=new LinkedList<>();
        }
    }
    static class Twitter {
        private int recentMax;  // getNewsFeed 检索的推文的上限
        private int time;//tweetId 的时间戳
        private Map<Integer,Integer> tweetTime; // tweetId 对应发送的时间
        private Map<Integer,Node> users;   // 每个用户存储的信息

        /** Initialize your data structure here. */
        public Twitter() {
            recentMax=10;
            time=0;
            tweetTime=new HashMap<>();
            users=new HashMap<>();
        }
        // 初始化用户
        private void init(int userId){
            users.put(userId,new Node());
        }
        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            // 用户不存在
            if (!users.containsKey(userId)){
               init(userId);
            }
            // 达到限制，剔除链表末尾元素，有线程安全问题
            if (users.get(userId).tweets.size()==recentMax){
                users.get(userId).tweets.remove(recentMax-1);
            }
            users.get(userId).tweets.addFirst(tweetId); // 添加推文
            tweetTime.put(tweetId,time++); // 总体时间 + 1
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed.
         * Each item in the news feed must be posted by users who the user followed or by the user herself.
         * Tweets must be ordered from most recent to least recent.
         * 获取所关注用户最近的10条 tweet ，相当于合并K个有序链表，链表的结点为 tweet 发布时间
         * */
        public List<Integer> getNewsFeed(int userId) {
            LinkedList<Integer> ans = new LinkedList<>();
            // 获取自己的 推特链表信息
            for (int it:users.getOrDefault(userId,new Node()).tweets){
                ans.addLast(it);
            }
            // 线性合并多个链表，逐个将每个关注用户的推特信息，和自己的推特信息链表合并
            for (int followeeId:users.getOrDefault(userId,new Node()).followee){
                if (followeeId!=userId) { // 可能出现自己关注自己的情况
                    ans = mergeTwoLists(followeeId, ans);
                }
            }
            return ans;
        }
        // 线性合并
        public LinkedList<Integer> mergeTwoLists(int followeeId , LinkedList<Integer> ans) {
            LinkedList<Integer> res = new LinkedList<>();
            int tweetSize = users.get(followeeId).tweets.size();
            Iterator<Integer> it = users.get(followeeId).tweets.iterator();
            // 合并两个链表
            int i = 0, j = 0;
            int cur=-1;
            if (j<tweetSize) {
                cur = it.next();// 获取当前推特的Id
            }
            while (i < ans.size() && j<tweetSize ) {
                if (tweetTime.get(cur) > tweetTime.get(ans.get(i))) {
                    res.addLast(cur); // 时间越大越近
                    if (it.hasNext()) {
                        cur = it.next();
                    }
                    j++;
                } else {
                    res.addLast(ans.get(i));
                    i++;
                }
                // 已经找到这两个链表合起来后最近的 recentMax 条推文
                if (res.size() == recentMax) {
                    break;
                }
            }
            // 合并剩下的链表
            while (i < ans.size() && res.size() < recentMax) {
                res.addLast(ans.get(i++));
            }
            if (j<tweetSize && res.size()<recentMax) {
                res.addLast(cur);
                while (it.hasNext() && res.size() < recentMax) {
                    res.addLast(it.next());
                }
            }
            return res;
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            //  无自身
            if (!users.containsKey(followerId)){
                init(followerId);
            }
            // 无关注者
            if (!users.containsKey(followeeId)){
                init(followeeId);
            }
            users.get(followerId).followee.add(followeeId);
        }
        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if (!users.containsKey(followerId)){
                return;
            }
            users.get(followerId).followee.remove(followeeId);
        }
    }

    public static void main(String[] args) {
        Twitter twitter=new Twitter();
        twitter.postTweet(1,5);
        twitter.follow(1,2);
        twitter.postTweet(2,6);
        twitter.getNewsFeed(1);
        twitter.follow(2,1);
        twitter.getNewsFeed(2);
    }
}
