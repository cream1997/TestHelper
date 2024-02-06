package com.cream.helper.core.net.common.listener;

import cn.hutool.core.util.IdUtil;
import com.cream.helper.core.ExecutorManager;
import com.cream.helper.core.net.common.listener.base.MsgListener;
import com.cream.helper.core.net.handler.base.MsgHandler;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.msg.res.ResEnterRoleMsg;
import com.cream.helper.core.net.msg.res.ResMockMsgOneMsg;
import com.cream.helper.core.net.proto.clazz.CommonProto;
import com.cream.helper.utils.MsgClassUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Slf4j
@Component
public class ServerMsgListener extends MsgListener {

    private final Map<Integer, MsgHandler<?>> allServerMsgHandler = new ConcurrentHashMap<>();
    private final ApplicationContext appContext;
    private final ConcurrentLinkedQueue<ServerHeartTask> allHeartTasks = new ConcurrentLinkedQueue<>();


    @Autowired
    public ServerMsgListener(ExecutorManager executorManager,
                             ApplicationContext appContext) {
        this.appContext = appContext;
        registerHandler();
        serverHeart(executorManager);
    }

    private void serverHeart(ExecutorManager executorManager) {
        executorManager.runFixedRate(() -> {
            for (ServerHeartTask heartTask : allHeartTasks) {
                if (heartTask.taskFuture == null) {
                    exeTask(executorManager, heartTask);
                }
            }
            allHeartTasks.removeIf(heartTask -> heartTask.getTaskFuture() != null && heartTask.taskFuture.isCancelled());
        }, 1, 1, TimeUnit.SECONDS);
    }

    private void exeTask(ExecutorManager executorManager, ServerHeartTask heartTask) {
        ScheduledFuture<?> scheduledFuture = executorManager.runFixedRate(heartTask.getTask()
                , 1, 1, TimeUnit.SECONDS);
        heartTask.setTaskFuture(scheduledFuture);
    }

    @Override
    public void receiveMsg(ChannelHandlerContext ctx, Message<?> msg) {
        MsgHandler<?> msgHandler = allServerMsgHandler.get(msg.getMsgId());
        if (msgHandler == null) {
            log.error("未定义消息处理器, msgId:{}", msg.getMsgId());
        } else {
            Message<?> resMsg = msgHandler.handle0(msg);
            ctx.channel().writeAndFlush(resMsg);
            if (resMsg instanceof ResEnterRoleMsg) {
                // 服务器就不搞session，简单实现数据模拟
                allHeartTasks.add(new ServerHeartTask(ctx.channel(), this::mockRes));
            }
        }
    }

    private void mockRes(Channel channel) {
        ResMockMsgOneMsg mockMsg1 = new ResMockMsgOneMsg(() -> CommonProto.MockMsgOne.newBuilder()
                .setDesc(IdUtil.fastSimpleUUID())
                .setDesc2((int) IdUtil.getSnowflakeNextId())
                .build());
        channel.writeAndFlush(mockMsg1);
    }

    /**
     * 目前不采用反射读取，简单一点
     */
    @SuppressWarnings("rawtypes")
    private void registerHandler() {
        Map<String, MsgHandler> allMsgHandler = appContext.getBeansOfType(MsgHandler.class);
        for (MsgHandler msgHandler : allMsgHandler.values()) {
            try {
                int msgId = MsgClassUtil.getMsgId(msgHandler);
                allServerMsgHandler.put(msgId, msgHandler);
            } catch (Exception e) {
                log.error("注册处理器发生异常", e);
            }
        }
    }

    @Getter
    @AllArgsConstructor
    private static class ServerHeartTask {
        private final Channel channel;
        @Setter
        private ScheduledFuture<?> taskFuture;
        private final Runnable task;

        public ServerHeartTask(Channel channel, Consumer<Channel> task) {
            this.channel = channel;
            this.task = () -> {
                try {
                    task.accept(channel);
                } catch (Exception e) {
                    // 这里主要是简单处理channel关闭的情况
                    if (taskFuture != null) {
                        taskFuture.cancel(true);
                    }
                }
            };
        }
    }
}
