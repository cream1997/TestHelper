package com.cream.helper.core.net;

import com.cream.helper.config.configuration.exception.RunErr;
import com.cream.helper.core.ExecutorManager;
import com.cream.helper.core.net.bo.UserSession;
import com.cream.helper.utils.Times;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 管理RoleSession
 */
@Slf4j
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
            uid2Session.values().removeIf(userSession ->
                    {
                        if (userSession.getLastHeartTime() + 5 * Times.ONE_SECOND < Times.now()) {
                            userSession.getGameClient().close();
                            return true;
                        } else {
                            return false;
                        }
                    }
            );
        }, 0, 10, TimeUnit.SECONDS);
    }

    public void removeSession(long uid) {
        UserSession session = uid2Session.remove(uid);
        if (session != null) {
            session.getGameClient().close();
            log.info("成功退出");
        } else {
            throw new RunErr("退出时找不到session uid:" + uid);
        }
    }

    public boolean isOnline(long uid) {
        return uid2Session.containsKey(uid);
    }

    public boolean isOffLine(long uid) {
        return !isOnline(uid);
    }


    public UserSession getSession(long uid) {
        return uid2Session.get(uid);
    }
}
