package com.cream.helper.core.net.client;

import com.cream.helper.config.configuration.exception.CommonError;
import com.cream.helper.core.net.common.GameNetSetup;
import com.cream.helper.core.net.common.MsgPool;
import com.cream.helper.core.net.common.msg.base.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameClient {
    private final static int Threads = 1;
    private final MsgPool msgPool = new MsgPool();
    private final Channel channel;

    public static final AttributeKey<GameClient> ClientRefKey = AttributeKey.valueOf(null);

    public GameClient(GameNetSetup setup) throws CommonError {
        this.channel = this.start(setup);
    }

    private Channel start(GameNetSetup setup) throws CommonError {
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
                    .connect(setup.getGamePlatform().ip, setup.getGamePlatform().port)
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
        return null;
    }

    public void receiveMsg(Message<?> msg) {
        msgPool.addResMsg(msg);
    }
}
