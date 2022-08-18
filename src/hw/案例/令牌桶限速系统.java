 

package hw.案例;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc: time是递增的
 *
 * @author junlin
 * @since 2022/1/18
 **/
public class 令牌桶限速系统 {
    Map<Integer, List<Integer>> rules = new HashMap<>();

    int leftTokens = 0; // 当前剩余token

    int maxTokens; // token最大数量

    public 令牌桶限速系统(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public boolean addRule(int ruleId, int time, int interval, int number) {
        if (rules.containsKey(ruleId)) {
            return false;
        }
        List<Integer> rule = new ArrayList<>(3);
        rule.add(time);
        rule.add(interval);
        rule.add(number);
        rules.put(ruleId, rule);
        return true;
    }

    /*
    删除 token 时也要计算生成的token
     */
    public boolean removeRule(int ruleId, int time) {
        if (!rules.containsKey(ruleId)) {
            return false;
        }
        List<Integer> rule = rules.get(ruleId);
        refreshToken(rule, time);
        rules.remove(ruleId);
        return true;
    }

    /*
    先查询当前剩余token,查询的时候将所有规则都刷新了一遍
     */
    public boolean transferData(int time, int size) {
        queryToken(time);
        if (leftTokens < size) {
            return false;
        }
        leftTokens -= size;
        return true;
    }

    /*
    查询当前剩余可用token,刷新所有规则
     */
    public int queryToken(int time) {
        rules.forEach((index, rule) -> {
            refreshToken(rule, time);
        });
        return leftTokens;
    }

    /*
    刷新当前规则，更新token剩余数量,并且记录时间
     */
    private void refreshToken(List<Integer> rule, int time) {
        // token产生时间
        int lastTime = rule.get(0);
        if (time >= lastTime) {
            leftTokens += ((time - lastTime) / rule.get(1) + 1) * rule.get(2);
            while (time >= lastTime) {
                lastTime += rule.get(1);
            }
            // 更新token最新产生时间
            rule.set(0, lastTime);
        }
        // 抛弃溢出的token
        leftTokens = Math.min(maxTokens, leftTokens);
    }

    public static void main(String[] args) {
        令牌桶限速系统 solu = new 令牌桶限速系统(8);
        System.out.println(solu.addRule(0, 0, 1, 3));
        System.out.println(solu.addRule(1, 2, 2, 1));
        System.out.println(solu.transferData(3, 12));
        System.out.println(solu.removeRule(3 ,4));
        System.out.println(solu.removeRule(0 ,5));
        System.out.println(solu.transferData(6, 8));
        System.out.println(solu.queryToken(7));
        System.out.println(solu.removeRule(1 ,8));
        System.out.println(solu.queryToken(9));
        System.out.println(solu.addRule(0, 10, 2, 2));
        System.out.println(solu.queryToken(12));
        System.out.println(solu.addRule(0, 13, 2, 2));
        System.out.println(solu.transferData(14, 8));
    }
}
