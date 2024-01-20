package com.cream.helper.core.net;

import com.cream.helper.core.ExecutorManager;
import com.cream.helper.core.net.bo.RoleSession;
import com.cream.helper.core.net.client.GameClient;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.obj.domain.bo.RoleHeartInfo;
import com.cream.helper.utils.Times;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 管理RoleSession
 */
@Service
public class RoleSessionManager {

    private final ExecutorManager executorManager;

    private final Map<Long, RoleSession> rid2Session = new ConcurrentHashMap<>();

    public RoleSessionManager(ExecutorManager executorManager) {
        this.executorManager = executorManager;
        cycleCheckOffLine();
    }

    /**
     * 周期检查下线
     */
    private void cycleCheckOffLine() {
        executorManager.runFixedDelay(() -> {
            rid2Session.values()
                    .removeIf(roleSession -> roleSession.getLastHeartTime() + 15 * Times.ONE_SECOND < Times.now());
        }, 0, 10, TimeUnit.SECONDS);
    }

    public boolean isOnline(long rid) {
        return rid2Session.containsKey(rid);
    }

    public boolean isOffLine(long rid) {
        return !isOnline(rid);
    }

    public void addOnline(Role role, GameClient gameClient) {
        rid2Session.put(role.getId(), new RoleSession(role.getId(), role.getUserId(), gameClient));
    }

    public void removeOnline(long rid) {
        rid2Session.remove(rid);
    }

    public Ret<RoleHeartInfo> heart(long rid) {
        RoleSession roleSession = rid2Session.get(rid);
        if (roleSession == null) {
            throw new RuntimeException("心跳异常");
        }
        roleSession.setLastHeartTime(Times.now());
        long userId = roleSession.getUserId(), lastHeartTime = roleSession.getLastHeartTime();
        return Ret.ok(new RoleHeartInfo(userId, rid, lastHeartTime));
    }

    public RoleSession getRoleSession(long rid) {
        return rid2Session.get(rid);
    }
}
