package com.nio.netty.server;

import com.nio.netty.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @program: io
 * @description: netty服务端
 * @author: hewen
 * @create: 2019-09-29 15:26
 **/
public class NettyServer {

    private int port;

    NettyServer(int port) {
        this.port = port;
    }

    public void run() {
        // 定义工作组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = null;
        try {
            // 实例启动项
            serverBootstrap = new ServerBootstrap();
            // 设置工作组满时等待握手的tcp队列的大小
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            // 绑定工作组
            serverBootstrap
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyServerHandler());
                }
            });

            System.out.println("netty server is start ......");
            // 持续监听连接
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            // 监听服务端口关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.valueOf(args[0]);
        }
        NettyServer nettyServer = new NettyServer(port);
        nettyServer.run();
    }
}
