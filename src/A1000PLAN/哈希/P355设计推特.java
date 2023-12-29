package A1000PLAN.哈希;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * desc:
 *
 * @author Lin
 * @since 2023/12/25
 **/
public class P355设计推特 {
    // 可以优化：题目要求最多10个推特，如果关注的某个博主超过10个可以删除
    static class Twitter {
        private Map<Integer, Set<Integer>> follows = new HashMap<>(); // 关注的人

        private Map<Integer, List<Integer>> postTweets = new HashMap<>(); // 发布的博文

        private Map<Integer, Integer> tweetsTime = new HashMap<>(); // 发布博文的时间线

        private int index;

        public Twitter() {
            index = 0;
        }

        public void postTweet(int userId, int tweetId) {
            if (!postTweets.containsKey(userId)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(tweetId);
                postTweets.put(userId, list);
            } else {
                postTweets.get(userId).add(tweetId);
            }
            tweetsTime.put(tweetId, index);
            index++;
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>();
            // 关注的所有用户中 所有发布的博文时间按降序取前10,加上自己的关注
            Set<Integer> followSet = follows.getOrDefault(userId, new HashSet<>());
            followSet.add(userId);
            Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
            for (Integer user : followSet) {
                if (postTweets.containsKey(user)) {
                    for (Integer tweet : postTweets.get(user)) {
                        queue.add(new int[] {tweetsTime.get(tweet), tweet});
                    }
                }
            }
            if (queue.isEmpty()) {
                return res;
            }
            for (int i = 0; i < 10 && !queue.isEmpty(); i++) {
                res.add(queue.poll()[1]);
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            // 被关注的人添加当前任务
            if (!follows.containsKey(followerId)) {
                Set<Integer> set = new HashSet<>();
                set.add(followeeId);
                follows.put(followerId, set);
            } else {
                follows.get(followerId).add(followeeId);
            }
        }

        public void unfollow(int followerId, int followeeId) {
            if (!follows.containsKey(followerId)) {
                return;
            }
            Set<Integer> followers = follows.get(followerId);
            followers.remove(followeeId);
        }
    }

    public static void main(String[] args) {
        P355设计推特.Twitter twitter = new P355设计推特.Twitter();

        twitter.postTweet(1, 5);
        twitter.follow(1, 2);
        twitter.follow(2, 1);
        twitter.getNewsFeed(2);

        twitter.postTweet(2, 6);
        twitter.getNewsFeed(1);
        twitter.getNewsFeed(2);
    }
}
