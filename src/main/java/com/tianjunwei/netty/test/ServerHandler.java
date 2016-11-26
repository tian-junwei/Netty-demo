package com.tianjunwei.netty.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter{

	@Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        System.err.println("server channelRead..");  
        System.err.println("server read message"+ msg.toString());  
        ctx.write("server write"+msg);  
        ctx.flush();  
    }  
	@Override  
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
	     cause.printStackTrace();  
	     ctx.close();  
	}  
}
