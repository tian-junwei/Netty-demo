package com.tianjunwei.netty.test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler2 extends ChannelInboundHandlerAdapter{

	@Override  
    public void channelActive(ChannelHandlerContext ctx) {  
    }  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) {  
    }  
  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	ctx.flush();
    }
  
   @Override  
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {  
       cause.printStackTrace();  
       ctx.close();  
    }  
}
