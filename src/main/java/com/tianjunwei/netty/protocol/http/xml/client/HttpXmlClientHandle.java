package com.tianjunwei.netty.protocol.http.xml.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.tianjunwei.netty.protocol.http.xml.codec.HttpXmlRequest;
import com.tianjunwei.netty.protocol.http.xml.codec.HttpXmlResponse;
import com.tianjunwei.netty.protocol.http.xml.pojo.OrderFactory;


public class HttpXmlClientHandle extends
	SimpleChannelInboundHandler<HttpXmlResponse> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
	HttpXmlRequest request = new HttpXmlRequest(null,
		OrderFactory.create(123));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	cause.printStackTrace();
	ctx.close();
    }

    protected void messageReceived(ChannelHandlerContext ctx,
	    HttpXmlResponse msg) throws Exception {
	System.out.println("The client receive response of http header is : "
		+ msg.getHttpResponse().headers().names());
	System.out.println("The client receive response of http body is : "
		+ msg.getResult());
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpXmlResponse msg) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
