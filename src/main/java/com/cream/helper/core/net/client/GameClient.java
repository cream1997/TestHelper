package com.cream.helper.core.net.client;

import com.cream.helper.config.configuration.exception.CommonError;
import com.cream.helper.core.net.common.GameNetSetup;
import com.cream.helper.core.net.common.MsgPool;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.obj.domain.vo.ServerVO;
import com.cream.helper.utils.Times;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class GameClient {
    private final static int Threads = 1;
    /**
     * 取消息加锁，保证取指定消息时，不会被fetchAll抢先
     */
    private final ReentrantLock fetchLock = new ReentrantLock();
    private final MsgPool msgPool = new MsgPool();
    private final Channel channel;

    public static final AttributeKey<GameClient> ClientRefKey = AttributeKey.valueOf("clientRefKey");

    public GameClient(GameNetSetup setup, ServerVO serverVO) throws CommonError {
        this.channel = this.start(setup, serverVO);
    }

    public void sendMsg(Message<?> msg) {
        channel.writeAndFlush(msg);
    }

    public <T extends Message<?>> T sendMsg(Message<?> msg, Class<T> resClass) throws CommonError {
        fetchLock.lock();
        int msgSerialNum = msgPool.getMsgSerialNum();
        try {
            channel.writeAndFlush(msg);
            return msgPool.fetchResMsg(resClass, msgSerialNum);
        } finally {
            fetchLock.unlock();
        }
    }

    public List<Message<?>> fetchAllMsg() {
        fetchLock.lock();
        try {
            return msgPool.fetchAllResMsg();
        } finally {
            fetchLock.unlock();
        }
    }

    public void receiveMsg(Message<?> msg) {
        msg.setReceiveTime(Times.now());
        msgPool.addResMsg(msg);
    }

    private Channel start(GameNetSetup setup, ServerVO serverVO) throws CommonError {
        NioEventLoopGroup worker = new NioEventLoopGroup(Threads);
        Channel channel = null;
        try {
            channel = new Bootstrap()
                    .group(worker)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(setup.getMsgEncoder())
                                    .addLast(setup.getMsgDecoder())
                                    .addLast(setup.getGlobalMsgHandler());
                        }
                    })
                    .connect(serverVO.getIp(), serverVO.getPort())
                    .sync()
                    .channel();
            channel.attr(ClientRefKey).set(this);
        } catch (InterruptedException e) {
            log.error("创建客户端异常", e);
            throw new CommonError("连接服务器异常");
        } finally {
            if (channel != null) {
                channel.closeFuture()
                        .addListener(future -> worker.shutdownGracefully());
            }
        }
        return channel;
    }

    public void close() {
        if (channel != null) {
            channel.close();
        }
    }
}
