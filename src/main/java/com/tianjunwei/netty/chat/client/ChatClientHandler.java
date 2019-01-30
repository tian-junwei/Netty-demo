package com.tianjunwei.netty.chat.client;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tianjunwei
 * @date 2019/1/30 14:23
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //直接输出消息
        System.out.println(s);
    }
}