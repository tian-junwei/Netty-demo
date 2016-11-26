package com.tianjunwei.netty.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter{

	@Override  
    public void channelActive(ChannelHandlerContext ctx) {  
    }  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) {  
       try {
		Thread.sleep(200000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
       //ctx.writeAndFlush(msg);
       ctx.fireChannelRead(msg);
    }  
    
   @Override  
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {  
       cause.printStackTrace();  
       ctx.close();  
    }  
}
