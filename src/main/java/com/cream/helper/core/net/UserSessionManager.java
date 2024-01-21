package com.cream.helper.core.net;

import com.cream.helper.core.ExecutorManager;
import com.cream.helper.core.net.bo.UserSession;
import com.cream.helper.utils.Times;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 管理RoleSession
 */
@Service
public class UserSessionManager {

    private final ExecutorManager executorManager;

    private final Map<Long, UserSession> uid2Session = new ConcurrentHashMap<>();

    public UserSessionManager(ExecutorManager executorManager) {
        this.executorManager = executorManager;
        cycleCheckOffLine();
    }

    public void addSession(UserSession userSession) {
        uid2Session.put(userSession.getUserId(), userSession);
    }


    /**
     * 周期检查下线
     */
    private void cycleCheckOffLine() {
        executorManager.runFixedDelay(() -> {
            uid2Session.values()
                    .removeIf(userSession -> userSession.getLastHeartTime() + 15 * Times.ONE_SECOND < Times.now());
        }, 0, 10, TimeUnit.SECONDS);
    }


    public boolean isOnline(long uid) {
        return uid2Session.containsKey(uid);
    }

    public boolean isOffLine(long uid) {
        return !isOnline(uid);
    }


    /**
     * fixme
     */
    public UserSession getRoleSession(long rid) {
        return uid2Session.get(rid);
    }
}
