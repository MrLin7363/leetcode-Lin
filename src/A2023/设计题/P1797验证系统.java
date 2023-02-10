package A2023.设计题;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class P1797验证系统 {
    private int timeToLive;

    // tokenId, currentTime
    private Map<String, Integer> tokens = new HashMap<>();

    public P1797验证系统(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        tokens.put(tokenId, currentTime);
    }

    public void renew(String tokenId, int currentTime) {
        // 不存在或者过期
        if (!tokens.containsKey(tokenId) || tokens.get(tokenId) + timeToLive <= currentTime) {
            return;
        }
        tokens.put(tokenId, currentTime);
    }

    public int countUnexpiredTokens(int currentTime) {
        AtomicInteger count = new AtomicInteger(0);
        tokens.values().forEach((curTime) -> {
            if (curTime + timeToLive > currentTime && curTime <= currentTime) {
                count.incrementAndGet();
            }
        });
        return count.get();
    }
}
