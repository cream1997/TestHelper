package com.cream.helper.core.server;

import com.cream.helper.config.ProjectConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
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
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            // todo 添加自定义处理器
                            pipeline.addLast(null);
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = serverBootstrap.bind(projectConfig.getMockServerPort()).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("MockGameServer初始化异常", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
