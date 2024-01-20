package com.cream.helper.core.server;

import com.cream.helper.config.ProjectConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 模拟的游戏服务器，用以本地测试
 */
@Slf4j
@Component
public class MockGameServer {

    private static final int BossThreads = 1;
    private static final int WorkerThreads = 1;

    @Autowired
    public MockGameServer(ProjectConfig projectConfig) {
        NioEventLoopGroup boss = new NioEventLoopGroup(BossThreads);
        NioEventLoopGroup worker = new NioEventLoopGroup(WorkerThreads);
        Channel serverChannel = null;
        try {
            serverChannel = new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128) //设置最大连接数量
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置channel保持连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast(null);
                        }
                    })
                    .bind(projectConfig.getMockServerPort())
                    .sync()
                    .channel();
        } catch (Exception e) {
            log.error("MockGameServer初始化异常", e);
        } finally {
            if (serverChannel != null) {
                serverChannel.closeFuture()
                        .addListener(future -> shutdownGracefully(boss, worker));
            }
        }
    }

    private void shutdownGracefully(NioEventLoopGroup boss, NioEventLoopGroup worker) {
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
