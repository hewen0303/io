package com.nio.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @program: io
 * @description:
 * @author: hewen
 * @create: 2019-09-30 11:47
 **/
public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandler registered");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandler channelActive");
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            //利用nextXXX()方法输出内容
//            String str = sc.next();
//            // 发送数据到服务端
//            ctx.writeAndFlush(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));
//        }
        ctx.writeAndFlush(Unpooled.copiedBuffer("我是客户端", CharsetUtil.UTF_8));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandler channelInactive");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandler channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        // 接收服务端返回
        System.out.println("NettyClient rec: " + byteBuf.toString(CharsetUtil.UTF_8));
    }
}
